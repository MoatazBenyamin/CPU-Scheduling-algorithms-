/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

import java.util.ArrayList;

public class PriorityScheduling {
    ArrayList < PriorityProcess > Processes         = new ArrayList < PriorityProcess > ( ); //which processes havent yet been processed
    ArrayList < PriorityProcess > WaitingQueue      = new ArrayList < PriorityProcess > ( ); //which of above queue has arrived and is waiting
    ArrayList < PriorityProcess > executedProcesses = new ArrayList < PriorityProcess > ( ); //which have already been processed

    int CurrentTime = 0;

    PriorityScheduling ( ArrayList < PriorityProcess > Q ) {
        for ( PriorityProcess i : Q ) {
            Processes.add ( new PriorityProcess ( i ) );
        }
        Processes.sort ( null );
        CurrentTime = Processes.get ( 0 ).getArrivalTime ( );
        ConstructWaitingQueue ( CurrentTime );

    }

    public void startScheduling ( ) {
        PriorityProcess currentProcess = new PriorityProcess ( );
        while ( Processes.size ( ) > 0 ) {
            if ( FindMaxPriorityInWaiting ( ) == null ) {
                CurrentTime++;
                ConstructWaitingQueue ( CurrentTime );
            }
            else {
                currentProcess = FindMaxPriorityInWaiting ( );


                currentProcess.setStartTime ( CurrentTime );
                CurrentTime += currentProcess.getBurstTime ( );//increase time with brust time

                currentProcess.setWaitingTime ( currentProcess.getStartTime ( ) - currentProcess.getArrivalTime ( ) );
                currentProcess.setTurnaroundTime ( currentProcess.getWaitingTime ( ) + currentProcess.getBurstTime ( ) );

                executedProcesses.add ( currentProcess );
                Processes.remove ( currentProcess );

                ConstructWaitingQueue ( CurrentTime );//if we have arrival time <current time,for loop to put process less that current time

                System.out.println ( "\nOrder Of processes " );
                currentProcess.Execute ( );

                System.out.println ( "waiting time: " + currentProcess.getWaitingTime ( ) );
                System.out.println ( "turnaroundtime time: " + currentProcess.getTurnaroundTime ( ) + "\n" );
            }
        }
    }

    public double getAverageWaiting ( ) {
        double sumOfWaiting = 0.0;
        for ( PriorityProcess p : executedProcesses ) {
            sumOfWaiting += p.getWaitingTime ( );
        }
        return sumOfWaiting / executedProcesses.size ( );
    }

    public double getAverageTurnAround ( ) {
        double sumOfTurnAround = 0.0;
        for ( PriorityProcess p : executedProcesses ) {
            sumOfTurnAround += p.getTurnaroundTime ( );
        }
        return sumOfTurnAround / executedProcesses.size ( );
    }

    public PriorityProcess FindMaxPriorityInWaiting ( ) {//we have two check 1 we get max priority and if we get two processes have same priority
        PriorityProcess maxPriority = null;
        if ( WaitingQueue.size ( ) > 0 ) {
            maxPriority = WaitingQueue.get ( 0 );
            for ( int i = 1 ; i < WaitingQueue.size ( ) ; i++ ) {//check to get max priority
                if ( maxPriority.getPriority ( ) >= WaitingQueue.get ( i ).getPriority ( ) ) {
                    if ( maxPriority.getPriority ( ) == WaitingQueue.get ( i ).getPriority ( ) )//if two processes have same priority// then we get firts come from arrival time
                        maxPriority = ( maxPriority.getArrivalTime ( ) > WaitingQueue.get ( i ).getArrivalTime ( ) ? WaitingQueue.get ( i ) : maxPriority );
                    else
                        maxPriority = WaitingQueue.get ( i );
                }
            }
        }
        return maxPriority;//max priority have minimum number
    }

    public void ConstructWaitingQueue ( int currentTime ) {//add process in waiting queue that if arrival time <current time then we put it in queue
        WaitingQueue = new ArrayList < PriorityProcess > ( );
        for ( int i = 0 ; i < Processes.size ( ) ; i++ )
            if ( Processes.get ( i ).getArrivalTime ( ) <= currentTime ) {
                WaitingQueue.add ( Processes.get ( i ) );
            }
            else break;
    }


}