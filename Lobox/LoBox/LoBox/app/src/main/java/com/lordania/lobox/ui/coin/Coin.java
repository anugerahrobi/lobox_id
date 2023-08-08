package com.lordania.lobox.ui.coin;

import android.os.Bundle;
;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.lordania.lobox.R;

public class Coin extends Fragment {
    ImageView imageView_Coin;

    RadioButton imageButton_link;
    RadioButton imageButton_ovo;
    RadioGroup rgPayment;


    TextView textView_redeem;
    TextView textView_netcoin;
    TextView textView_amount;
    TextView textView_net;
    TextView textView_jumlah1;
    TextView textView_jumlah2;
    EditText editText_jumlah3;
    TextView textView_total;
    TextView textView_transfer;
    EditText editText_hp;
    EditText editText_nama_profil;
    TextView textView_note;
    TextView textView_note1;
    TextView textView_note2;
    TextView textView_note3;
    TextView textView_note4;
    Button button_submitcoin;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_coin,container, false);
        imageView_Coin = root.findViewById(R.id.coin);

        textView_redeem= root.findViewById(R.id.redeem);
        textView_netcoin= root.findViewById(R.id.netcoin);
        textView_amount= root.findViewById(R.id.amount);
        textView_net= root.findViewById(R.id.net);
        textView_jumlah1= root.findViewById(R.id.jumlah1);
        textView_jumlah2= root.findViewById(R.id.jumlah2);
        editText_jumlah3= root.findViewById(R.id.jumlah3);
        textView_total = root.findViewById(R.id.total);
        textView_transfer= root.findViewById(R.id.transfer);
        editText_hp= root.findViewById(R.id.hp);
        editText_nama_profil= root.findViewById(R.id.nama_profil);
        button_submitcoin = root.findViewById(R.id.submitcoin);
        textView_note= root.findViewById(R.id.note);
        textView_note1= root.findViewById(R.id.note1);
        textView_note2= root.findViewById(R.id.note2);
        textView_note3= root.findViewById(R.id.note3);
        textView_note4= root.findViewById(R.id.note4);


        imageButton_link= root.findViewById(R.id.rbLinkAJa);
        imageButton_ovo= root.findViewById(R.id.rbOvo);
        rgPayment = root.findViewById(R.id.rgPayment);

        return root;
    }
}