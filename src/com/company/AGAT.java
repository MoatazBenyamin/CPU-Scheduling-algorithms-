package com.company;

import java.util.ArrayList;
import static java.lang.Math.round;

public class AGAT {

    double  curTime = 0 , quantumPercent = 0 , contextSwitch = 0,calc=0;
    //int isSmailar;
    int i=0,RemaingQuantum=0;
    ArrayList <Process> process = new ArrayList <> (  );
    ArrayList <Process> active = new ArrayList <> (  );
    ArrayList <Process> dead = new ArrayList <> (  );

    AGAT ( ArrayList < Process > proc, int contextTime )
    {
        this.process = proc;
        this.contextSwitch = contextTime;
        sortByArrival ();
        calcAgatFactor ( );
        sortByFactor();
    }


    public void sortByArrival () {
        for ( int i = 0 ; i < process.size ( ) ; i++ ) {
            for ( int j = i + 1 ; j < process.size ( ) ; j++ ) {
                if ( process.get ( j ).getArrivalTime () < process.get ( i ).getArrivalTime () ) {
                    swap ( j , i );
                }
            }
        }
    }

    private void swap ( int i, int j ) {
        Process swapProc ;
        swapProc = process.get ( j );
        process.set ( j , process.get ( i ));
        process.set ( i , swapProc);
        ///////////////////////////////////
        swapProc = active.get ( i );
        active.set ( i , active.get ( j ));
        active.set ( j , swapProc);
    }


    public double calcV1 () {
        int    constVar = 1;
        double last     = 0;
        for ( int i = 0 ; i < process.size ( ) ; i++ ) {
            if ( process.get ( i ).getArrivalTime ( ) > last ) {
                last = process.get ( i ).getArrivalTime ( );
            }
        }

        if ( last > 10 ) {
            last = round ( last / 10 );
            return last;
        }
        else {
            return constVar;
        }
    }

    public double calcV2 () {
        double max = 0;
        for ( Process p : process ) {
            if ( p.getRemaining ( ) > max ) {
                max = p.getRemaining ( );
            }
        }
        if ( max > 10 ) {
            max = round ( max / 10 );
            return max;
        }
        else {
            return 1;
        }
    }

    public void calcAgatFactor ( ) {
        int priority = 0;
        double agatFactor = 0;

        double v1 = calcV1(), v2 = calcV2();

        for (Process p : active) {
            priority = p.getPriority();
            agatFactor = round((10 - priority) + (p.getArrivalTime() / v1) + (p.getRemaining() / v2));
            p.setFactor(agatFactor);

        }
    }
    public void sortByFactor()
    {
        Process p,prev=new Process();
        while(process.size()!=0)
        {
            calcAgatFactor();
            if(i==0)
            {
                // print(process.get(i));
                quantumPercent+=round(process.get(0).getQuantum()*.4);

                execute(process.get(0),0);
                prev=process.get(0);
                i++;
            }
            else if(active.size()==1)
            {
                quantumPercent+=round(active.get(0).getQuantum()*.4);

                prev=active.get(0);
                execute(active.get(0),0);


            }
            else if(!active.isEmpty())
            {
                calcAgatFactor();
                p=findBetterProcess();
                String name=p.getName();

                if(prev.getName()==(name))
                {
                    //  print(prev);
                    quantumPercent+=round(prev.getQuantum()*.4);
                    execute(prev,1);

                }
                else
                {
                    //  print(p);
                    quantumPercent+=round(p.getQuantum()*.4);
                    prev.setQuantum(prev.getQuantum()+(prev.getQuantum() - RemaingQuantum));
                    calcAgatFactor();
                    execute(p,0);
                    prev=p;

                }

            }
            if(process.size()==0)
            {

                print();

            }
            else
            {

                calcAgatFactor();
                findNext();



            }


        }
    }
    public void findNext()
    {

        for(int i=0;i<process.size();i++){
            if(quantumPercent>=process.get(i).getArrivalTime())
            {
                if(!active.contains(process.get(i)))
                    active.add(process.get(i));

            }

        }
        for(int i=0;i<active.size();i++) {

            for (int j = 0; j < active.size(); j++)
                if (active.get(i).getFactor() >= active.get(j).getFactor()) {
                    swap(i, j);

                }

        }
    }

