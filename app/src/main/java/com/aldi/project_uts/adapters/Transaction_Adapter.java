package com.aldi.project_uts.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.aldi.project_uts.R;
import com.aldi.project_uts.models.Transaction;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

public class Transaction_Adapter extends AbstractItem<Transaction_Adapter, Transaction_Adapter.ViewHolder> {
    private String sumBarangKeluar, sumBarangMasuk;
    private String date, time;

    public Transaction_Adapter(String sumBarangKeluar, String sumBarangMasuk, String date, String time) {
        this.sumBarangKeluar = sumBarangKeluar;
        this.sumBarangMasuk = sumBarangMasuk;
        this.date = date;
        this.time = time;
    }

    @NonNull
    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    public int getType() {
        return R.id.rv_home;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_home;
    }

    public class ViewHolder extends FastAdapter.ViewHolder<Transaction_Adapter> {
        TextView timeText, dateText, inCount, outCount;
        public ViewHolder(View itemView) {
            super(itemView);
            timeText = itemView.findViewById(R.id.item_time_text);
            dateText = itemView.findViewById(R.id.item_date_text);
            inCount = itemView.findViewById(R.id.item_in_value_text);
            outCount = itemView.findViewById(R.id.item_out_value_text);
        }

        @Override
        public void bindView(Transaction_Adapter item, List<Object> payloads) {
            timeText.setText(item.time);
            dateText.setText(item.date);
            inCount.setText(item.sumBarangMasuk);
            outCount.setText(item.sumBarangKeluar);
        }

        @Override
        public void unbindView(Transaction_Adapter item) {
            timeText.setText(null);
            dateText.setText(null);
            inCount.setText(null);
            outCount.setText(null);
        }
    }
}
