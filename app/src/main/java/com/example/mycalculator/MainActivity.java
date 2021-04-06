package com.example.mycalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mycalculator.business_logic.MyCalc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    private TextView monitor;
    private EditText value;
    private Button resetButton;
    private Button offButton;
    private Button sqrtButton;
    private Button divisionButton;
    private Button multiplyButton;
    private Button plusButton;
    private Button minusButton;
    private Button equalButton;
    private Button pointButton;
    private Button changeTheme;

    private MyCalc myCalc = new MyCalc();

    private final String MY_CALC_KEY = "myCalcKey";


    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        initViews();
        monitor.setText("0");
        value.setText("0");
        onClickResetButton();
        onClickOffButton();
        onClickSqrtButton();
        onClickPointButton();
        onClickPlusButton();
        onClickMultiplyButton();
        onClickDivisionButton();
        onClickMinusButton();
        onClickEqualButton();
        onClickNumberButton();
        onClickChangeTheme();

    }

    private final int[] numberButtonIds = new int[]{R.id.zeroButton,
            R.id.oneButton,
            R.id.twoButton,
            R.id.threeButton,
            R.id.fourButton,
            R.id.fiveButton,
            R.id.sixButton,
            R.id.sevenButton,
            R.id.eightButton,
            R.id.nineButton};


    private void initViews() {
        monitor = findViewById(R.id.monitor);
        resetButton = findViewById(R.id.resetButton);
        offButton = findViewById(R.id.offButton);
        sqrtButton = findViewById(R.id.sqrtButton);
        divisionButton = findViewById(R.id.divisionButton);
        multiplyButton = findViewById(R.id.multiplyButton);
        plusButton = findViewById(R.id.plusButton);
        minusButton = findViewById(R.id.minusButton);
        equalButton = findViewById(R.id.equalButton);
        pointButton = findViewById(R.id.pointButton);
        value = findViewById(R.id.value);
        changeTheme = findViewById(R.id.changeTheme);
    }

    public void onClickResetButton() {
        resetButton.setOnClickListener(v -> {
            myCalc.setFirstNumber(BigDecimal.valueOf(0));
            myCalc.setSecondNumber(BigDecimal.valueOf(0));
            value.setText("0");
            monitor.setText("0");
            myCalc.setAllButtonIsNotClicked();
            myCalc.setEqual(true);
            myCalc.setResult(value.getText().toString());

        });
    }

    public void onClickOffButton() {
        offButton.setOnClickListener(v -> finish());
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    public void onClickSqrtButton() {
        sqrtButton.setOnClickListener(v -> {
            try {
                myCalc.setFirstNumber(new BigDecimal(value.getText().toString()));
            } catch (Exception e) {
                myCalc.setFirstNumber(new BigDecimal("0"));
            }
            if (!monitor.getText().equals("ERROR")) {
                if (myCalc.getFirstNumber().floatValue() >= 0) {
                    value.setText(String.format("%.7f", Math.sqrt(myCalc.getFirstNumber().doubleValue())));
                    monitor.setText(value.getText());
                } else {
                    monitor.setText("ERROR");
                }
                monitor.setText(monitor.getText().toString().replaceAll("\\.(.*?)0+$", ".$1").replaceAll("\\.$", ""));

            }
            myCalc.setResult(monitor.getText().toString());
        });
    }


    public void onClickDivisionButton() {
        divisionButton.setOnClickListener(v -> {
            if (!myCalc.isDivision()) {
                try {
                    myCalc.setFirstNumber(new BigDecimal(value.getText().toString()));
                } catch (Exception e) {
                    myCalc.setFirstNumber(new BigDecimal("0"));
                }
                value.getText().clear();
                value.append("0");
                myCalc.setAllButtonIsNotClicked();
                myCalc.setDivision(true);
            }
            myCalc.setResult(monitor.getText().toString());
        });
    }

    public void onClickMultiplyButton() {
        multiplyButton.setOnClickListener(v -> {
            if (!myCalc.isMultiply()) {
                try {
                    myCalc.setFirstNumber(new BigDecimal(value.getText().toString()));
                } catch (Exception e) {
                    myCalc.setFirstNumber(new BigDecimal("0"));
                }
                value.getText().clear();
                value.append("0");
                myCalc.setAllButtonIsNotClicked();
                myCalc.setMultiply(true);

            }
            myCalc.setResult(monitor.getText().toString());
        });
    }


    public void onClickMinusButton() {
        minusButton.setOnClickListener(v -> {
            if (!myCalc.isMinus()) {

                try {
                    myCalc.setFirstNumber(new BigDecimal(value.getText().toString()));
                } catch (Exception e) {
                    myCalc.setFirstNumber(new BigDecimal("0"));
                }
                value.getText().clear();
                value.append("0");
                myCalc.setAllButtonIsNotClicked();
                myCalc.setMinus(true);

            }
            myCalc.setResult(monitor.getText().toString());
        });
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    public void onClickEqualButton() {
        equalButton.setOnClickListener(v -> {
            if (!myCalc.isEqual() && (myCalc.isPoint() || myCalc.isNumber())) {
                myCalc.setSecondNumber(new BigDecimal(value.getText().toString()));
                value.getText().clear();
                if (myCalc.isPlus()) {
                    monitor.setText(myCalc.getFirstNumber().add(myCalc.getSecondNumber()).setScale(7, RoundingMode.DOWN).toString());
                } else if (myCalc.isMinus()) {
                    monitor.setText(myCalc.getFirstNumber().subtract(myCalc.getSecondNumber()).setScale(7, RoundingMode.DOWN).toString());
                } else if (myCalc.isDivision()) {
                    if (myCalc.getSecondNumber().floatValue() != 0) {
                        monitor.setText(myCalc.getFirstNumber().divide(myCalc.getSecondNumber(), 7, RoundingMode.DOWN).toString());

                    } else {
                        monitor.setText("ERROR");
                        myCalc.setAllButtonIsNotClicked();
                        myCalc.setEqual(true);

                        myCalc.setFirstNumber(BigDecimal.valueOf(0));
                        myCalc.setSecondNumber(BigDecimal.valueOf(0));
                        value.setText("0");
                        myCalc.setResult(monitor.getText().toString());

                        return;
                    }
                } else if (myCalc.isMultiply()) {
                    monitor.setText(myCalc.getFirstNumber().multiply(myCalc.getSecondNumber()).setScale(7, RoundingMode.DOWN).toString());
                } else {
                    value.setText("0");
                    myCalc.setAllButtonIsNotClicked();
                    myCalc.setEqual(true);
                    myCalc.setResult(monitor.getText().toString());
                    return;
                }
                value.setText(monitor.getText());
                monitor.setText(monitor.getText().toString().replaceAll("\\.(.*?)0+$", ".$1").replaceAll("\\.$", ""));
                myCalc.setAllButtonIsNotClicked();
                myCalc.setEqual(true);
                myCalc.setResult(monitor.getText().toString());

            }

        });
    }

    public void onClickPlusButton() {
        plusButton.setOnClickListener(v -> {

            if (!myCalc.isPlus()) {
                try {
                    myCalc.setFirstNumber(new BigDecimal(value.getText().toString()));
                } catch (Exception e) {
                    myCalc.setFirstNumber(new BigDecimal("0"));
                }
                value.getText().clear();
                value.append("0");
                myCalc.setAllButtonIsNotClicked();
                myCalc.setPlus(true);
            }
            myCalc.setResult(monitor.getText().toString());
        });
    }

    public void onClickPointButton() {
        pointButton.setOnClickListener(v -> {

            try {
                if ((!myCalc.isPoint() && myCalc.isNumber()) || (!myCalc.isPoint() && Float.parseFloat(monitor.getText().toString()) == 0)) {
                    value.append(".");
                    monitor.setText(value.getText());

                    myCalc.setPoint(true);
                    myCalc.setNumber(true);
                    myCalc.setSqrt(false);
                }

                myCalc.setResult(monitor.getText().toString());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            myCalc.setResult(monitor.getText().toString());
        });
    }

    public void onClickNumberButton() {

        for (int numbersId : numberButtonIds) {
            Button button = findViewById(numbersId);
            button.setOnClickListener(v -> {
                try {
                    if (Float.parseFloat(monitor.getText().toString()) == 0 && Float.parseFloat(button.getText().toString()) == 0 && !myCalc.isPoint()) {
                        return;
                    } else {
                        if ((myCalc.isDivision() && myCalc.isMinus() && myCalc.isMultiply() && myCalc.isEqual() && myCalc.isPlus() && myCalc.isSqrt()) || !myCalc.isNumber()) {
                            value.setText("");
                            monitor.setText("");

                        }
                        if ((value.length() <= 7 && !myCalc.isPoint()) || (value.length() <= 8 && myCalc.isPoint())) {

                            myCalc.setEqual(false);
                            myCalc.setNumber(true);
                            myCalc.setSqrt(false);

                            value.append(button.getText());
                            monitor.setText(value.getText());
                        }
                    }
                    myCalc.setResult(monitor.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    // Сохранение данных
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(MY_CALC_KEY, myCalc);
        super.onSaveInstanceState(outState);

    }

    // Восстановление данных
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        myCalc = (MyCalc) savedInstanceState.getParcelable(MY_CALC_KEY);
        updateMyCalc();
    }

    @SuppressLint("SetTextI18n")
    private void updateMyCalc() {
        monitor.setText(myCalc.getResult());
        monitor.setText(monitor.getText().toString().replaceAll("\\.(.*?)0+$", ".$1"));
    }

    public void onClickChangeTheme() {
        changeTheme.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ChangeTheme.class);
            startActivity(intent);
        });

    }

}