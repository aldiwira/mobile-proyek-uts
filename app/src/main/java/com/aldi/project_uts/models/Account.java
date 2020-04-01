package com.aldi.project_uts.models;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private List<Transaction> transactions;

    public Account() {
        this.transactions = new ArrayList<>();
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addTransactions(Transaction trans){
        this.transactions.add(trans);
    }
    public void deleteTransaction(int index){
        Transaction trans = transactions.get(index);
        this.transactions.remove(index);
    }
    public void update(int index, Transaction trans){
        this.transactions.set(index, trans);
    }
}
