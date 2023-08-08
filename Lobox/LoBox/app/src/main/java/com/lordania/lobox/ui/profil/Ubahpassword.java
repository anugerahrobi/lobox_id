package com.lordania.lobox.ui.profil;

import static android.text.TextUtils.isEmpty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lordania.lobox.MainActivity;
import com.lordania.lobox.R;
import com.lordania.lobox.ui.login.Login;
import com.lordania.lobox.ui.register.Register;
import com.lordania.lobox.ui.server.BaseResponse;
import com.lordania.lobox.ui.server.DataService;
import com.lordania.lobox.ui.server.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Ubahpassword extends AppCompatActivity {
    private TextView textView_ubahpss;
    private EditText editText_email;
    private EditText editText_ubahpss2;
    private Button button_submitubhpss;
    private DataService service;
    private static final String TAG = Register.class.getSimpleName();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubahpassword);
        initViews();
        initListener();
        service = ServiceGenerator.createBaseService(this, DataService.class);

    }
    private void initListener() {
        button_submitubhpss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editText_email.getText().toString();
                String passwordbaru = editText_ubahpss2.getText().toString();

                if(isEmpty(email))
                    editText_email.setError("Must not be empty");
                else if(isEmpty(passwordbaru))
                    editText_ubahpss2.setError("Must not be empty");
                else
                    addData(email,passwordbaru);
            }
        });
    }

    private void addData(String email, String passwordbaru) {

        Call<BaseResponse> call = service.apiUpdate_password(email,passwordbaru);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if(response.code() == 200 && response.body().isSuccess()==1) {
                    Toast.makeText(Ubahpassword.this, "Password Berhasil Berubah.", Toast.LENGTH_SHORT).show();
                    editText_email.setText("");
                    editText_ubahpss2.setText("");
                    startActivity(new Intent(Ubahpassword.this, Login.class));
                }
            }
            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.e(TAG + ".error", t.toString());
            }
        });
    }


    private void initViews() {
        textView_ubahpss =(TextView) findViewById( R.id.ubahpss);
        editText_email = (EditText) findViewById(R.id.email);
        editText_ubahpss2 = (EditText) findViewById(R.id.ubahpss2);
        button_submitubhpss =  (Button) findViewById(R.id.submitubhpss);
    }
}