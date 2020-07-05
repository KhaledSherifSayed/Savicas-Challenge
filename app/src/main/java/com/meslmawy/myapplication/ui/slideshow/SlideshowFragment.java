package com.meslmawy.myapplication.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;
import com.meslmawy.myapplication.R;
import com.meslmawy.myapplication.SharedPrefrencesdata;
import com.meslmawy.myapplication.ui.home.HomeViewModel;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    SharedPrefrencesdata sharedPrefrencesdata;
    TextView textView;
    Button save;
    TextInputEditText userName;
    TextInputEditText current_Numbers_text;
    TextInputEditText max_numberstext;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        textView = root.findViewById(R.id.text_slideshow);
        save = root.findViewById(R.id.save_button);
        userName = root.findViewById(R.id.UserName);
        current_Numbers_text = root.findViewById(R.id.current_Numbers_text);
        max_numberstext = root.findViewById(R.id.max_numberstext);
        sharedPrefrencesdata = new SharedPrefrencesdata(getContext());
        current_Numbers_text.setText(Integer.toString(sharedPrefrencesdata.getCurren()));
        current_Numbers_text.setEnabled(false);
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPrefrencesdata.saver_userName(userName.getText().toString());
                sharedPrefrencesdata.saveMaxPatiens(Integer.parseInt(max_numberstext.getText().toString()));
            }
        });
        return root;
    }
}