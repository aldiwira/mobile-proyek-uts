package com.aldi.project_uts.models;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private List<Transaction> record;

    public Account() {
        this.record = new ArrayList<>();
    }

    public List<Transaction> getTransactions(){
        return record;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.record = transactions;
    }

    public void addTransactions(Transaction trans){
        this.record.add(trans);
    }
    public void deleteTransaction(int index){
        Transaction trans = record.get(index);
        this.record.remove(index);
    }
    public void update(int index, Transaction trans){
        this.record.set(index, trans);
    }
}
