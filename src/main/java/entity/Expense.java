package entity;

import java.time.LocalDate;
import java.util.Date;

public class Expense {
    private int id;
    private String name;
    private double amount;
    private LocalDate date;
    private String description;
    private int categoryId;
    private int accountId;
    private int userId;

    public Expense() {
    	name = "";
    	amount = 0;
    	date = LocalDate.now();
    	description = "";
    }
    public Expense(String name, double amount, LocalDate date, int categoryId, int accountId, int userId,String description) {
    	this.name = name;
    	this.amount = amount;
    	this.date = date;
    	this.categoryId = categoryId;
    	this.accountId = accountId;
    	this.userId = userId;
    	this.description = description;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
