package com.aldi.project_uts.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.aldi.project_uts.R;
import com.aldi.project_uts.adapters.Transaction_Adapter;
import com.aldi.project_uts.models.Transaction;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView dateText;
    String date;
    String time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupLayout();
        setDateData();
        setupRV();
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
        RecyclerView transactionView = findViewById(R.id.rv_home);
        ItemAdapter itemAdapter = new ItemAdapter<>();
        FastAdapter fastAdapter = FastAdapter.with(itemAdapter);
        List transaction = new ArrayList<>();
        transaction.add(new Transaction_Adapter("2", "2", date, time));
        transaction.add(new Transaction_Adapter("3", "3", date, time));
        itemAdapter.add(transaction);
        transactionView.setAdapter(fastAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        transactionView.setLayoutManager(layoutManager);
    }
}
