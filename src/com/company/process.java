package com.company;
 class Process {
    private String name;
    private int arrivalTime;
    private int BurstTime;
    private int WaitingTime;
    private int turnaroundTime;
    private int completionTime;
    private int responseTime;
    private int remaining;
    private int priority;
    private int qType;
    private double factor;
    private int quantum;

    public void setFactor (double fac)
    {
        this.factor = fac;
    }

    public double getFactor () {
        return factor;
    }

    public void setQuantum ( int quantum ) {
        this.quantum = quantum;
    }

    public int getQuantum () {
        return quantum;
    }

    public int getqType () {
        return qType;
    }
     Process(){

    }
    public void setqType(int qType) {
        this.qType = qType;
    }

     Process(String name, int arrivalTime,int burstTime,int qType,boolean multilevel){
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.BurstTime = burstTime;
        this.qType = qType;
        this.responseTime = -1;
        this.remaining = burstTime;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }

    public int getCompletionTime() {
        return completionTime;
    }

    public Process(String name, int arrivalTime, int BurstTime , int processPriority , int quantumTime) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.BurstTime = BurstTime;
        this.responseTime = -1;
        this.remaining = BurstTime;
        this.priority = processPriority;
        this.quantum = quantumTime;
    }

    public Process(String name, int arrivalTime, int BurstTime,int priority) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.BurstTime = BurstTime;
        this.priority=priority;

    }

    public void setWaitingTime(int WaitingTime) {
        this.WaitingTime = WaitingTime;
    }

    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    public int getWaitingTime() {
        return WaitingTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setBurstTime(int burstTime) {
        BurstTime = burstTime;
    }

    public String getName() {
        return name;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return BurstTime;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    public int getRemaining() {
        return this.remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }


    @Override
    public String toString() {
        return name + " :  " + " WaitingTime=" + WaitingTime + ",  turnaroundTime=" + turnaroundTime;
    }


}
