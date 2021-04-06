package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.google.android.material.radiobutton.MaterialRadioButton;

import java.util.Objects;

public class ChangeTheme extends AppCompatActivity {

    // Имя настроек
    private static final String NameSharedPreference = "LOGIN";

    // Имя параметра в настройках
    private static final String AppTheme = "APP_THEME";

    protected static final int AppThemeLight = 0;
    protected static final int AppThemeDark = 1;
    protected static final int AppThemeSport = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getAppTheme());
        setContentView(R.layout.activity_change_theme);
        Objects.requireNonNull(getSupportActionBar()).hide();
        initThemeChooser();

    }


    private void initThemeChooser() {
        initRadioButton(findViewById(R.id.light),
                AppThemeLight);
        initRadioButton(findViewById(R.id.dark),
                AppThemeDark);
        initRadioButton(findViewById(R.id.sport),
                AppThemeSport);
        RadioGroup rg = findViewById(R.id.radioButtons);
        ((MaterialRadioButton)rg.getChildAt(getCodeStyle(AppThemeLight))).setChecked(true);
    }


    protected int getAppTheme() {
        return codeStyleToStyleId(getCodeStyle(R.style.Theme_MyCalculator));
    }

    // Чтение настроек, параметр «тема»
    protected int getCodeStyle(int codeStyle) {
        // Работаем через специальный класс сохранения и чтения настроек
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        //Прочитать тему, если настройка не найдена - взять по умолчанию
        return sharedPref.getInt(AppTheme, codeStyle);
    }

    private int codeStyleToStyleId(int codeStyle){
        switch(codeStyle){
            case AppThemeDark:
                return R.style.Theme_MyCalculatorDark;
            case AppThemeSport:
                return R.style.Theme_MyCalculatorSport;
            default:
                return R.style.Theme_MyCalculator;

        }
    }

    // Сохранение настроек
    protected void setAppTheme(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        // Настройки сохраняются посредством специального класса editor.
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(AppTheme, codeStyle);
        editor.apply();
    }

    private void initRadioButton(View button, final int codeStyle){
        button.setOnClickListener(v -> {
            setAppTheme(codeStyle);
            recreate();
        });
    }
}