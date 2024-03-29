/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ccsim;

import java.util.ArrayList;

/**
 *
 * @author Käyttäjä
 */
public class AgentGroup {

    private ArrayList<Agent> agents;
    private String name;
    private ArrayList<String> skills;
    private ArrayList<Double> lambdas;
    private double cost;
    private int agentsMin;
    private int agentsMax;
    
    public AgentGroup(String n) {
        this.name = n;
        this.agents = new ArrayList<Agent>();
    }

    public ArrayList<Agent> getAgents() {
        return agents;
    }

    public void setAgents(ArrayList<Agent> agents) {
        this.agents = agents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<String> skills) {
        this.skills = skills;
    }
    
    public void addAgent(Agent a) {
        this.agents.add(a);
    }

    public ArrayList<Double> getLambdas() {
        return lambdas;
    }

    public void setLambdas(ArrayList<Double> lambdas) {
        this.lambdas = lambdas;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getAgentsMin() {
        return agentsMin;
    }

    public void setAgentsMin(int agentsMin) {
        this.agentsMin = agentsMin;
    }

    public int getAgentsMax() {
        return agentsMax;
    }

    public void setAgentsMax(int agentsMax) {
        this.agentsMax = agentsMax;
    }
    
    public void removeAgents() {
        this.agents.clear();
    }
    
    
}
