package com.aldi.project_uts.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.aldi.project_uts.R;
import com.aldi.project_uts.models.Transaction;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Add_Data_Activity extends AppCompatActivity {
    private String date;
    private String time;
    private int index;

    private Transaction record;
    private Intent intent;

    private TextView dateText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        record = new Transaction();
        bindSetup();
        setDateData();
    }
    private void bindSetup(){
        dateText = findViewById(R.id.header_date_text);
    }
    private void setDateData(){
        Calendar calendar = Calendar.getInstance();
        date = new SimpleDateFormat("EEEE, dd MMMM yyyy").format(calendar.getTime());
        time = new SimpleDateFormat("hh:mm a").format(calendar.getTime());
        dateText.setText(date);
    }
    private void getParcelableData(){
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            index = extras.getInt(MainActivity.INDEX_KEY, 0);
            record = extras.getParcelable(MainActivity.TRANSACTION_KEY);
            dateText.setText(record.getDate());
        }
    }
    public void handleSaveRecord(View view) {
        int in_data = 2;
        int out_data = 2;
        record.setSumBarangMasuk(2);
        record.setSumBarangKeluar(2);
        record.setDate(date);
        record.setTime(time);
        intent = new Intent();
        intent.putExtra(MainActivity.TRANSACTION_KEY, record);
        intent.putExtra(MainActivity.INDEX_KEY, index);
        setResult(RESULT_OK, intent);
        finish();
    }
}
