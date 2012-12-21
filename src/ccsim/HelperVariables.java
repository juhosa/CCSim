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
public abstract class HelperVariables {
    private ArrayList<Queue> queues;
    private ArrayList<Call> incomingCalls;

    public ArrayList<Queue> getQueues() {
        return queues;
    }

    public void addToQueues(Queue q) {
        this.queues.add(q);
    }
}
