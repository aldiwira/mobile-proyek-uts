package com.aldi.project_uts.models;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Transaction implements Parcelable {
    private int sumBarangKeluar, sumBarangMasuk;
    private String date, time;
    private List<Barang> items;

    public Transaction() {
        this.items = new ArrayList<>();
    }

    public Transaction(int sumBarangKeluar, int sumBarangMasuk, String date, String time, List<Barang> items) {
        this.sumBarangKeluar = sumBarangKeluar;
        this.sumBarangMasuk = sumBarangMasuk;
        this.date = date;
        this.time = time;
        this.items = items;
    }

    public int getSumBarangKeluar() {
        return sumBarangKeluar;
    }

    public void setSumBarangKeluar(int sumBarangKeluar) {
        this.sumBarangKeluar = sumBarangKeluar;
    }

    public int getSumBarangMasuk() {
        return sumBarangMasuk;
    }

    public void setSumBarangMasuk(int sumBarangMasuk) {
        this.sumBarangMasuk = sumBarangMasuk;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<Barang> getItems() {
        return items;
    }

    public void setItems(List<Barang> items) {
        this.items = items;
    }

    public void addBarang(Barang barang){
        this.items.add(barang);
    }
    public void updateBarang(Barang barang, int index){
        this.items.set(index, barang);
    }
    public void deleteBarang(int index){
        Barang barang = this.items.get(index);
        this.items.remove(barang);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.sumBarangKeluar);
        dest.writeInt(this.sumBarangMasuk);
        dest.writeString(this.date);
        dest.writeString(this.time);
        dest.writeTypedList(this.items);
    }

    protected Transaction(Parcel in) {
        this.sumBarangKeluar = in.readInt();
        this.sumBarangMasuk = in.readInt();
        this.date = in.readString();
        this.time = in.readString();
        this.items = in.createTypedArrayList(Barang.CREATOR);
    }

    public static final Creator<Transaction> CREATOR = new Creator<Transaction>() {
        @Override
        public Transaction createFromParcel(Parcel source) {
            return new Transaction(source);
        }

        @Override
        public Transaction[] newArray(int size) {
            return new Transaction[size];
        }
    };
}
