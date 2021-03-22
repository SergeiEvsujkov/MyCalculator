package com.example.mycalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mycalculator.business_logic.MyCalc;

import java.math.BigDecimal;
import java.math.RoundingMode;


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
    private Button zeroButton;
    private Button oneButton;
    private Button twoButton;
    private Button threeButton;
    private Button fourButton;
    private Button fiveButton;
    private Button sixButton;
    private Button sevenButton;
    private Button eightButton;
    private Button nineButton;

    private MyCalc myCalc = new MyCalc();

    private String myCalcKey = "myCalcKey";


    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        onClickNumberButton(zeroButton);
        onClickNumberButton(oneButton);
        onClickNumberButton(twoButton);
        onClickNumberButton(threeButton);
        onClickNumberButton(fourButton);
        onClickNumberButton(fiveButton);
        onClickNumberButton(sixButton);
        onClickNumberButton(sevenButton);
        onClickNumberButton(eightButton);
        onClickNumberButton(nineButton);

    }


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
        zeroButton = findViewById(R.id.zeroButton);
        oneButton = findViewById(R.id.oneButton);
        twoButton = findViewById(R.id.twoButton);
        threeButton = findViewById(R.id.threeButton);
        fourButton = findViewById(R.id.fourButton);
        fiveButton = findViewById(R.id.fiveButton);
        sixButton = findViewById(R.id.sixButton);
        sevenButton = findViewById(R.id.sevenButton);
        eightButton = findViewById(R.id.eightButton);
        nineButton = findViewById(R.id.nineButton);
        value = findViewById(R.id.value);
    }

    public void onClickResetButton() {
        resetButton.setOnClickListener(v -> {
            myCalc.firstNumber = BigDecimal.valueOf(0);
            myCalc.secondNumber = BigDecimal.valueOf(0);
            value.setText("0");
            monitor.setText("0");
            myCalc.isPoint = false;
            myCalc.isPlus = false;
            myCalc.isMinus = false;
            myCalc.isMultiply = false;
            myCalc.isEqual = true;
            myCalc.isDivision = false;
            myCalc.isNumber = false;
            myCalc.isSqrt = false;
            myCalc.result = value.getText().toString();

        });
    }

    public void onClickOffButton() {
        offButton.setOnClickListener(v -> {
            finish();
        });
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    public void onClickSqrtButton() {
        sqrtButton.setOnClickListener(v -> {
            try {
                myCalc.firstNumber = new BigDecimal(value.getText().toString());
            } catch (Exception e) {
                myCalc.firstNumber = new BigDecimal("0");
            }
            if (myCalc.firstNumber.floatValue() >= 0) {
                value.setText(String.format("%.7f", Math.sqrt(myCalc.firstNumber.doubleValue())));
                monitor.setText(String.format("%.7f", Math.sqrt(myCalc.firstNumber.doubleValue())));
                myCalc.result = value.getText().toString();
            } else {
                monitor.setText("ERROR");
                myCalc.result = value.getText().toString();
            }
            monitor.setText(monitor.getText().toString().replaceAll("\\.(.*?)0+$", ".$1").replaceAll("\\.$", ""));
        });
    }


    public void onClickDivisionButton() {
        divisionButton.setOnClickListener(v -> {
            if (!myCalc.isDivision) {
                try {
                    myCalc.firstNumber = new BigDecimal(value.getText().toString());
                } catch (Exception e) {
                    myCalc.firstNumber = new BigDecimal("0");
                }
                value.getText().clear();
                value.append("0");
                myCalc.isPoint = false;
                myCalc.isPlus = false;
                myCalc.isMinus = false;
                myCalc.isMultiply = false;
                myCalc.isEqual = false;
                myCalc.isDivision = true;
                myCalc.isNumber = false;
                myCalc.isSqrt = false;
            }
            myCalc.result = value.getText().toString();
        });
    }

    public void onClickMultiplyButton() {
        multiplyButton.setOnClickListener(v -> {
            if (!myCalc.isMultiply) {
                try {
                    myCalc.firstNumber = new BigDecimal(value.getText().toString());
                } catch (Exception e) {
                    myCalc.firstNumber = new BigDecimal("0");
                }
                value.getText().clear();
                value.append("0");
                myCalc.isPoint = false;
                myCalc.isPlus = false;
                myCalc.isMinus = false;
                myCalc.isMultiply = true;
                myCalc.isEqual = false;
                myCalc.isDivision = false;
                myCalc.isNumber = false;
                myCalc.isSqrt = false;
            }
            myCalc.result = value.getText().toString();
        });
    }


    public void onClickMinusButton() {
        minusButton.setOnClickListener(v -> {
            if (!myCalc.isMinus) {

                try {
                    myCalc.firstNumber = new BigDecimal(value.getText().toString());
                } catch (Exception e) {
                    myCalc.firstNumber = new BigDecimal("0");
                }
                value.getText().clear();
                value.append("0");
                myCalc.isPoint = false;
                myCalc.isPlus = false;
                myCalc.isMinus = true;
                myCalc.isMultiply = false;
                myCalc.isEqual = false;
                myCalc.isDivision = false;
                myCalc.isNumber = false;
                myCalc.isSqrt = false;

            }
            myCalc.result = value.getText().toString();
        });
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    public void onClickEqualButton() {
        equalButton.setOnClickListener(v -> {
            if (!myCalc.isEqual && (myCalc.isPoint || myCalc.isNumber)) {
                myCalc.secondNumber = new BigDecimal(value.getText().toString());
                value.getText().clear();
                if (myCalc.isPlus) {
                    monitor.setText(myCalc.firstNumber.add(myCalc.secondNumber).setScale(7, RoundingMode.DOWN).toString());
                } else if (myCalc.isMinus) {
                    monitor.setText(myCalc.firstNumber.subtract(myCalc.secondNumber).setScale(7, RoundingMode.DOWN).toString());
                } else if (myCalc.isDivision) {
                    if (myCalc.secondNumber.floatValue() != 0) {
                        monitor.setText(myCalc.firstNumber.divide(myCalc.secondNumber, 7, RoundingMode.DOWN).toString());
                    } else {
                        monitor.setText("ERROR");
                        value.getText().clear();
                        myCalc.isPoint = false;
                        myCalc.isPlus = false;
                        myCalc.isMinus = false;
                        myCalc.isMultiply = false;
                        myCalc.isEqual = true;
                        myCalc.isDivision = false;
                        myCalc.isNumber = false;
                        myCalc.isSqrt = false;
                        return;
                    }
                } else if (myCalc.isMultiply) {
                    monitor.setText(myCalc.firstNumber.multiply(myCalc.secondNumber).setScale(7, RoundingMode.DOWN).toString());
                } else {
                    value.setText("0");
                    myCalc.isPoint = false;
                    myCalc.isPlus = false;
                    myCalc.isMinus = false;
                    myCalc.isMultiply = false;
                    myCalc.isEqual = true;
                    myCalc.isDivision = false;
                    myCalc.isNumber = false;
                    myCalc.isSqrt = false;
                    return;
                }
                value.setText(monitor.getText());
                myCalc.result = value.getText().toString();
                if (Float.parseFloat(monitor.getText().toString()) % 1 == 0) {
                    try {
                        int dotPos = (monitor.getText().toString()).indexOf(".");
                        monitor.setText(monitor.getText().toString().substring(0, dotPos));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                monitor.setText(monitor.getText().toString().replaceAll("\\.(.*?)0+$", ".$1").replaceAll("\\.$", ""));
                myCalc.isPoint = false;
                myCalc.isPlus = false;
                myCalc.isMinus = false;
                myCalc.isMultiply = false;
                myCalc.isEqual = true;
                myCalc.isDivision = false;
                myCalc.isNumber = false;
                myCalc.isSqrt = false;

            }

        });
    }

    public void onClickPlusButton() {
        plusButton.setOnClickListener(v -> {

            if (!myCalc.isPlus) {
                try {
                    myCalc.firstNumber = new BigDecimal(value.getText().toString());
                } catch (Exception e) {
                    myCalc.firstNumber = new BigDecimal("0");
                }
                value.getText().clear();
                value.append("0");
                myCalc.isPoint = false;
                myCalc.isPlus = true;
                myCalc.isMinus = false;
                myCalc.isMultiply = false;
                myCalc.isEqual = false;
                myCalc.isDivision = false;
                myCalc.isNumber = false;
                myCalc.isSqrt = false;
            }
            myCalc.result = value.getText().toString();
        });
    }

    public void onClickPointButton() {
        pointButton.setOnClickListener(v -> {
            if ((!myCalc.isPoint && myCalc.isNumber) || (!myCalc.isPoint && Float.parseFloat(monitor.getText().toString()) == 0)) {
                value.append(".");
                monitor.setText(value.getText());

                myCalc.isPoint = true;
                myCalc.isNumber = true;
                myCalc.isSqrt = false;
            }
            myCalc.result = value.getText().toString();

        });
    }

    public void onClickNumberButton(Button button) {
        button.setOnClickListener(v -> {
            if (Float.parseFloat(monitor.getText().toString()) == 0 && Float.parseFloat(button.getText().toString()) == 0 && !myCalc.isPoint) {
                return;
            } else {
                if ((myCalc.isDivision && myCalc.isMinus && myCalc.isMultiply && myCalc.isEqual && myCalc.isPlus && myCalc.isSqrt) || !myCalc.isNumber) {
                    value.setText("");
                    monitor.setText("");

                }
                if ((value.length() <= 7 && !myCalc.isPoint) || (value.length() <= 8 && myCalc.isPoint)) {

                    myCalc.isEqual = false;
                    myCalc.isNumber = true;
                    myCalc.isSqrt = false;

                    value.append(button.getText());
                    monitor.setText(value.getText());
                }
            }
            myCalc.result = value.getText().toString();
        });
    }

    // Сохранение данных
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(myCalcKey, myCalc);
        super.onSaveInstanceState(outState);

    }

    // Восстановление данных
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        myCalc = (MyCalc) savedInstanceState.getSerializable(myCalcKey);
        updateMyCalc();
    }

    @SuppressLint("SetTextI18n")
    private void updateMyCalc() {
        monitor.setText(myCalc.result);
        monitor.setText(monitor.getText().toString().replaceAll("\\.(.*?)0+$", ".$1"));
    }

}