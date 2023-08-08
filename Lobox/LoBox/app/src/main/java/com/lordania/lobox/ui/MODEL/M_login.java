package com.lordania.lobox.ui.MODEL;

import android.os.Parcel;
import android.os.Parcelable;

public class M_login implements Parcelable {
    String id_user;
    String username;
    String password;
    String email;
    String nama;
    String no_hp;
    String no_ktp;
    String alamat;

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getNo_ktp() {
        return no_ktp;
    }

    public void setNo_ktp(String no_ktp) {
        this.no_ktp = no_ktp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    protected M_login(Parcel in) {
        id_user = in.readString();
        username = in.readString();
        password = in.readString();
        email = in.readString();
        nama = in.readString();
        no_hp = in.readString();
        no_ktp = in.readString();
        alamat = in.readString();
    }

    public static final Creator<M_login> CREATOR = new Creator<M_login>() {
        @Override
        public M_login createFromParcel(Parcel in) {
            return new M_login(in);
        }

        @Override
        public M_login[] newArray(int size) {
            return new M_login[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id_user);
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(email);
        dest.writeString(nama);
        dest.writeString(no_hp);
        dest.writeString(no_ktp);
        dest.writeString(alamat);
    }
}
