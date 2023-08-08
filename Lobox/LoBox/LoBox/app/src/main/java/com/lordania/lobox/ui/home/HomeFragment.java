package com.lordania.lobox.ui.home;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.lordania.lobox.MainActivity;
import com.lordania.lobox.R;
import com.lordania.lobox.ui.slide.Adapter;
import com.lordania.lobox.ui.slide.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {
    ViewPager viewPager;
    Adapter adapter;
    List<Model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    TabLayout indicator;
    public static Timer timer;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager = root.findViewById (R.id.viewPager);
        indicator = root.findViewById (R.id.indicator);

        // 設置 banner 的圖片
        models = new ArrayList<>();
        models.add(new Model(R.drawable.banner1));
        models.add(new Model(R.drawable.banner2));
        models.add(new Model(R.drawable.banner3));

        // 設置 Adapter
        adapter = new Adapter(models,getContext());
        viewPager.setAdapter(adapter);

        // 設置 banner 彼此之間的間距
//        viewPager.setPadding(60,0,60,0);

        // 設置圓點指示器
        indicator.setupWithViewPager(viewPager, true);
        final ImageView imageView = root.findViewById(R.id.hey);
        timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 4000, 5000);



        return root;
    }
    private class SliderTimer extends TimerTask {

        @Override
        public void run() {
            try {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (viewPager.getCurrentItem() < models.size() - 1) {
                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                        } else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });

            }catch (Exception e){
                Log.e("Thread",e.toString());
                HomeFragment.timer.cancel();
            }
        }
    }
}