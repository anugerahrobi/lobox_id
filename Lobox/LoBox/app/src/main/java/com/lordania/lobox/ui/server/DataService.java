package com.lordania.lobox.ui.server;

import com.lordania.lobox.ui.MODEL.M_CoinKeluar;
import com.lordania.lobox.ui.MODEL.M_CoinMasuk;
import com.lordania.lobox.ui.MODEL.M_login;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {
    @FormUrlEncoded
    @POST(Server.API_CREATE_LOGIN)
    Call<BaseResponse> apiCreateLogin(@Field("username") String username,
                                 @Field("password") String password,
                                 @Field("email") String email,
                                 @Field("nama") String nama,
                                 @Field("no_hp") String no_hp,
                                 @Field("no_ktp") String no_ktp,
                                 @Field("alamat") String alamat);

    @GET(Server.API_READ_LOGIN)
    Call<BaseResponse<List<M_login>>> apiReadLogin();

    @FormUrlEncoded
    @POST(Server.API_UPDATE_LOGIN)
    Call<BaseResponse> apiUpdateLogin(
            @Field("id_user") String id_user,
            @Field("username") String username,
            @Field("password") String password);

    @FormUrlEncoded
    @POST(Server.API_LOGIN)
    Call<BaseResponse<M_login>> apiLogin(
            @Field("username") String username,
            @Field("password") String password);

    @FormUrlEncoded
    @POST(Server.API_UPDATE_PASSWORD)
    Call<BaseResponse> apiUpdate_password(
            @Field("email") String email,
            @Field("password") String password);

    @FormUrlEncoded
    @POST(Server.API_DELETE_LOGIN)
    Call<BaseResponse> apiDeleteLogin(@Field("id_user") String id);

    //untuk coin masuk
    @FormUrlEncoded
    @POST(Server.API_CREATE_COIN_MASUK)
    Call<BaseResponse> apiCreateCoinMasuk(@Field("saldo") String saldo,
                                          @Field("berat_sampah") String berat_sampah,
                                          @Field("waktu") String waktu);

    @GET(Server.API_READ_COIN_MASUK)
    Call<BaseResponse<List<M_CoinMasuk>>> apiReadCoinMasuk();

    @FormUrlEncoded
    @POST(Server.API_UPDATE_COIN_MASUK)
    Call<BaseResponse> apiUpdateCoinMasuk(
            @Field("id_coin") String id_coin,
            @Field("id_user") String id_user,
            @Field("saldo") String saldo,
            @Field("berat_sampah") String berat_sampah,
            @Field("waktu") String waktu);

    @FormUrlEncoded
    @POST(Server.API_DELETE_COIN_MASUK)
    Call<BaseResponse> apiDeleteCoinMasuk(@Field("id_coin") String id);

    //coin keluar
    @FormUrlEncoded
    @POST(Server.API_CREATE_COIN_KELUAR)
    Call<BaseResponse> apiCreateCoinKeluar(@Field("saldo") String saldo,
                                          @Field("berat_sampah") String berat_sampah,
                                          @Field("waktu") String waktu);

    @GET(Server.API_READ_COIN_KELUAR)
    Call<BaseResponse<List<M_CoinKeluar>>> apiReadCoinKeluar();

    @FormUrlEncoded
    @POST(Server.API_UPDATE_COIN_KELUAR)
    Call<BaseResponse> apiUpdateCoinKeluar(
            @Field("id_coin") String id_coin,
            @Field("id_user") String id_user,
            @Field("saldo") String saldo,
            @Field("berat_sampah") String berat_sampah,
            @Field("waktu") String waktu);

    @FormUrlEncoded
    @POST(Server.API_DELETE_COIN_KELUAR)
    Call<BaseResponse> apiDeleteCoinKeluar(@Field("id_coin") String id);
}
