/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Movie;

/**
 *
 * @author benja
 */
public class MovieDTO {
    
        
    private int year;
    private String name;
    private String[] actors;

    public MovieDTO(Movie m) {
    
    this.year = m.getYear();
    this.name = m.getName();
    this.actors = m.getActors();
        
    
    }
    
    
    
}



//    public CustomerDTO(BankCustomer bc) {
//        this.customerID = bc.getId().intValue();
//        this.fullName = bc.getFirstName() + " " + bc.getLastName();
//        this.accountNumber = bc.getAccountNumber();
//        this.balance = bc.getBalance();
//    }