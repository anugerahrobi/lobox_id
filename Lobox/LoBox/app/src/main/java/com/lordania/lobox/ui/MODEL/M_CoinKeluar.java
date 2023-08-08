package com.lordania.lobox.ui.MODEL;

import android.os.Parcel;
import android.os.Parcelable;
public class M_CoinKeluar implements Parcelable {
    String id_coin;
    String id_user;
    String saldo;
    String jenis_pembayaran;
    String waktu;


    public String getId_coin() {
        return id_coin;
    }

    public void setId_coin(String id_coin) {
        this.id_coin = id_coin;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getJenis_pembayaran() {
        return jenis_pembayaran;
    }

    public void setJenis_pembayaran(String jenis_pembayaran) {
        this.jenis_pembayaran = jenis_pembayaran;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    protected M_CoinKeluar(Parcel in) {
        id_coin = in.readString();
        id_user = in.readString();
        saldo = in.readString();
        jenis_pembayaran = in.readString();
        waktu = in.readString();



    }

    public static final Parcelable.Creator<M_CoinKeluar> CREATOR = new Parcelable.Creator<M_CoinKeluar>() {
        @Override
        public M_CoinKeluar createFromParcel(Parcel in) {
            return new M_CoinKeluar(in);
        }

        @Override
        public M_CoinKeluar[] newArray(int size) {
            return new M_CoinKeluar[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
        public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id_coin);
        dest.writeString(id_user);
        dest.writeString(saldo);
        dest.writeString(jenis_pembayaran);
        dest.writeString(waktu);

    };
}

