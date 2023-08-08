package com.lordania.lobox.ui.help;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.lordania.lobox.R;

public class Help extends Fragment {
    ImageView imageView_Kotak;
    TextView textView_prosedur;
    TextView textView_prosedur1;
    TextView textView_prosedur2;
    TextView textView_prosedur3;
    TextView textView_prosedur4;
    TextView textView_prosedur5;
    TextView textView_syarat;
    TextView textView_syarat1;
    TextView textView_syarat2;
    TextView textView_syarat3;
    TextView textView_syarat4;
    TextView textView_namapesan;
    TextView textView_judulpesan;
    TextView textView_pesan;
    Button button_submitpesan;
    Button button_prosedur;
    Button button_syarat;
    Button button_lapor;

    LinearLayout linearLayout_lytpros;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_help, container, false);
        imageView_Kotak = root.findViewById(R.id.kotak);
        textView_prosedur= root.findViewById(R.id.prosedur);
        textView_prosedur1= root.findViewById(R.id.prosedur1);
        textView_prosedur2= root.findViewById(R.id.prosedur2);
        textView_prosedur3= root.findViewById(R.id.prosedur3);
        textView_prosedur4= root.findViewById(R.id.prosedur4);
        textView_prosedur5= root.findViewById(R.id.prosedur5);
        textView_syarat= root.findViewById(R.id.syarat);
        textView_syarat1 = root.findViewById(R.id.syarat1);
        textView_syarat2= root.findViewById(R.id.syarat2);
        textView_syarat3= root.findViewById(R.id.syarat3);
        textView_syarat4= root.findViewById(R.id.syarat4);
        textView_namapesan= root.findViewById(R.id.namapesan);
        textView_judulpesan= root.findViewById(R.id.judulpesan);
        textView_pesan= root.findViewById(R.id.pesan);
        button_submitpesan = root.findViewById(R.id.submitpesan);
        linearLayout_lytpros = root.findViewById(R.id.lytpros);
        button_prosedur = root.findViewById(R.id.prosedur);

        button_prosedur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linearLayout_lytpros.getVisibility()==View.VISIBLE)
                    linearLayout_lytpros.setVisibility(View.GONE);
                else
                    linearLayout_lytpros.setVisibility(View.VISIBLE);

            }
        });




        return root;
    }
}