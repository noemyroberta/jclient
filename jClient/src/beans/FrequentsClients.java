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
public class FrequentsClients extends Clients {
    
    private int number;

    public FrequentsClients(int number, String name, int credit) {
        super(name, credit);
        
        this.number = number;
    }
    
    public FrequentsClients() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
