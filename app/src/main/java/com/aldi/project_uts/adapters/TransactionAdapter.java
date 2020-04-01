package com.aldi.project_uts.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aldi.project_uts.R;
import com.aldi.project_uts.models.Transaction;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    public interface OnItemTransactionListener{
        void onTransactionClicked(int index, Transaction item);
    }
    private List<Transaction> items;
    private OnItemTransactionListener listener;

    public TransactionAdapter(List<Transaction> items, OnItemTransactionListener listener) {
        this.items = items;
        this.listener = listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transaction item = items.get(position);
        holder.bind(position, item);
    }

    @Override
    public int getItemCount() {
        return (items != null) ? items.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView timeText, dateText, inCount, outCount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            timeText = itemView.findViewById(R.id.item_time_text);
            dateText = itemView.findViewById(R.id.item_date_text);
            inCount = itemView.findViewById(R.id.item_in_value_text);
            outCount = itemView.findViewById(R.id.item_out_value_text);
        }
        public void bind(final int index, final Transaction item){
            timeText.setText(item.getTime());
            dateText.setText(item.getDate());
            inCount.setText(item.getSumBarangMasuk());
            outCount.setText(item.getSumBarangKeluar());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onTransactionClicked(index, item);
                }
            });
        }
    }
}
