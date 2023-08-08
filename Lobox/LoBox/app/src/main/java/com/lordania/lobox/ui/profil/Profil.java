package com.lordania.lobox.ui.profil;

import static android.text.TextUtils.isEmpty;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lordania.lobox.ui.login.Login;
import com.lordania.lobox.ui.profil.Ubahpassword;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.lordania.lobox.R;
import com.lordania.lobox.ui.register.Register;
import com.lordania.lobox.ui.server.BaseResponse;
import com.lordania.lobox.ui.server.DataService;
import com.lordania.lobox.ui.server.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class Profil extends Fragment {
    private static final String TAG = Register.class.getSimpleName();
    ImageView imageView_profil;
    TextView textView_name;
    EditText editText_namaprofil;
    TextView textView_email;
    EditText editText_email1;
    TextView textView_phone;
    EditText editText_phone1;
    Button button_submitprofil;
    Button button_editpss;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedPreferenceseditor;
    private DataService service;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profil, container, false);
        initListener();
        service = ServiceGenerator.createBaseService(getContext(), DataService.class);

        button_editpss= root.findViewById(R.id.editpss);
        button_editpss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Ubahpassword.class));
            }
        });



        imageView_profil = root.findViewById(R.id.hey);
        textView_name = root.findViewById(R.id.name);
        editText_namaprofil = root.findViewById(R.id.namaprofil);
        textView_email = root.findViewById(R.id.email);
        editText_email1 = root.findViewById(R.id.email1);
        textView_phone = root.findViewById(R.id.phone);
        editText_phone1 = root.findViewById(R.id.phone1);
        button_submitprofil = root.findViewById(R.id.submitprofil);
        button_editpss = root.findViewById(R.id.editpss);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        sharedPreferenceseditor = sharedPreferences.edit();



        editText_namaprofil.setText(sharedPreferences.getString("nama",""));
        editText_email1.setText(sharedPreferences.getString("email", ""));
        editText_phone1.setText(sharedPreferences.getString("no_hp", ""));




        return root;
    }
    private void initListener() {
        button_submitprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editText_email1.getText().toString();
                String username = editText_namaprofil.getText().toString();
                String no_hp = editText_phone1.getText().toString();

                if(isEmpty(email))
                    editText_email1.setError("Must not be empty");
                else if(isEmpty(email))
                    editText_namaprofil.setError("Must not be empty");
                else if(isEmpty(username))
                    editText_phone1.setError("Must not be empty");
                else
                    addData(email,username,no_hp);
            }
        });
    }

    private void addData (String email, String username, String no_hp){

        Call<BaseResponse> call = service.apiUpdateLogin(email,username,no_hp);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.code() == 200 && response.body().isSuccess() == 1) {
                    Toast.makeText(getContext(), "Data Berhasil Berubah.", Toast.LENGTH_SHORT).show();
                    editText_email1.setText("");
                    editText_namaprofil.setText("");
                    editText_phone1.setText("");
                    startActivity(new Intent(getContext(), Login.class));
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.e(TAG + ".error", t.toString());
            }
        });
    }


}