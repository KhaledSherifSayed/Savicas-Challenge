package com.meslmawy.myapplication.ui.home;

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

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    SharedPrefrencesdata sharedPrefrencesdata;
    String allUser;
    TextView textView;
    TextView gender;
    Button save;
    TextInputEditText fullName;
    TextInputEditText age;
    String genderText;
    RadioGroup radioGroup;
    TextView all_data;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        textView = root.findViewById(R.id.text_home);
        all_data = root.findViewById(R.id.all_data);
        gender = root.findViewById(R.id.gender);
        save = root.findViewById(R.id.save);
        fullName = root.findViewById(R.id.fullname);
        age = root.findViewById(R.id.age_text);
        radioGroup = root.findViewById(R.id.radio_group);
        allUser = "";
        genderText = "";
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        sharedPrefrencesdata = new SharedPrefrencesdata(getContext());

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.male:
                      genderText = "M";
                        break;
                    case R.id.female:
                        genderText = "F";
                        // do operations specific to this selection
                        break;
                    case R.id.oher:
                        genderText = "O";
                        // do operations specific to this selection
                        break;
                }
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sharedPrefrencesdata.getCurren() < sharedPrefrencesdata.getKeyMaxPatients()){
                    saveNewuser();
                }
                else{
                    Toast.makeText(getContext(),"Max of patients per session reached",Toast.LENGTH_LONG).show();
                }
            }
        });

        return root;
    }
    public void saveNewuser(){
        int current  = sharedPrefrencesdata.getCurren() + 1;
        allUser+= current + "Patient" + "{FullName="+ fullName.getText() + ",gender="+genderText + ",age="+age.getText()+"}/n";
        all_data.setText(allUser);
        sharedPrefrencesdata.saveCurrent();
    }

}