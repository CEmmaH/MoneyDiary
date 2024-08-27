package entity;

import java.time.LocalDate;

public class Income {
    int id;
    String name;
    double amount;
    LocalDate date;
    String description;
    int userId;
    
    public Income() {
    	this("",0,LocalDate.now(),"");
    }
    public Income(String name, double amount, LocalDate date,String description) {
    	this.name = name;
    	this.amount = amount;
    	this.date = date;
    	this.description = description;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