    public void execute(Process proc,int key)
    {
        boolean flag ;
        if(key==0)
        {
            flag=false;
        }
        else
            flag=true;
        calc=round(proc.getQuantum()*.4);
        proc.setCompletionTime(proc.getArrivalTime()+proc.getBurstTime());
        proc.setTurnaroundTime (proc.getCompletionTime()  - proc.getArrivalTime()); ;
        proc.setWaitingTime (proc.getTurnaroundTime() - proc.getBurstTime());
        if(proc.getRemaining()<=calc)
        {

            dead.add(proc);
            System.out.println("this process is done: "+proc.getName());
            process.remove(proc);
            active.remove(proc);




        }
        else
        {
            proc.setRemaining(proc.getRemaining() - (int)calc);
            curTime+=(int)calc;
            if(curTime>=proc.getQuantum()&&flag)
            {


                proc.setQuantum(proc.getQuantum()+2);
                calcAgatFactor();
                execute(process.get(0), 0 );
            }
            else  if(curTime<=proc.getQuantum()&&flag)
            {
                curTime+=(int)calc;
            }
            else
            {
                RemaingQuantum=(int)(proc.getQuantum()-curTime);

                curTime=0;
            }


        }

    }
    public Process findBetterProcess()
    {
        Process  p=new Process() ;
        if (process.size()==1){
            p=active.get(0);
        }

        for(int i=0;i<active.size();i++){
            for(int j=i+1;j<active.size();j++) {
                System.out.println("quantum remain calc "+active.get(i).getName()+" "+active.get(i).getQuantum() +'\n');
                System.out.println("quantum remain calc "+ active.get(j).getName()+" "+active.get(j).getQuantum()+'\n');
                System.out.println("AgatFactor calc "+active.get(i).getName()+" "+active.get(i).getFactor()+'\n' );
                System.out.println("AgatFactor calc "+active.get(j).getName()+" "+active.get(j).getFactor()+'\n');
                if (active.get(i).getFactor() <= active.get(j).getFactor()) {
                    //  System.out.println(active.get(i).getName());
                    p=active.get(i);
                    System.out.println("the best "+p.getName());
                }
                else
                {
                    System.out.println("the best "+p.getName());
                    p=active.get(j);
                }
            }

        }

        if(p==null)
        {
            p=active.get(0);
        }
        return p;

    }


    void print() {
        System.out.printf("%s %20s %20s %20s %20s %20s %20s %20s \n", "name", "burst time", "arrived time", "completion time",
                "turnaround time", "waiting time", "Quantum time","Priority" );
        int avgWaiting = 0, avgTurn = 0;
        for (Process p : dead) {
            System.out.printf("%s %20d %20d %20d %20d %20d %20d %20d\n", p.getName(), p.getBurstTime(), p.getArrivalTime(), p.getCompletionTime(),
                    p.getTurnaroundTime(), p.getWaitingTime(), p.getQuantum(),p.getPriority() );

        }
        calcAgatFactor ();
    }

    }
   /* public void removing(ArrayList<Process> forRemove,Process proc)
    {
        for(int i=0;i<forRemove.size();i++)
        {
                String name=proc.getName();
            System.out.println(name+" "+forRemove.get(i).getName());
            if(forRemove.get(i).getName().equals(name))
            {
                System.out.println("aa7aaaaa");
                if(forRemove.size()==process.size())
                {

                    process.remove(i);
                }
               else if(forRemove.size()==active.size())
                {
                    active.remove(i);
                }
            }
        }
    }*/


