package com.company;
import java.util.ArrayList;
import java.util.Scanner;

 class Scheduler {


    static Scanner in = new Scanner(System.in);

    static void priority()
    {
        int numberOfProcesses;
        ArrayList<PriorityProcess> Processes = new ArrayList<PriorityProcess>();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter n of processes :");
        numberOfProcesses = input.nextInt();
        for(int i = 0 ; i < numberOfProcesses ;i++)
        {
            input = new Scanner(System.in);
            PriorityProcess p = new PriorityProcess();
            System.out.println( (i+1) + " Enter Process Name :");
            p.setName(input.nextLine());

            System.out.println("Enter Process Burst Time :");
            p.setBurstTime(input.nextInt())  ;

            System.out.println("Enter Process Arraival Time :");
            p.setArrivalTime(input.nextInt()) ;

            System.out.println("Enter Process prority :");
            p.setPriority(input.nextInt());
            Processes.add(p);
        }


        PriorityScheduling pScheduling = new PriorityScheduling(Processes);
        pScheduling.startScheduling();
        System.out.println( "\nAverage Waiting Time :  " + pScheduling.getAverageWaiting());
        System.out.println("Average Turnaround Time :" + pScheduling.getAverageTurnAround() + "\n");


	          /* System.out.println("Priority Schedule");
		   for(int i = 0 ; i < Processes.size();i++)
                   {
                       //Processes.get(i).printProcess();
                       // System.out.println("Waiting time : "+ Processes.getWaitingTime());
                   }*/
    }



    static void Srtf(){
        System.out.println("Enter number of process: ");
        int n = in.nextInt();
        ArrayList<Process> processes = new ArrayList<>();
        System.out.print("\nEnter context switching time: ");
        int contextTime = in.nextInt();
        for (int i = 0; i < n; i++) {
            in.nextLine();
            System.out.print("Enter name of process: ");
            String name = in.nextLine();
            System.out.print("\nEnter burst time: ");
            int burstTime = in.nextInt();
            System.out.print("\nEnter arrival time: ");
            int arrivalTime = in.nextInt();
            System.out.print("\nEnter Process Priority: ");
            int priority = in.nextInt();
            processes.add(new Process(name,arrivalTime,burstTime,priority));
        }
        System.out.println();
        SRTF srtf = new SRTF(processes,contextTime);
        srtf.execute();
        System.out.println();
    }
    static void AGAT(){
        System.out.println("Enter number of processes: ");
        int n = in.nextInt();
        ArrayList<Process> processes = new ArrayList<>();
        ArrayList<Integer> quantum = new ArrayList<>();


        System.out.print("\nEnter context switching time: ");
        int contextTime = in.nextInt();
        for (int i = 0; i < n; i++) {
            in.nextLine();
            System.out.print("Enter name of process: ");
            String name = in.nextLine();
            System.out.print("\nEnter burst time: ");
            int burstTime = in.nextInt();
            System.out.print("\nEnter arrival time: ");
            int arrivalTime = in.nextInt();
            System.out.print("\nEnter quantum: ");
            int qua = in.nextInt();
            quantum.add(qua);
            System.out.print("\nEnter Process Priority: ");
            int priority = in.nextInt();
            processes.add(new Process(name, arrivalTime, burstTime,priority ,qua));
        }
        System.out.println();
        AGAT RR = new AGAT(processes, contextTime);

    }

    public static void main(String[] args) {

        while(true){
            System.out.println("1- SRTF");
            System.out.println("2- SJF");
            System.out.println("3- Priority");
            System.out.println("4- RoundRobin");
            int option = in.nextInt();
            if(option == 1){
                Srtf();
            }else if(option == 2){
                SJF p = new SJF ( );
                p.addProcess ( );
            }else if(option == 3){
                priority ();
            }else if(option==4){
                AGAT();

            }

        }

    }
}