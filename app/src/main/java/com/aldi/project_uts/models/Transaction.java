package com.aldi.project_uts.models;


public class Transaction{
    int sumBarangKeluar, sumBarangMasuk;
    String date, time;

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
}
