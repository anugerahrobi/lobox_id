package com.lordania.lobox;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.lordania.lobox.ui.MODEL.M_login;


public class Preferences {
    /** Pendeklarasian key-data berupa String, untuk sebagai wadah penyimpanan data.
     * Jadi setiap data mempunyai key yang berbeda satu sama lain */
    static final String KEY_USERNAME ="username", KEY_PASS ="password";
    static final String KEY_ID_USER ="id_user";
    static final String KEY_EMAIL= "email";
    static final String KEY_NAMA = "nama";
    static final String KEY_NO_HP = "no_hp";
    static final String KEY_NO_KTP = "no_ktp";
    static final String KEY_ALAMAT = "alamat";

    static final String KEY_STATUS_SEDANG_LOGIN = "Status_logged_in";

    /** Pendlakarasian Shared Preferences yang berdasarkan paramater context */
    private static SharedPreferences getSharedPreference(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /** Deklarasi Edit Preferences dan mengubah data
     *  yang memiliki key isi KEY_USER_TEREGISTER dengan parameter username */

    public static void setLogin(Context context, M_login m){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_ID_USER, m.getId_user());
        editor.putString(KEY_USERNAME, m.getUsername());
        editor.putString(KEY_PASS, m.getPassword());
        editor.putString(KEY_NAMA, m.getNama());
        editor.putString(KEY_EMAIL, m.getEmail());
        editor.putString(KEY_NO_HP, m.getNo_hp());
        editor.putString(KEY_NO_KTP, m.getNo_ktp());
        editor.putString(KEY_ALAMAT, m.getAlamat());
//        editor.putString(KEY_PASS_TEREGISTER, m.get());
        editor.apply();
    }
    public static void setRegisteredUser(Context context, String username){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_USERNAME, username);
        editor.apply();
    }
    /** Mengembalikan nilai dari key KEY_USER_TEREGISTER berupa String */
    public static String getRegisteredUser(Context context){
        return getSharedPreference(context).getString(KEY_USERNAME,"");
    }

    /** Deklarasi Edit Preferences dan mengubah data
     *  yang memiliki key KEY_PASS_TEREGISTER dengan parameter password */
    public static void setRegisteredPass(Context context, String password){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_PASS, password);
        editor.apply();
    }
    /** Mengembalikan nilai dari key KEY_PASS_TEREGISTER berupa String */
    public static String getRegisteredPass(Context context){
        return getSharedPreference(context).getString(KEY_PASS,"");
    }

    /** Deklarasi Edit Preferences dan mengubah data
     *  yang memiliki key KEY_USERNAME_SEDANG_LOGIN dengan parameter username */
    public static void setLoggedInUser(Context context, String username){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_USERNAME, username);
        editor.apply();
    }
    /** Mengembalikan nilai dari key KEY_USERNAME_SEDANG_LOGIN berupa String */
    public static String getLoggedInUser(Context context){
        return getSharedPreference(context).getString(KEY_USERNAME,"");
    }

    /** Deklarasi Edit Preferences dan mengubah data
     *  yang memiliki key KEY_STATUS_SEDANG_LOGIN dengan parameter status */
    public static void setLoggedInStatus(Context context, boolean status){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putBoolean(KEY_STATUS_SEDANG_LOGIN,status);
        editor.apply();
    }
    /** Mengembalikan nilai dari key KEY_STATUS_SEDANG_LOGIN berupa boolean */
    public static boolean getLoggedInStatus(Context context){
        return getSharedPreference(context).getBoolean(KEY_STATUS_SEDANG_LOGIN,false);
    }

    /** Deklarasi Edit Preferences dan menghapus data, sehingga menjadikannya bernilai default
     *  khusus data yang memiliki key KEY_USERNAME_SEDANG_LOGIN dan KEY_STATUS_SEDANG_LOGIN */
    public static void clearLoggedInUser (Context context){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.remove(KEY_USERNAME);
        editor.remove(KEY_STATUS_SEDANG_LOGIN);
        editor.apply();
    }
}