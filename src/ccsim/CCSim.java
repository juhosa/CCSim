/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ccsim;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Käyttäjä
 */
public class CCSim {
    
    public static ArrayList<Queue> queues = new ArrayList<Queue>();
    public static ArrayList<Call> incomingCalls = new ArrayList<Call>();
    
    public static ArrayList<AgentGroup> agentGroups = new ArrayList<AgentGroup>();
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Luetaan asetukset
        readSettings();
        
        makeCombinations();
        makeCalls();
        int[] combi = new int[3];
        combi[0] = 2;
        combi[1] = 2;
        combi[2] = 2;
        // run teh simulation for this combination of agents
        runCombination(combi);
        
    }
    
    private static void readSettings() {
        // Luetaan sitte filu
        
        /*
         * Luodaan jonot kun on luettu puhelutyyppien määrä
         */
        for(int i = 0; i < 1; i++) {
            Queue q = new Queue("A");
            queues.add(q);
        }
        
        // Luodaan agentit ku o luettu tarpeelliset jutut
        int groupCount = 1;
        makeAgentsIntoGroups(groupCount);
    }
    
    private static void makeCombinations() {
        
    }
    
    private static void makeCalls() {
        Call c;
        for(int i = 0; i < 10; i++) {
            c = new Call("A");
            c.setLength((i+1)*2);
            c.setCallTime((i+i*2)+2);
            incomingCalls.add(c);
        }
        
        // sortataan käännettyyn järjestykseen
        Collections.sort(incomingCalls, new sortByCalltime());
       
        System.out.println("In total " + incomingCalls.size() + " calls generated.");
    } // makeCallss end
    
    private static void makeAgentsIntoGroups(int grCount) {
        for(int k = 0; k < grCount; k++) {
            AgentGroup ag = new AgentGroup("S1");
            ArrayList<String> skills = new ArrayList<String>();
            skills.add("A");
            skills.add("B");
            ag.setSkills(skills);

            for(int i = 0; i < 1; i++) {
                Agent a = new Agent();
                ag.addAgent(a);
            }
            
            agentGroups.add(ag);
            
            System.out.println(ag.getAgents().size() + " agents created "
                    + "for group " + ag.getName());
        }
    }
    
    // this is where the magic happens
    private static void runCombination(int[] combi) {
        int simCurrentTime = 0;
        int simLength = 600;
        
        while(simCurrentTime < simLength) {
            
            
            
            // sekunti etiäpäin
            simCurrentTime++;
        } // simulointi while end
        
    }
}
