package com.aldi.project_uts.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aldi.project_uts.R;
import com.aldi.project_uts.models.Barang;


import java.util.List;


public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.ViewHolder> {

    public interface OnItemDataListener{
        void onDataClicked(int index, Barang item);
    }
    private List<Barang> items;
    private OnItemDataListener listener;

    public BarangAdapter(List<Barang> items, OnItemDataListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Barang item = items.get(position);
        holder.bind(position, item);
    }

    @Override
    public int getItemCount() {
        return (items != null) ? items.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namaBarang, jumlahBarang, typeBarang, statusBarang;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namaBarang = itemView.findViewById(R.id.item_name_text);
            jumlahBarang = itemView.findViewById(R.id.item_value_text);
            typeBarang = itemView.findViewById(R.id.item_type_text);
            statusBarang = itemView.findViewById(R.id.item_status_data_text);
        }
        public void bind(final int index, final Barang item){
            namaBarang.setText(item.getNama_Barang());
            jumlahBarang.setText(item.getJumlah());
            typeBarang.setText(typeValue(item));
            statusBarang.setText(statusValue(item));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onDataClicked(index, item);
                }
            });
        }
        private String typeValue(Barang item){
            String type = "";
            if(item.getSatuan() == Barang.SatuanBarang.LITER){
                type = "Liter";
            } else if (item.getSatuan()==Barang.SatuanBarang.PCS){
                type = "Pcs";
            } else if (item.getSatuan()==Barang.SatuanBarang.BOX){
                type = "Box";
            } else if (item.getSatuan()==Barang.SatuanBarang.KG){
                type = "Kg";
            }
            return type;
        }
        private String statusValue(Barang item){
            String status = "";
            if (item.getStatus() == Barang.Status.MASUK){
                status = "Barang Masuk";
            } else if(item.getStatus() == Barang.Status.KELUAR){
                status = "Keluar";
            }
            return status;
        }
    }
}
