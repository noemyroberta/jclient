/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author noemy
 */
public abstract class Clients {
    
    private String name;
    private int credit;

    public Clients(String name, int credit) {
        this.name = name;
        this.credit = credit;
    }

    public Clients() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredit() {
        return credit;
    }
    
    public void addCredit(int credit) {
        this.credit += credit;       
    }
    
    public void removeCredit(int credit) {
        this.credit -= credit;  
    }
    
    public void close() {
        System.exit(0);
    }
 
}
