package com.aldi.project_uts.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.aldi.project_uts.R;
import com.aldi.project_uts.adapters.TransactionAdapter;
import com.aldi.project_uts.models.Account;
import com.aldi.project_uts.models.Record;
import com.aldi.project_uts.models.Transaction;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements TransactionAdapter.OnItemTransactionListener {
    public static final String TRANSACTION_KEY = "TRANSACTION";
    public static final String INDEX_KEY = "INDEX";
    public static final int INSERT_REQUEST = 1;
    public static final int UPDATE_REQUEST = 2;
    private TextView dateText;
    private String date;
    private String time;
    private Intent worldOfIntent;

    private TransactionAdapter transactionAdapter;
    private RecyclerView transactionsView;
    private Account account;
    private Record record;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupVar();
        setupLayout();
        setDateData();
        setupRV();
        setupDeleteRecord();
    }
    private void setupVar(){
        account = new Account();
        record = new Record(this);
        account.setTransactions(record.getTransaction());
    }
    private void setupLayout(){
        dateText = findViewById(R.id.date_text_home);
    }
    private void setDateData(){
        Calendar calendar = Calendar.getInstance();
        date = new SimpleDateFormat("EEEE, dd MMMM yyyy").format(calendar.getTime());
        time = new SimpleDateFormat("hh:mm a").format(calendar.getTime());
        dateText.setText(date);
    }

    private void setupRV(){
        transactionsView = findViewById(R.id.rv_home);
        transactionAdapter = new TransactionAdapter(account.getTransactions(),this);
        transactionsView.setAdapter(transactionAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        transactionsView.setLayoutManager(layoutManager);
    }
    private void setupDeleteRecord(){
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int index = viewHolder.getAdapterPosition();
                account.deleteTransaction(index);
                transactionAdapter.notifyDataSetChanged();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(transactionsView);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED){
            return;
        }
        if (resultCode == RESULT_OK){
            Transaction transaction = data.getParcelableExtra(TRANSACTION_KEY);
            if (requestCode == INSERT_REQUEST){
                account.addTransactions(transaction);
            } else if(requestCode == UPDATE_REQUEST){
                int index = data.getIntExtra(INDEX_KEY, 0);
                account.updateTransaction(index, transaction);
            }
            transactionAdapter.notifyDataSetChanged();
        }
    }
    public void handleAddTransaction(View view) {
        worldOfIntent = new Intent(MainActivity.this, Add_Data_Activity.class);
        worldOfIntent.putExtra(TRANSACTION_KEY, new Transaction());
        startActivityForResult(worldOfIntent, INSERT_REQUEST);
    }

    @Override
    public void onTransactionClicked(int index, Transaction item) {
        worldOfIntent = new Intent(this, Add_Data_Activity.class);
        worldOfIntent.putExtra(TRANSACTION_KEY, item);
        worldOfIntent.putExtra(INDEX_KEY, index);
        startActivityForResult(worldOfIntent, UPDATE_REQUEST);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        record.setTransaction(account.getTransactions());
    }

    public void handleSaveRecords(View view) {
        record.setTransaction(account.getTransactions());
    }
}
