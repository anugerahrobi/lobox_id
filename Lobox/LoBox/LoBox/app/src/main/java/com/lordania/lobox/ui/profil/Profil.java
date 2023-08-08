package com.lordania.lobox.ui.profil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.lordania.lobox.R;

public class Profil extends Fragment {
    ImageView imageView_profil;
    TextView textView_name;
    EditText editText_namaprofil;
    TextView textView_email;
    EditText editText_email1;
    TextView textView_phone;
    EditText editText_phone1;
    TextView textView_pss;
    EditText editText_pss1;
    Button button_submitprofil;
    Button button_editpss;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profil, container, false);
        imageView_profil = root.findViewById(R.id.hey);
        textView_name = root.findViewById(R.id.name);
        editText_namaprofil = root.findViewById(R.id.namaprofil);
        textView_email = root.findViewById(R.id.email);
        editText_email1 = root.findViewById(R.id.email1);
        textView_phone = root.findViewById(R.id.phone);
        editText_phone1 = root.findViewById(R.id.phone1);
        textView_pss = root.findViewById(R.id.pss);
        button_submitprofil = root.findViewById(R.id.submitprofil);
        button_editpss = root.findViewById(R.id.editpss);


        return root;
    }
}