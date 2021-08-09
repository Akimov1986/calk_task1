package com.example.homework2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    static final int MyTheme1 = 1;
    static final int MyTheme2 = 2;

    static final String KEY_SP = "sp";
    static final String KEY_CURRENT_THEME = "current_theme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setCurrentTheme(getRealId(getCurrentTheme()));
        super.onCreate(savedInstanceState);
        setTheme(getRealId(getCurrentTheme()));
        setContentView(R.layout.activity_setting);
        initRadioButtons();
    }


    private void initRadioButtons() {
        (findViewById(R.id.BtnTheme1)).setOnClickListener(this);
        (findViewById(R.id.BtnTheme2)).setOnClickListener(this);
        switch (getCurrentTheme()) {
            case 1:
                ((RadioButton) findViewById(R.id.BtnTheme1)).setChecked(true);
                break;

            case 2:
                ((RadioButton) findViewById(R.id.BtnTheme2)).setChecked(true);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.BtnTheme1:
                setCurrentTheme(MyTheme1);
                Intent i = new Intent(SettingActivity.this,MainActivity.class);
                startActivity(i);
                break;
            case R.id.BtnTheme2:
                setCurrentTheme(MyTheme2);
                Intent intent = new Intent(SettingActivity.this,MainActivity.class);
                startActivity(intent);
                break;
        }
        recreate();

    }

    private int getCurrentTheme() {
        SharedPreferences sharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_CURRENT_THEME, -1);
    }

    private void setCurrentTheme(int currentTheme) {
        SharedPreferences sharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_CURRENT_THEME, currentTheme);
        editor.apply();
    }

    private int getRealId(int currentTheme) {
        switch (currentTheme) {
            case MyTheme1:
                return R.style.Theme1;
            case MyTheme2:
                return R.style.Theme2;
            default:
                return 0;

        }
    }
}