package com.aldi.project_uts.models;


import android.os.Parcel;
import android.os.Parcelable;

public class Transaction implements Parcelable {
    int sumBarangKeluar, sumBarangMasuk;
    String date, time;

    public Transaction() {
    }

    public Transaction(int sumBarangKeluar, int sumBarangMasuk, String date, String time) {
        this.sumBarangKeluar = sumBarangKeluar;
        this.sumBarangMasuk = sumBarangMasuk;
        this.date = date;
        this.time = time;
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
    }

    protected Transaction(Parcel in) {
        this.sumBarangKeluar = in.readInt();
        this.sumBarangMasuk = in.readInt();
        this.date = in.readString();
        this.time = in.readString();
    }

    public static final Parcelable.Creator<Transaction> CREATOR = new Parcelable.Creator<Transaction>() {
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
