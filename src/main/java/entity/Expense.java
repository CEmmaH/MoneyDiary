package entity;

import java.time.LocalDate;

public class Expense {
    private int id;
    private String name;
    private double amount;
    private LocalDate date;
    private String description;
    private int category_Id;
    private int account_Id;
    private String categorylName;
    private String accountName;
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
    	this.category_Id = categoryId;
    	this.account_Id = accountId;
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
        return category_Id;
    }

    public void setCategoryId(int categoryId) {
        this.category_Id = categoryId;
    }
    
    public void setCategoryName(String categoryName) {
    	this.categorylName = categoryName;
    }
    public String getCategoryName() {
    	return this.categorylName;
    }
    
    public void setAccountName(String accountName) {
    	this.accountName = accountName;
    }
    public String getAccountName() {
    	return accountName;
    }

    public int getAccountId() {
        return account_Id;
    }

    public void setAccountId(int accountId) {
        this.account_Id = accountId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
