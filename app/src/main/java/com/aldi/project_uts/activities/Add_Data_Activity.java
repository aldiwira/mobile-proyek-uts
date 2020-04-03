package com.aldi.project_uts.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.aldi.project_uts.R;
import com.aldi.project_uts.adapters.BarangAdapter;
import com.aldi.project_uts.models.Barang;
import com.aldi.project_uts.models.Transaction;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Add_Data_Activity extends AppCompatActivity implements BarangAdapter.OnItemDataListener {
    public static final String BARANG_KEY = "BARANG";
    public static final String INDEX_KEY = "INDEX";
    public static final int INSERT_REQUEST = 1;
    public static final int UPDATE_REQUEST = 2;

    private String date;
    private String time;
    private int index;
    private int sum_in_item = 0;
    private int sum_out_item = 0;

    private RecyclerView recordView;
    private BarangAdapter adapter;
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
        getParcelableData();
        setupRV();
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
    private void setupRV(){
        recordView = findViewById(R.id.rv_data);
        adapter = new BarangAdapter(record.getItems(), this);
        recordView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recordView.setLayoutManager(layoutManager);
    }
    public void handleSaveRecord(View view) {

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

    @Override
    public void onDataClicked(int index, Barang item) {
        intent = new Intent(this, Add_Item_Activity.class);
        intent.putExtra(BARANG_KEY, item);
        intent.putExtra(INDEX_KEY, index);
        startActivityForResult(intent, UPDATE_REQUEST);
    }

    public void handleAddBarang(View view) {
        intent = new Intent(Add_Data_Activity.this, Add_Item_Activity.class);
        intent.putExtra(BARANG_KEY, new Barang());
        startActivityForResult(intent, INSERT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_OK){
            Barang barang = data.getParcelableExtra(BARANG_KEY);
            if (barang.getStatus() == Barang.Status.MASUK){
                sum_in_item += 1;
            } else if(barang.getStatus() == Barang.Status.KELUAR){
                sum_out_item += 1;
            }
            if (requestCode == INSERT_REQUEST ){
                record.addBarang(barang);
            } else if(requestCode == UPDATE_REQUEST){
                int index = data.getIntExtra(INDEX_KEY, 0);
                record.updateBarang(barang, index);
            }
            adapter.notifyDataSetChanged();
        }
    }
}
