/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ccsim;

import java.util.ArrayList;

/**
 *
 * @author juho
 */
public class CombinationInfo {
    private int[] combi;
    private ArrayList<RoundInfo> rounds;
    private double totalCost;
    private double avgNumberOfCalls;
    private double avgSL;

    public CombinationInfo() {
        this.rounds = new ArrayList<RoundInfo>();
    }

    public int[] getCombi() {
        return combi;
    }

    public void setCombi(int[] combi) {
        this.combi = combi;
    }

    public ArrayList<RoundInfo> getRounds() {
        return rounds;
    }

    public void setRounds(ArrayList<RoundInfo> rounds) {
        this.rounds = rounds;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
    
    public void addRound(RoundInfo round) {
        rounds.add(round);
    }

    public double getAvgNumberOfCalls() {
        return avgNumberOfCalls;
    }

    public void setAvgNumberOfCalls(double avgNumberOfCalls) {
        //this.avgNumberOfCalls = avgNumberOfCalls;
        
    }

    public double getAvgSL() {
        //return avgSL;
        
        double tmp = 0.0;
        ArrayList<RoundInfo> ro = this.getRounds();
        System.out.println("Rounds size in combi: " + ro.size());
        for(int i = 0; i < ro.size(); i++) {
            RoundInfo r = ro.get(i);
            tmp += r.getServiceLevel();
        }
        double sl = tmp / (double)ro.size();
        
        this.avgSL = sl;
        return sl;
    }

    public void setAvgSL(double avgSL) {
        this.avgSL = avgSL;
    }
    
    
    
    
    
    public double avgNumberOfCalls() {
        double ret;
        int sum = 0;
        for(int i = 0; i < rounds.size(); i++) {
            sum += rounds.get(i).getCallsTotal();
        }
        ret = sum / rounds.size();
        return ret;
    }
}
