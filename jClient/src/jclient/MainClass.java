/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jclient;

import beans.FrequentsClients;
import beans.SimpleClients;

import java.util.ArrayList;
import java.util.Scanner;

import services.SimpleClientsService;
import services.FrequentsClientsService;

/**
 *
 * @author noemy
 */
public class MainClass {

    /**
     * @param args the command line arguments
     *
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner input = new Scanner(System.in);
        Scanner string = new Scanner(System.in);
        // Objects
        FrequentsClients fc = new FrequentsClients();
        SimpleClients sc = new SimpleClients();
        // Lists
        ArrayList<FrequentsClients> fcList = new ArrayList();
        ArrayList<SimpleClients> scList = new ArrayList();
        // Services List
        FrequentsClientsService fcService = new FrequentsClientsService();
        SimpleClientsService scService = new SimpleClientsService();
        
        do {
        
            System.out.println("-------------------------------------");
            System.out.println("Enter the number of the desired option: ");
            System.out.println("1. Register a new simple client; ");
            System.out.println("2. Register a new frequent client; ");
            System.out.println("3. Increase some's client credit; ");
            System.out.println("4. Decrease some's client credit; ");
            System.out.println("5. Check out all clients registered; ");
            System.out.println("6. Exit. ");
            System.out.println("-------------------------------------");
            
            int option = input.nextInt();
            
            switch(option) {
                
                case 1:
                    
                    System.out.println("Enter the client's name: ");
                    String scName = string.next();
                    
                    System.out.println("Enter the client's credit: ");
                    int scCredit = input.nextInt();
                    
                    System.out.println("Enter the number of the purchases last "
                                                   + "month made for client: ");
                    int numbPurchasesLastMonth = input.nextInt();
                    
                    sc.setName(scName);
                    sc.setNumbPurchasesLastMonth(numbPurchasesLastMonth);
                    sc.addCredit(scCredit);
                    
                    scList.add(sc);
                    scService.insert(sc);
                    
                    break;
                
                case 2:
                    
                    System.out.println("Enter the client's name: ");
                    String fcName = string.next();
                    
                    System.out.println("Enter the client's credit: ");
                    int fcCredit = input.nextInt();
                    
                    System.out.println("Enter the number: ");
                    int number = input.nextInt();
                    
                    fc.setName(fcName);
                    fc.setNumber(number);
                    fc.addCredit(fcCredit);
                    
                    fcList.add(fc);
                    fcService.insert(fc);
                    
                    break;
                
                case 3:

                    System.out.println("Enter the client's name: ");
                    String increaseCName = string.next();
                    
                    scList = scService.getAll();
                    fcList = fcService.getAll();
                    
                    boolean a;
                    
                    a = (scList.size() >= fcList.size());
                    
                    if (a == true) {
                        
                        for(int i = 0; i < scList.size(); i++) {
                            
                            if(scList.get(i).getName().equals(increaseCName)) {

                            System.out.println("Enter the value: ");
                            int value = input.nextInt();
                            // verifica se o valor é maior que o dobro do atual
                            if(value <= scList.get(i).getCredit()){
                                //adicionando crédito pelo beans
                                scList.get(i).addCredit(value); 
                                // adicionando crédito no banco de dados
                                scService.addingCredit(value, 
                                                       scList.get(i).getName());
                            } else {
                                System.err.println("Credit can't be more than the double's atual."); 
                            } 
                            // fim da verificação
                            System.out.println("Total value: "
                                                    +scList.get(i).getCredit());
                            }
                                                    
                        }
                        
                    } else {
                        
                        for(int i = 0; i < fcList.size(); i++) {
                            
                            if(fcList.get(i).getName().equals(increaseCName)) {

                            System.out.println("Enter the value: ");
                            int value = input.nextInt();
                            // verifica se o valor é maior que o dobro do atual
                            if(value <= fcList.get(i).getCredit()){
                                // adicionando crédito pelo beans
                                fcList.get(i).addCredit(value); 
                                // adicionando crédito no banco de dados
                                fcService.addingCredit(value, 
                                                       fcList.get(i).getName());   
                            } else {
                                System.err.println("Credit can't be more than the double's atual."); 
                            }
                            // fim da verificação
                            System.out.println("Total value: "
                                                    +fcList.get(i).getCredit());
                            
                        }
  
                    }

                }
                
                break;
                    
                case 4:
                    
                    System.out.println("Enter the client's name: ");
                    String decreaseCName = string.next();
                    
                    // recebem a lista de clientes cadastrados no banco de dados
                    scList = scService.getAll();
                    fcList = fcService.getAll();
                    
                    boolean b;
                    // recebe true ou false
                    b = (scList.size() >= fcList.size());
                    // caso em que b é true
                    if(b == true) {
                        
                        for(int i = 0; i < scList.size(); i++) {
                            
                            if(scList.get(i).getName().equals(decreaseCName)) {

                            System.out.println("Enter the value: ");
                            int value = input.nextInt();
                            // verificando se o valor resultará no crédito negativo
                            if(value <= scList.get(i).getCredit()) {
                                // removendo crédito pelo beans
                                scList.get(i).removeCredit(value);
                                // removendo crédito no banco de dados
                                scService.removingCredit(value, 
                                                           scList.get(i).getName());
                            } else {
                                System.err.println("Credit can't be negative.");
                            }
                            //fim da verificação
                            // imprimindo valor modificado
                            System.out.println("Total value: "
                                                    +scList.get(i).getCredit());
                            }
                            
                        }
                        // caso em que b é false
                    } else {
                        
                        for(int i = 0; i < fcList.size(); i++) {
                            
                            if(fcList.get(i).getName().equals(decreaseCName)) {

                            System.out.println("Enter the value: ");
                            int value = input.nextInt();
                            // verificando se o valor resultará no crédito negativo
                            if(value <= fcList.get(i).getCredit()) {
                                // removendo crédito pelo beans
                                fcList.get(i).removeCredit(value);
                                // removendo crédito no banco de dados
                                fcService.removingCredit(value, 
                                                           fcList.get(i).getName());
                            } else {
                                System.err.println("Credit can't be negative.");
                            }
                            // fim da verificação
                            System.out.println("Total value: "
                                                    +fcList.get(i).getCredit());

                            }
                            
                        }  
                        
                    }
                    
                    break;
                    
                case 5:
                    
                    System.out.println("\n");
                    System.out.println("Simple clients: ");
                    System.out.println("----------------");
                    scService.listAll();

                    System.out.println("Frequent clients: ");
                    System.out.println("------------------");
                    fcService.listAll();
                    
                    break;
                    
                case 6:
                    
                    sc.close();
                    fc.close();
                    input.close();
                    
                    break;
                 
                default: 
                    System.err.println("Enter a valid value.");
            }
 
        } while (true);
 
    }
    
}
