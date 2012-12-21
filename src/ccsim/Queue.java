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
public class Queue {
    private ArrayList<Call> calls;
    String callType;
    
    public Queue(String ct) {
        callType = ct;
        calls = new ArrayList<Call>();
    }

    public ArrayList<Call> getCalls() {
        return calls;
    }

    public void setCalls(ArrayList<Call> calls) {
        this.calls = calls;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }
    
    public int getCallCount() {
        return this.calls.size();
    }
    
    public void addCallToQueue(Call call) {
        calls.add(call);
    }
}
