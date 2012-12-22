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
}
