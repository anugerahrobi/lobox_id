package com.lordania.lobox.ui.login;

import static android.text.TextUtils.isEmpty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lordania.lobox.MainActivity;
import com.lordania.lobox.R;
import com.lordania.lobox.ui.MODEL.M_login;
import com.lordania.lobox.ui.home.HomeFragment;
import com.lordania.lobox.ui.profil.Ubahpassword;
import com.lordania.lobox.ui.register.Register;
import com.lordania.lobox.ui.server.BaseResponse;
import com.lordania.lobox.ui.server.DataService;
import com.lordania.lobox.ui.server.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    private static final String TAG = Register.class.getSimpleName();
    private ImageView imageView_loginimg;
    private ImageView imageView_phn1;
    private ImageView imageView_phn2;
    private ImageView imageView_phn3;
    private TextView textView_login;
    private  DataService service;
    TextView textView_lupa;
    TextView textView_regisbuat;
    private  Button button_login1;
    private EditText editText_usernamelogin;
    private EditText editText_passwordlogin;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedPreferenceseditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initListener();
        service = ServiceGenerator.createBaseService(this, DataService.class);
        textView_regisbuat = findViewById(R.id.regisbuat);
        textView_regisbuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });
        textView_lupa = findViewById(R.id.lupa);
        textView_lupa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Ubahpassword.class));
            }
        });

    }




    private void initListener() {
        button_login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editText_usernamelogin.getText().toString();
                String password = editText_passwordlogin.getText().toString();

                if(isEmpty(username))
                    editText_usernamelogin.setError("Must not be empty");
                else if(isEmpty(password))
                    editText_passwordlogin.setError("Must not be empty");
                else
                    addData(username,password);
            }
        });
    }

    private void addData(String username, String password) {

        Call<BaseResponse<M_login>> call = service.apiLogin(username,password);
        call.enqueue(new Callback<BaseResponse<M_login>>() {
            @Override
            public void onResponse(Call<BaseResponse<M_login>> call, Response<BaseResponse<M_login>> response) {
                if(response.code() == 200 && response.body().isSuccess()==1) {
                    M_login pengguna = response.body().getData();

                    sharedPreferenceseditor.putBoolean("login", true);
                    sharedPreferenceseditor.putString("id_user", pengguna.getId_user());
                    sharedPreferenceseditor.putString("username", pengguna.getUsername());
                    sharedPreferenceseditor.putString("password", pengguna.getPassword());
                    sharedPreferenceseditor.putString("email", pengguna.getEmail());
                    sharedPreferenceseditor.putString("nama", pengguna.getNama());
                    sharedPreferenceseditor.putString("no_hp", pengguna.getNo_hp());
                    sharedPreferenceseditor.putString("no_ktp", pengguna.getNo_ktp());
                    sharedPreferenceseditor.putString("alamat", pengguna.getAlamat());
                    sharedPreferenceseditor.apply();

                    Toast.makeText(Login.this, "Success", Toast.LENGTH_SHORT).show();
                    editText_usernamelogin.setText("");
                    editText_passwordlogin.setText("");
                    startActivity(new Intent(Login.this, MainActivity.class));
                }
            }
            @Override
            public void onFailure(Call<BaseResponse<M_login>> call, Throwable t) {
                Toast.makeText(Login.this, t.toString(), Toast.LENGTH_SHORT).show();
                Log.e(TAG + ".error", t.toString());
            }
        });
    }

    private void initView() {
        imageView_loginimg = (ImageView) findViewById(R.id.loginimg);
        imageView_phn1 = (ImageView) findViewById(R.id.phn1);
        imageView_phn2 = (ImageView) findViewById(R.id.phn2);
        imageView_phn3 = (ImageView) findViewById(R.id.phn3);
        textView_login = (TextView) findViewById(R.id.login);
        button_login1 = (Button) findViewById(R.id.login1);
        editText_usernamelogin = (EditText) findViewById(R.id.usernamelogin);
        editText_passwordlogin = (EditText) findViewById(R.id.passwordlogin);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Login.this);
        sharedPreferenceseditor = sharedPreferences.edit();

    }
}