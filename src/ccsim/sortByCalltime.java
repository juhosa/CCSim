/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ccsim;

/**
 *
 * @author Käyttäjä
 */
public class sortByCalltime implements java.util.Comparator{

    @Override
    public int compare(Object o1, Object o2) {
        int sdif = ((Call)o2).getCallTime() - ((Call)o1).getCallTime();
        return sdif;
    }
    
}
