package com.company;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class PriorityProcess implements Comparable < PriorityProcess > {
    private String Name;
    private int    BurstTime;
    private int    WaitingTime;
    private int    TurnaroundTime;
    private int    StartTime;
    private int    ArrivalTime;
    private int    Priority;

    public PriorityProcess ( ) {
    }

    public PriorityProcess ( PriorityProcess P ) {
        Name        = P.getName ( );
        Priority    = P.getPriority ( );
        StartTime   = - 1;
        BurstTime   = P.getBurstTime ( );
        ArrivalTime = P.getArrivalTime ( );

    }


    public void setWaitingTime ( int waitingTime ) {
        WaitingTime = waitingTime;
    }

    public void setTurnaroundTime ( int turnaroundTime ) {
        TurnaroundTime = turnaroundTime;
    }

    public String getName ( ) {
        return Name;
    }

    public int getBurstTime ( ) {
        return BurstTime;
    }

    public int getArrivalTime ( ) {
        return ArrivalTime;
    }

    public int getPriority ( ) {
        return Priority;
    }

    public int getWaitingTime ( ) {
        return WaitingTime;
    }

    public int getTurnaroundTime ( ) {
        return TurnaroundTime;
    }

    /*public void printProcess ( ) {

        System.out.println ( "\nName : " + Name + "\nArrival Time : " + ArrivalTime + "\nBurstTime : " + BurstTime
                             + "\nPriority : " + Priority );
    }*/

    public int getStartTime ( ) {
        return StartTime;
    }

    public void setStartTime ( int startTime ) {
        StartTime = startTime;
    }

    void Execute ( ) {
        System.out.println ( "Process " + Name );

    }

    public void setName ( String name ) {
        Name = name;
    }

    public void setBurstTime ( int burstTime ) {
        BurstTime = burstTime;
    }

    public void setArrivalTime ( int arrivalTime ) {
        ArrivalTime = arrivalTime;
    }

    public void setPriority ( int priority ) {
        Priority = priority;
    }

    @Override
    public int compareTo ( PriorityProcess o ) {//compare arrival time
        // TODO Auto-generated method stub
        return this.getArrivalTime ( ) - o.getArrivalTime ( );
    }


}