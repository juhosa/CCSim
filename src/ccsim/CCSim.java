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
            
            ArrayList<Call> callsThisSecond = new ArrayList<Call>();
            /*
             * Hae kaikki puhelut joidenka calltime on kuluva sekunti,
             * poista löytyneet incomingcalls-listasta
             */
            for(int i = incomingCalls.size()-1; i > 0; i--){
                Call c = incomingCalls.get(i);
                int calltime = c.getCallTime();
                if(calltime > simCurrentTime) {
                    break;
                }
                else if(calltime == simCurrentTime) {
                    callsThisSecond.add(c);
                    incomingCalls.remove(i);
                }
            }
            
            // Jos ei yhtään puhelua, add second ja continue
            if(callsThisSecond.isEmpty()) {
                simCurrentTime++;
                continue;
            }
            
            /*
             * Käy läpi puhelut ja etsi niille agentti,
             * jos ei löydy vastaajaa, lisää puhelu oikeaan jonoon.
             */ 
            for(int i = 0; i < callsThisSecond.size(); i++) {
                String calltype = callsThisSecond.get(i).getType();
                for(int j = 0; j < agentGroups.size(); j++) {
                    AgentGroup agr = agentGroups.get(j);
                    if(agr.getSkills().contains(calltype)) {
                        // Kutsutaan metodia joka tekee asetusten mukaisen
                        // valinnan agentin valintaan
                        Agent ag = selectAgent(agr);
                    }
                }
            }
            
            /*
             * Poista meneillään olevista puheluista (eli varatuista agenteista)
             * sekunti.
             * Tarkista myös jos puhelu loppunut edellisellä sekunnilla
             * ja aseta agentti vapaaksi.
             */
            for(int i = 0; i < agentGroups.size(); i++) {
                AgentGroup agr = agentGroups.get(i);
                ArrayList<Agent> ags = agr.getAgents();
                for(int j = 0; j < ags.size(); j++) {
                    Agent ag = ags.get(j);
                    if(!ag.getAvailable() && ag.getCallRemainingInSecs() > 0) {
                        ag.setCallRemainingInSecs(ag.getCallRemainingInSecs()-1);
                    }
                    else if(ag.getCallRemainingInSecs() == 0) {
                        ag.setAvailable(Boolean.TRUE);
                        ag.setCallRemainingInSecs(-1);
                    }
                }
            }
            
            //System.out.println("Aika: " + simCurrentTime + " puheluita tulossa: " + incomingCalls.size());
            
            // sekunti etiäpäin
            simCurrentTime++;
        } // simulointi while end
        
    } // runcombi end
    
    private static Agent selectAgent(AgentGroup agr) {
        Agent ret;
        ret = selectFirstAvailable(agr);
        return ret;
    }
    
    private static Agent selectFirstAvailable(AgentGroup agr) {
        Agent ret = null;
        ArrayList<Agent> ags = agr.getAgents();
        for(int i = 0; i < ags.size(); i++) {
            Agent ag = ags.get(i);
            if(ag.getAvailable()) {
                ret = ag;
            }
        }
        
        return ret;
    }
}
