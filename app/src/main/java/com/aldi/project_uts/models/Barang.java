package com.aldi.project_uts.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Barang implements Parcelable {
    public enum SatuanBarang{
        PCS, LITER, KG, BOX
    }
    public enum Status{
        MASUK, KELUAR
    }
    private String nama_Barang;
    private int jumlah;
    private SatuanBarang satuan;
    private Status status;

    public Barang() {

    }

    public Barang(String nama_Barang, int jumlah, SatuanBarang satuan, Status status) {
        this.nama_Barang = nama_Barang;
        this.jumlah = jumlah;
        this.satuan = satuan;
        this.status = status;
    }

    public String getNama_Barang() {
        return nama_Barang;
    }

    public void setNama_Barang(String nama_Barang) {
        this.nama_Barang = nama_Barang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public SatuanBarang getSatuan() {
        return satuan;
    }

    public void setSatuan(SatuanBarang satuan) {
        this.satuan = satuan;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nama_Barang);
        dest.writeInt(this.jumlah);
        dest.writeInt(this.satuan == null ? -1 : this.satuan.ordinal());
        dest.writeInt(this.status == null ? -1 : this.status.ordinal());
    }

    protected Barang(Parcel in) {
        this.nama_Barang = in.readString();
        this.jumlah = in.readInt();
        int tmpSatuan = in.readInt();
        this.satuan = tmpSatuan == -1 ? null : SatuanBarang.values()[tmpSatuan];
        int tmpStatus = in.readInt();
        this.status = tmpStatus == -1 ? null : Status.values()[tmpStatus];
    }

    public static final Creator<Barang> CREATOR = new Creator<Barang>() {
        @Override
        public Barang createFromParcel(Parcel source) {
            return new Barang(source);
        }

        @Override
        public Barang[] newArray(int size) {
            return new Barang[size];
        }
    };
}
