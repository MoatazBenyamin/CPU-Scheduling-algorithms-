package com.company;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class SRTF {
    private ArrayList<Process> p;
    private ArrayList<Integer> allBurstTime = new ArrayList<>();
    private ArrayList<Integer> printingOrder = new ArrayList<>();
    private int currTime;
    private int contextSwitching;
    private int numOfProcesses;
    private int currProcess;
    private int minimumRemaining;


    public SRTF(ArrayList<Process> p, int contextSwitching) {
        this.p = p;
        this.currTime = 0;
        this.contextSwitching = contextSwitching;
        numOfProcesses = p.size();
        Collections.sort(p,Comparator.comparing(Process::getArrivalTime));
        for (int i = 0; i < numOfProcesses; i++) allBurstTime.add(p.get(i).getBurstTime());
        currProcess = 0;
        minimumRemaining = Integer.MAX_VALUE;
    }

    public void execute() {
        boolean cond = false;
        int loop = 0;
        int prevProcess = 0;
        while (numOfProcesses > 0) {//while there are remaining processes
            for (int i = 0; i < p.size(); i++) {
                if (p.get(i).getArrivalTime() <=currTime && allBurstTime.get(i) > 0
                    && allBurstTime.get(i) < minimumRemaining) {
                    minimumRemaining = allBurstTime.get(i);
                    currProcess = i;
                    cond = true;
                }
            }
            if (!cond) {
                currTime++;
                continue;
            }
            loop++;
            if (loop > 1 && prevProcess != currProcess) {
                currTime += contextSwitching;
                printingOrder.add(prevProcess);
            }
            allBurstTime.set(currProcess, (allBurstTime.get(currProcess)) - 1);
            minimumRemaining = allBurstTime.get(currProcess);
            if (minimumRemaining == 0)
                minimumRemaining = Integer.MAX_VALUE;
            prevProcess = currProcess;
            if (allBurstTime.get(currProcess) == 0) {//when there is a process finishes it's work
                numOfProcesses--;
                cond = false;
                int newWaitingTime = currTime + 1 - p.get(currProcess).getBurstTime() - p.get(currProcess).getArrivalTime();
                if (newWaitingTime > 0)
                    p.get(currProcess).setWaitingTime(newWaitingTime);
                else p.get(currProcess).setWaitingTime(0);
                p.get(currProcess).setTurnaroundTime(p.get(currProcess).getWaitingTime() + p.get(currProcess).getBurstTime());
            }
            currTime++;
        }
        printingOrder.add(prevProcess);
        print(printingOrder);
    }

    private void print(ArrayList<Integer> printingOrder) {
        for (int i = 0; i < printingOrder.size(); i++) {
            System.out.print(p.get(printingOrder.get(i)).getName());
            if (i == printingOrder.size() - 1) System.out.println();
            else System.out.print(" | ");
        }
        int avgWaiting = 0, avgTurn = 0;
        for (int i = 0; i < p.size(); i++) {
            System.out.println(p.get(i));
            avgWaiting += p.get(i).getWaitingTime();
            avgTurn += p.get(i).getTurnaroundTime();
        }
        System.out.println("Average waiting time: " + (double) avgWaiting / p.size());
        System.out.println("Average turnaround time: " + (double) avgTurn / p.size());
    }

}