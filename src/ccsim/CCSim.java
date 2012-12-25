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
    
    public static int acceptableAnswerTime = 20;
    public static int callsAnsweredIndesiredTime = 0;
    public static int callsNotAnsweredIndesiredtime = 0;
    public static ArrayList<CombinationInfo> combinationInfos = new ArrayList<CombinationInfo>();
    
    public static int simLength = 100;
    public static int rounds = 2;
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Luetaan asetukset
        readSettings();
        
        makeCombinations();
        
        int[] combi = new int[3];
        combi[0] = 2;
        combi[1] = 2;
        combi[2] = 2;
        
        // this many rounds per combination
        for(int i = 0; i < rounds; i++) {
            makeCalls();
            // run teh simulation for this combination of agents
            runCombination(combi);
            calculateAndSaveRoundInfo(i, combi);
            reset();
        }
        calculateAndSaveCombinationInfo();
        
        
    } // main end
    
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
        int calltime = 0;
        /*
        for(int i = 0; i < 10; i++) {
            c = new Call("A");
            c.setArrivalRate(10.0);
            // c.setLength((i+1)*2);
            //calltime += (i+i*2)+2;
            calltime += calculateCallTime(c.getArrivalRate());
            c.setCallTime(calltime);
            c.setAvgLen(10.0);
            incomingCalls.add(c);
            System.out.println("calltime: " + calltime);
        }
        */
        while(calltime < simLength) {
            c = new Call("A");
            c.setArrivalRate(10.0);
            // c.setLength((i+1)*2);
            //calltime += (i+i*2)+2;
            calltime += calculateCallTime(c.getArrivalRate());
            
            if(calltime > simLength) {
                break;
            }
            
            c.setCallTime(calltime);
            c.setAvgLen(10.0);
            incomingCalls.add(c);
            //System.out.println("calltime: " + calltime);
        }
        
        // sortataan käännettyyn järjestykseen
        Collections.sort(incomingCalls, new sortByCalltime());
       
        System.out.println("In total " + incomingCalls.size() + " calls generated.");
    } // makeCallss end
    
    private static void makeAgentsIntoGroups(int grCount) {
        for(int k = 0; k < grCount; k++) {
            AgentGroup ag = new AgentGroup("S1");
            ag.setCost(9.5);
            ArrayList<String> skills = new ArrayList<String>();
            ArrayList<Double> lambdas = new ArrayList<Double>();
            skills.add("A");
            lambdas.add(new Double(10.0));            
            ag.setSkills(skills);
            ag.setLambdas(lambdas);

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
        
        while(simCurrentTime < simLength) {
            
            /*
             * Tarkista jonot, jos puheluita, koitetaan vastata niihin
             * ennen uusia puheluita.
             */
            for(int i = 0; i < queues.size();i++) {
                Queue q = queues.get(i);
                String calltype = q.callType;
                for(int j = 0; j < q.getCallCount(); j++) {
                    Call call = q.getCalls().get(j);
                    for(int k = 0; k < agentGroups.size(); k++) {
                        AgentGroup agr = agentGroups.get(k);
                        if(agr.getSkills().contains(calltype)) {
                            Agent ag = selectAgent(agr);
                            if(ag != null) {
                                double lambda = returnLambda(agr, calltype);
                                int callLen = calculateCallLength(lambda); // call.getAvgLen()
                                //System.out.println("callLen from queue " + callLen);
                                ag.setCallRemainingInSecs(callLen);
                                ag.setAvailable(Boolean.FALSE);
                                
                                int waitTime = simCurrentTime - call.getCallTime();
                                
                                if(waitTime <= acceptableAnswerTime) {
                                    callsAnsweredIndesiredTime++;
                                }
                                else {
                                    callsNotAnsweredIndesiredtime++;
                                }
                                
                                q.getCalls().remove(j);
                            }
                            else {
                                // Breakkaa pois pitkälle koska ei vapaita ag
                            }
                        }
                    }
                }
            }
            
            ArrayList<Call> callsThisSecond = new ArrayList<Call>();
            /*
             * Hae kaikki puhelut joidenka calltime on kuluva sekunti,
             * poista löytyneet incomingcalls-listasta
             */
            for(int i = incomingCalls.size()-1; i >= 0; i--){
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
            
            /*
             * Käy läpi puhelut ja etsi niille agentti,
             * jos ei löydy vastaajaa, lisää puhelu oikeaan jonoon.
             */ 
            for(int i = 0; i < callsThisSecond.size(); i++) {
                Call call = callsThisSecond.get(i);
                String calltype = call.getType();
                for(int j = 0; j < agentGroups.size(); j++) {
                    AgentGroup agr = agentGroups.get(j);
                    if(agr.getSkills().contains(calltype)) {
                        // Kutsutaan metodia joka tekee asetusten mukaisen
                        // valinnan agentin valintaan
                        Agent ag = selectAgent(agr);
                        if(ag != null) {
                            double lambda = returnLambda(agr, calltype);
                            int callLen = calculateCallLength(lambda); // call.getAvgLen()
                            //System.out.println("callLen " + callLen);
                            ag.setCallRemainingInSecs(callLen);
                            ag.setAvailable(Boolean.FALSE);
                            
                            callsAnsweredIndesiredTime++;
                        }
                        else {
                            // Laita puhelu jonoon
                            insertCallToCorrectQueue(call);
                        }
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
                    if(ag.getCallRemainingInSecs() > 0) { // !ag.getAvailable() && 
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
        
        /*
         * Juttujen printtailua
         */
        System.out.println("Puhelut joihin vastattiin alle AWT: " + callsAnsweredIndesiredTime);
        System.out.println("Puhelut joihin vastattiin yli AWT: " + callsNotAnsweredIndesiredtime);
        
        //System.out.println("Calls in queue after this combi round: " + queues.get(0).getCallCount());
        
        //System.out.println();
        
    } // runcombi end
    
    private static void reset() {
        callsAnsweredIndesiredTime = 0;
        callsNotAnsweredIndesiredtime = 0;
        queues = new ArrayList<Queue>();
        incomingCalls = new ArrayList<Call>();
        System.out.println();
    }
    
    private static void calculateAndSaveRoundInfo(int roundNum, int[] combi) {
        // Katotaan jonoon jääneet puhelut
        int tmp = 0;
        for(int i = 0; i < queues.size(); i++) {
            Queue q = queues.get(i);
            tmp += q.getCallCount();
        }
        System.out.println("Jonoon jaaneet puhelut: " + tmp);
        
        
        CombinationInfo comp = new CombinationInfo();
        comp.setCombi(combi);
        
        RoundInfo round = new RoundInfo();
        round.setRoundNumber(roundNum);
        callsNotAnsweredIndesiredtime += tmp;
        round.setCallsOverAWT(callsNotAnsweredIndesiredtime);
        round.setCallsUnderAWT(callsAnsweredIndesiredTime);
        round.calculateServicelevel();
        System.out.println("SL for this round: " + round.getServiceLevel());
        
        comp.addRound(round);
        
        combinationInfos.add(comp);
    }
    
    private static void calculateAndSaveCombinationInfo() {
        // Lasketaan combinationInfos listan vikan elementin avg SL
        // ja jotain
        
        CombinationInfo comb = combinationInfos.get(combinationInfos.size()-1);
        
        // Lasketaan totalcost
        
        
        // Lasketaan avg SL
        double tmp = 0.0;
        ArrayList<RoundInfo> rounds = comb.getRounds();
        System.out.println("Rounds size in combi: " + rounds.size());
        for(int i = 0; i < rounds.size(); i++) {
            RoundInfo r = rounds.get(i);
            tmp += r.getServiceLevel();
        }
        double sl = tmp / (double)rounds.size();
        
        System.out.println("combi SL: " + sl);
        
    }
    
    private static double returnLambda(AgentGroup agr, String calltype) {
        double ret = -1.0;
        int index = -1;
        for(int i = 0; i < agr.getSkills().size(); i++) {
            String skill = agr.getSkills().get(i);
            
            if(skill.equals(calltype)) {
                index = i;
                break;
            }
        }
        
        ret = agr.getLambdas().get(index).doubleValue();
        
        return ret;
    }
    
    private static int calculateCallLength(double avg) {
        int ret = -1;
        
        ret = (int)(Math.floor(Math.random()*(avg+(avg/2)))+(avg-(avg/2)));
        
        return ret;
    }
    
    private static int calculateCallTime(double arr) {
        int ret = -1;
        ret = (int)(Math.floor(Math.random()*(arr+(arr/2)))+(arr-(arr/2)));
        return ret;
    }
    
    private static void insertCallToCorrectQueue(Call call) {
        for(int i = 0; i < queues.size(); i++) {
            Queue q = queues.get(i);
            if(q.getCallType().equals(call.getType())) {
                q.addCallToQueue(call);
                break;
            }
        }
    }
    
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
