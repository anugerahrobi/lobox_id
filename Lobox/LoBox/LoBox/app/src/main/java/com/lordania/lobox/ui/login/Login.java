package com.lordania.lobox.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lordania.lobox.R;

public class Login extends AppCompatActivity {
    ImageView imageView_loginimg;
    ImageView imageView_phn1;
    ImageView imageView_phn2;
    ImageView imageView_phn3;
    TextView textView_login;
    TextView textView_lupa;
    TextView textView_regisbuat;
    Button button_login1;
    EditText editText_usrnamelogin;
    EditText editText_passwrdlogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}