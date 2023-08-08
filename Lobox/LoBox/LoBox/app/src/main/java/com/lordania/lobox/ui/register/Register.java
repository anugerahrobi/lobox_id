package com.lordania.lobox.ui.register;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lordania.lobox.R;
public class Register extends AppCompatActivity {
    ImageView imageView_regis;
    ImageView imageView_phn4;
    ImageView imageView_phn5;
    ImageView imageView_phn6;
    TextView textView_daftar1;
    Button button_daftar;
    EditText editText_usrname;
    EditText editText_emaildaftar;
    EditText editText_pssword;
    EditText editText_replaypss;
    EditText editText_ntlpon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
}