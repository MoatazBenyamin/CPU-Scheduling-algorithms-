package com.company;
import java.util.ArrayList;
import java.util.Scanner;

class SJF {
    int                   count     = 1;
    Scanner               read      = new Scanner ( System.in );
    double avgWaitingTime = 0 , avgTurnAroundTime = 0;

    ArrayList < String >  processId   = new ArrayList <> ( );
    ArrayList < Integer > burstTime   = new ArrayList <> ( );
    ArrayList < Integer > arrivalTime = new ArrayList <> ( );

    int waitingTime[] = new int[ 4 ];
    int turnAroundTime[] = new int[ 4 ];
    int completionTime[] = new int[ 4 ];
    int sum [] = new int[ 4 ];
    int save[] = new int[ 4 ];


    public void addProcess () {
        int x = 0;
        int y = 0;

        System.out.println ( "Enter the number of process" );
        count = read.nextInt ( );
        System.out.println ( "Enter Arrival Time -- Burst Time in that order  For process " );

        // We here make our program be able to execute only 4 processes at a time and didn't
        // let another process enter til the first 4 processes done their work to avoid starvation.

        while ( true ) {

            if ( count >= 4 ) {
                y = 4;
            }
            else if ( count > 0 && count < 4 ) {
                y = count;
                x = 1;
            }
            else {
                break;
            }

            for ( int i = 0 ; i < y ; i++ ) {
                System.out.println ( "Process " + (i+1) + ":-");
                read = new Scanner ( System.in );
                processId.add ( "process" + (i+1) );
                arrivalTime.add ( read.nextInt ( ) );
                burstTime.add ( read.nextInt ( ) );
            }

            sortByArrival ( );
            sortByBurst ( );

            PerformSJF ( );
            calcTimes (y);

            displaySJF ( );

            processId.clear ( );
            arrivalTime.clear ( );
            burstTime.clear ( );

            if ( x == 1 ) {
                break;
            }
            else {
                count -= y;
            }
        }

    }


    public void sortByArrival () {
        for ( int i = 0 ; i < processId.size ( ) ; i++ ) {
            for ( int j = i + 1 ; j < processId.size ( ) ; j++ ) {
                if ( arrivalTime.get ( j ) < arrivalTime.get ( i ) ) {
                    swap ( j , i );
                }
            }
        }
    }


    public void sortByBurst () {
        for ( int i = 0 ; i < processId.size ( ) ; i++ ) {
            for ( int j = i + 1 ; j < processId.size ( ) ; j++ ) {
                if ( arrivalTime.get ( j ).equals ( arrivalTime.get ( i ) ) ) {
                    if ( burstTime.get ( j ) < burstTime.get ( i ) ) {
                        swap ( j , i );
                    }
                }
            }
        }
    }


    public void PerformSJF () {

        int temp = 0, size = 0, point = 0;
        sum[ 0 ] = ( burstTime.get ( 0 ) + arrivalTime.get ( 0 ) );
        for ( int i = 1 ; i < processId.size ( ) ; i++ ) {
            temp = sum[ i - 1 ];
            if ( arrivalTime.get ( i ) <= sum[ 0 ] ) {
                save[ i - 1 ] = i;
                                point = i;
                size++;
            }

        }
        //for (int i = 0 ; i < )
        finalSort ( save , size );
    }

    public void finalSort ( int arr[] , int size ) {
        for ( int i = 0 ; i < size  ; i++ ) {
            for (int j = i+1 ; j < size  ; j++) {
                if ( burstTime.get ( arr[ i ] ) > burstTime.get ( arr[ j ] ) ) {
                    swap ( arr[ i ] , arr[ j ] );
                }
            }
        }
    }

    public void displaySJF () {
        for ( int i = 0 ; i < processId.size ( ) ; i++ ) {
            System.out.println ( "..................... " + processId.get ( i ) + " Execution information ....................." );
            System.out.println ( "-Arrival Time : " + arrivalTime.get ( i ) );
            System.out.println ( "-Burst Time : " + burstTime.get ( i ) );
            System.out.println ( "-Waiting Time : " + waitingTime[i] );
            System.out.println ( "-TurnAround Time : " + turnAroundTime[i] );
            System.out.println ( "-Completion Time : " + completionTime [i]);
            System.out.println ( ".........................................................................." );
        }
        System.out.println ( "-Average Waiting Time : " + avgWaitingTime);
        System.out.println ( "-Average Turn Around Time : " + avgTurnAroundTime);
        System.out.println ( "--------------------------------------------------------------------------" );
    }

    public void calcTimes(int size)
    {
        int A_W_T_Sum = 0 , A_T_A_T_Sum = 0 ;
        completionTime [ 0 ]  =  arrivalTime.get ( 0 ) + burstTime.get ( 0 )  ;
        turnAroundTime [ 0 ]  = ( arrivalTime.get ( 0 ) + burstTime.get(0)) ;
        waitingTime    [ 0 ]  =   0;

        for(int i = 0 ; i < size ; i++)
        {
            for (int j = i+1 ; j < size ; j++)
            {
                completionTime [ j ]  = completionTime[i] + burstTime.get ( j ) ;
                waitingTime    [ j ]  =  (completionTime[j] - arrivalTime.get ( j ) - burstTime.get ( j ));
                turnAroundTime [ j ]  = ( completionTime [ j ] - arrivalTime.get ( j )) ;
            }
        }
        for (int i = 0 ; i < size ; i++)
        {
            A_W_T_Sum += waitingTime[i];
            A_T_A_T_Sum += turnAroundTime[i];
        }
        avgWaitingTime = (A_W_T_Sum/(double)waitingTime.length);
        avgTurnAroundTime = (A_T_A_T_Sum/(double)turnAroundTime.length);
    }

    public void swap ( int x , int y ) {
        String swapString;
        int    intSwap;
        swapString = processId.get ( x );
        processId.set ( x , processId.get ( y ) );
        processId.set ( y , swapString );
        ///////////////////////////////////////
        intSwap = burstTime.get ( x );
        burstTime.set ( x , burstTime.get ( y ) );
        burstTime.set ( y , intSwap );
        //////////////////////////////////////
        intSwap = arrivalTime.get ( x );
        arrivalTime.set ( x , arrivalTime.get ( y ) );
        arrivalTime.set ( y , intSwap );
    }
}


