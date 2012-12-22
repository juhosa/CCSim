/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ccsim;

/**
 *
 * @author Käyttäjä
 */
public class Call {
    //private int length;
    private String type;
    private int callTime;
    private double avgLen;
    private int timeInQueue;
    private double arrivalRate;
    
    public Call(String t) {
        this.type = t;
        this.timeInQueue = 0;
    }
    
    /*
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
    */
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCallTime() {
        return callTime;
    }

    public void setCallTime(int callTime) {
        this.callTime = callTime;
    }

    public double getAvgLen() {
        return avgLen;
    }

    public void setAvgLen(double avgLen) {
        this.avgLen = avgLen;
    }

    public int getTimeInQueue() {
        return timeInQueue;
    }

    public void setTimeInQueue(int timeInQueue) {
        this.timeInQueue = timeInQueue;
    }

    public double getArrivalRate() {
        return arrivalRate;
    }

    public void setArrivalRate(double arrivalRate) {
        this.arrivalRate = arrivalRate;
    }
    
    
    
}
