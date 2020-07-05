package com.meslmawy.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefrencesdata {

    public static final String KEY_USERName = "userName";
    public static final String KEY_MAX_PATIENTS = "KEY_MAX_PATIENTS";
    private static final String KEY_CURRENT_PATIENTS_Number = "KEY_CURRENT_PATIENTS_Number";
    private static final String PREFER_NAME = "DataPref";

    SharedPreferences pref;
    // Editor reference for Shared preferences
    SharedPreferences.Editor editor;
    // Context
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;
    // Constructor
    public SharedPrefrencesdata(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void saveMaxPatiens(int max){
        editor.putInt(KEY_MAX_PATIENTS,max);
        editor.commit();
    }
    public int getKeyMaxPatients(){
        return pref.getInt(KEY_MAX_PATIENTS,5);
    }

    public void saver_userName(String userName){
        editor.putString(KEY_USERName,userName);
        editor.commit();
    }
    public String getUserName(){
        return pref.getString(KEY_USERName,"userName");
    }
    public void saveCurrent(){
        int current = getCurren() + 1;
        editor.putInt(KEY_CURRENT_PATIENTS_Number,current);
        editor.commit();
    }
    public int getCurren(){
        return pref.getInt(KEY_CURRENT_PATIENTS_Number,0);
    }

    public void clearAllData(){
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        pref.edit().clear().commit();
    }

}
