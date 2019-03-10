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
public class SimpleClients extends Clients {
    
    private int numbPurchasesLastMonth;

    public SimpleClients(int numbPurchasesLastMonth, String name, int credit) {
        super(name, credit);
        
        this.numbPurchasesLastMonth = numbPurchasesLastMonth;
    }
    
    public SimpleClients() {
    }

    public int getNumbPurchasesLastMonth() {
        return numbPurchasesLastMonth;
    }

    public void setNumbPurchasesLastMonth(int numbPurchasesLastMonth) {
        this.numbPurchasesLastMonth = numbPurchasesLastMonth;
    }
    
}
