package com.aldi.project_uts.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Record {
    public static final String TRANSACTION_KEY = "transaction";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Gson gson;
    List<Transaction> tList ;

    public Record(Context context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
        gson = new Gson();
    }
    public void setTransaction(List<Transaction> transaction){
        this.tList = transaction;
        String json = gson.toJson(transaction);
        editor.putString(TRANSACTION_KEY, json);
        editor.apply();
    }
    public List<Transaction> getTransaction(){
        String json = sharedPreferences.getString(TRANSACTION_KEY, null);
        Type type = new TypeToken<ArrayList<Transaction>>(){}.getType();
        tList = gson.fromJson(json, type);
        if (tList == null){
            tList = new ArrayList<>();
        }
        return tList;
    }
}
