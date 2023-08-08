package com.lordania.lobox.ui.MODEL;

import android.os.Parcel;
import android.os.Parcelable;
public class M_CoinMasuk implements Parcelable {
    String id_coin;
    String id_user;
    String saldo;
    String berat_sampah;
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

    public String getBerat_sampah() {
        return berat_sampah;
    }

    public void setBerat_sampah(String berat_sampah) {
        this.berat_sampah = berat_sampah;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    protected M_CoinMasuk(Parcel in) {
        id_coin = in.readString();
        id_user = in.readString();
        saldo = in.readString();
        berat_sampah = in.readString();
        waktu = in.readString();



    }

    public static final Parcelable.Creator<M_CoinMasuk> CREATOR = new Parcelable.Creator<M_CoinMasuk>() {
        @Override
        public M_CoinMasuk createFromParcel(Parcel in) {
            return new M_CoinMasuk(in);
        }

        @Override
        public M_CoinMasuk[] newArray(int size) {
            return new M_CoinMasuk[size];
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
        dest.writeString(berat_sampah);
        dest.writeString(waktu);

    };
}
