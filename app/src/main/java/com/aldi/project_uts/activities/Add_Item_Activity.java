package com.aldi.project_uts.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.aldi.project_uts.R;
import com.aldi.project_uts.models.Barang;

public class Add_Item_Activity extends AppCompatActivity {
    private EditText namaBarang, valueBarang;
    private RadioGroup statusBarang, typeBarang;
    private Barang barang;
    private int index;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        setupLayout();
        setupParcelable();
    }
    private void setupLayout(){
        namaBarang = findViewById(R.id.data_nama_barang_input);
        valueBarang = findViewById(R.id.data_value_barang_input);
        statusBarang = findViewById(R.id.radio_group_status_item);
        typeBarang = findViewById(R.id.radio_group_type_item);
    }
    private void setupParcelable(){
        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            barang = extras.getParcelable(Add_Data_Activity.BARANG_KEY);
            index = extras.getInt(Add_Data_Activity.INDEX_KEY, 0);
            namaBarang.setText(barang.getNama_Barang());
            valueBarang.setText(String.valueOf(barang.getJumlah()));
            getTypeBarang(barang);
            getStatus(barang);
        }
    }
    private void getTypeBarang(Barang barang){
        if (barang.getSatuan() == Barang.SatuanBarang.LITER){
            typeBarang.check(R.id.radio_type_liter);
        } else if (barang.getSatuan() == Barang.SatuanBarang.KG){
            typeBarang.check(R.id.radio_type_kg);
        } else if (barang.getSatuan()== Barang.SatuanBarang.BOX){
            typeBarang.check(R.id.radio_type_box);
        } else if (barang.getSatuan()== Barang.SatuanBarang.PCS){
            typeBarang.check(R.id.radio_type_buah);
        }
    }
    private void getStatus(Barang barang){
        if (barang.getStatus() == Barang.Status.MASUK){
            statusBarang.check(R.id.radio_input_box);
        } else if(barang.getStatus() == Barang.Status.KELUAR){
            statusBarang.check(R.id.radio_output_box);
        }
    }
    private Barang.SatuanBarang getTypeBarang(){
        if (typeBarang.getCheckedRadioButtonId() == R.id.radio_type_liter){
            return Barang.SatuanBarang.LITER;
        } else if (typeBarang.getCheckedRadioButtonId() == R.id.radio_type_kg){
            return Barang.SatuanBarang.KG;
        } else if (typeBarang.getCheckedRadioButtonId() == R.id.radio_type_box){
            return Barang.SatuanBarang.BOX;
        } else if (typeBarang.getCheckedRadioButtonId() == R.id.radio_type_buah){
            return Barang.SatuanBarang.PCS;
        }
        return Barang.SatuanBarang.EMPTY;
    }
    private Barang.Status getStatus(){
        if (statusBarang.getCheckedRadioButtonId() == R.id.radio_input_box){
            return Barang.Status.MASUK;
        } else if (statusBarang.getCheckedRadioButtonId() == R.id.radio_output_box){
            return Barang.Status.KELUAR;
        }
        return Barang.Status.EMPTY;
    }


    public void handleAddData(View view) {
        barang.setNama_Barang(namaBarang.getText().toString());
        barang.setJumlah(Integer.parseInt(valueBarang.getText().toString()));
        barang.setSatuan(getTypeBarang());
        barang.setStatus(getStatus());
        intent = new Intent();
        intent.putExtra(Add_Data_Activity.BARANG_KEY, barang);
        intent.putExtra(Add_Data_Activity.INDEX_KEY, index);
        setResult(RESULT_OK, intent);
        finish();
    }
}
