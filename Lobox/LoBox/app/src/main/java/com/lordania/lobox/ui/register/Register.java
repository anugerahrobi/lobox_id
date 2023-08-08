package com.lordania.lobox.ui.register;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lordania.lobox.R;
import com.lordania.lobox.ui.login.Login;
import com.lordania.lobox.ui.server.BaseResponse;
import com.lordania.lobox.ui.server.DataService;
import com.lordania.lobox.ui.server.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.text.TextUtils.isEmpty;

public class Register extends AppCompatActivity {
    private static final String TAG = Register.class.getSimpleName();
    private ImageView imageView_regis;
    private ImageView imageView_phn4;
    private ImageView imageView_phn5;
    private ImageView imageView_phn6;
    private TextView textView_daftar1;
    private Button button_daftar;
    private DataService service;
    private EditText et_username;
    private EditText et_email;
    private EditText et_password;
    private EditText et_nama;
    private EditText et_no_hp;
    private EditText et_no_ktp;
    private EditText et_alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
        initListener();
        service = ServiceGenerator.createBaseService(this, DataService.class);
    }

    private void initListener() {
        button_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString();
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();
                String nama = et_nama.getText().toString();
                String no_hp = et_no_hp.getText().toString();
                String alamat = et_alamat.getText().toString();
                String no_ktp = et_no_ktp.getText().toString();

                if(isEmpty(username))
                    et_username.setError("Must not be empty");
                else if(isEmpty(password))
                    et_password.setError("Must not be empty");
                else if(isEmpty(email))
                    et_email.setError("Must not be empty");
                else if(isEmpty(nama))
                    et_nama.setError("Must not be empty");
                else if(isEmpty(no_hp))
                    et_no_hp.setError("Must not be empty");
                else if(isEmpty(no_ktp))
                    et_no_ktp.setError("Must not be empty");
                else if(isEmpty(alamat))
                    et_alamat.setError("Must not be empty");
                else
                    addData(username,password,email,nama,no_hp,no_ktp,alamat);
            }
        });
    }
    private void addData(String username, String password, String email, String nama, String no_hp, String no_ktp, String alamat) {
        Call<BaseResponse> call = service.apiCreateLogin(username,password,email,nama,no_hp,no_ktp,alamat);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if(response.code() == 200) {
                    Toast.makeText(Register.this, "Success", Toast.LENGTH_SHORT).show();
                    et_username.setText("");
                    et_password.setText("");
                    et_email.setText("");
                    et_nama.setText("");
                    et_no_hp.setText("");
                    et_no_ktp.setText("");
                    et_alamat.setText("");
                    startActivity(new Intent(Register.this, Login.class));
                }
            }
            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                int e = Log.e(TAG + ".error", t.toString());
            }
        });
    }

    private void initViews() {
        imageView_regis = (ImageView) findViewById(R.id.regis);
        imageView_phn4 = (ImageView) findViewById(R.id.phn4);
        imageView_phn5 = (ImageView) findViewById(R.id.phn5);
        imageView_phn6 = (ImageView) findViewById(R.id.phn6);
        textView_daftar1 = (TextView) findViewById(R.id.daftar1);
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        et_email = (EditText) findViewById(R.id.et_email);
        et_nama = (EditText) findViewById(R.id.et_nama);
        et_no_hp = (EditText) findViewById(R.id.et_no_hp);
        et_no_ktp = (EditText) findViewById(R.id.et_no_ktp);
        et_alamat = (EditText) findViewById(R.id.et_alamat);
        button_daftar = (Button) findViewById(R.id.daftar);

    }
}
