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
    
    public AgentGroup(String n) {
        name = n;
        agents = new ArrayList<Agent>();
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
    
}
