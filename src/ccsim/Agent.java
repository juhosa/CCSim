/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ccsim;

/**
 *
 * @author Käyttäjä
 */
public class Agent {
    private Boolean available;
    private int callRemainingInSecs;
    
    public Agent() {
        // -1 means no calls
        this.callRemainingInSecs = -1;
        this.available = true;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public int getCallRemainingInSecs() {
        return callRemainingInSecs;
    }

    public void setCallRemainingInSecs(int callRemainingInSecs) {
        this.callRemainingInSecs = callRemainingInSecs;
    }
    
    
}
