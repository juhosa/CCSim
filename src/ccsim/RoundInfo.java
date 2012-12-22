/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ccsim;

/**
 *
 * @author juho
 */
public class RoundInfo {
    
    private int callsUnderAWT;
    private int callsOverAWT;
    private double serviceLevel;
    private int roundNumber;
    
    public RoundInfo() {
        this.callsOverAWT = -1;
        this.callsUnderAWT = -1;
        this.serviceLevel = -1.0;
        this.roundNumber = -1;
    }

    public int getCallsUnderAWT() {
        return callsUnderAWT;
    }

    public void setCallsUnderAWT(int callsUnderAWT) {
        this.callsUnderAWT = callsUnderAWT;
    }

    public int getCallsOverAWT() {
        return callsOverAWT;
    }

    public void setCallsOverAWT(int callsOverAWT) {
        this.callsOverAWT = callsOverAWT;
    }

    public double getServiceLevel() {
        return serviceLevel;
    }

    private void setServiceLevel(double serviceLevel) {
        this.serviceLevel = serviceLevel;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }
    
    public void calculateServicelevel() {
        int sum = this.callsOverAWT + this.callsUnderAWT;
        double sl = this.callsOverAWT / sum * 100.0;
        this.serviceLevel = sl;
    }
    
}
