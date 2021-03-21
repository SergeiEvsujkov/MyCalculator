package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView monitor;
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

    private EditText value;

    private BigDecimal firstNumber;
    private BigDecimal secondNumber;

    private boolean isPoint = false;
    private boolean isPlus = false;
    private boolean isMinus = false;
    private boolean isMultiply = false;
    private boolean isDivision = false;
    private boolean isEqual = true;
    private boolean isNumber = false;
    private boolean isSqrt = false;


    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
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
            firstNumber = BigDecimal.valueOf(0);
            secondNumber = BigDecimal.valueOf(0);
            value.setText("0");
            monitor.setText("0");
            isPoint = false;
            isPlus = false;
            isMinus = false;
            isMultiply = false;
            isEqual = true;
            isDivision = false;
            isNumber = false;
            isSqrt = false;

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
                firstNumber = new BigDecimal(value.getText().toString());
            } catch (Exception e) {
                firstNumber = new BigDecimal("0");
            }
            if (firstNumber.floatValue() >= 0) {
                value.setText(String.format("%.7f", Math.sqrt(firstNumber.doubleValue())));
                monitor.setText(String.format("%.7f", Math.sqrt(firstNumber.doubleValue())));
            } else {
                monitor.setText("ERROR");
            }

        });
    }


    public void onClickDivisionButton() {
        divisionButton.setOnClickListener(v -> {
            if (!isDivision) {
                try {
                    firstNumber = new BigDecimal(value.getText().toString());
                } catch (Exception e) {
                    firstNumber = new BigDecimal("0");
                }
                value.getText().clear();
                value.append("0");
                isPoint = false;
                isDivision = true;
                isMinus = false;
                isMultiply = false;
                isEqual = false;
                isPlus = false;
                isNumber = false;
                isSqrt = false;
            }
        });
    }

    public void onClickMultiplyButton() {
        multiplyButton.setOnClickListener(v -> {
            if (!isMultiply) {
                try {
                    firstNumber = new BigDecimal(value.getText().toString());
                } catch (Exception e) {
                    firstNumber = new BigDecimal("0");
                }
                value.getText().clear();
                value.append("0");
                isPoint = false;
                isDivision = false;
                isMinus = false;
                isMultiply = true;
                isEqual = false;
                isPlus = false;
                isNumber = false;
                isSqrt = false;
            }
        });
    }


    public void onClickMinusButton() {
        minusButton.setOnClickListener(v -> {
            if (!isMinus) {

                try {
                    firstNumber = new BigDecimal(value.getText().toString());
                } catch (Exception e) {
                    firstNumber = new BigDecimal("0");
                }
                value.getText().clear();
                value.append("0");
                isPoint = false;
                isDivision = false;
                isMinus = true;
                isMultiply = false;
                isEqual = false;
                isPlus = false;
                isNumber = false;
                isSqrt = false;
            }
        });
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    public void onClickEqualButton() {
        equalButton.setOnClickListener(v -> {
            if (!isEqual && (isPoint || isNumber)) {
                secondNumber = new BigDecimal(value.getText().toString());
                value.getText().clear();
                if (isPlus) {
                    monitor.setText(firstNumber.add(secondNumber).setScale(7, RoundingMode.DOWN).toString());
                } else if (isMinus) {
                    monitor.setText(firstNumber.subtract(secondNumber).setScale(7, RoundingMode.DOWN).toString());
                } else if (isDivision) {
                    if (secondNumber.floatValue() != 0) {
                        monitor.setText(firstNumber.divide(secondNumber, 7, RoundingMode.DOWN).toString());
                    } else {
                        monitor.setText("ERROR");
                        value.getText().clear();
                        isPlus = false;
                        isMinus = false;
                        isMultiply = false;
                        isDivision = false;
                        isNumber = false;
                        isSqrt = false;
                        isEqual = true;
                        isPoint = false;
                        return;
                    }
                } else if (isMultiply) {
                    monitor.setText(firstNumber.multiply(secondNumber).setScale(7, RoundingMode.DOWN).toString());
                } else {
                    return;
                }
                value.setText(monitor.getText());
                if (Float.parseFloat(monitor.getText().toString()) % 1 == 0) {
                    try {
                        int dotPos = (monitor.getText().toString()).indexOf(".");
                        monitor.setText(monitor.getText().toString().substring(0, dotPos));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                isPlus = false;
                isMinus = false;
                isMultiply = false;
                isDivision = false;
                isNumber = false;
                isSqrt = false;
                isEqual = true;
                isPoint = false;

            }
        });
    }

    public void onClickPlusButton() {
        plusButton.setOnClickListener(v -> {

            if (!isPlus) {
                try {
                    firstNumber = new BigDecimal(value.getText().toString());
                } catch (Exception e) {
                    firstNumber = new BigDecimal("0");
                }
                value.getText().clear();
                value.append("0");
                isPoint = false;
                isDivision = false;
                isMinus = false;
                isMultiply = false;
                isEqual = false;
                isPlus = true;
                isNumber = false;
                isSqrt = false;
            }
        });
    }

    public void onClickPointButton() {
        pointButton.setOnClickListener(v -> {
            if ((!isPoint && isNumber) || (!isPoint && Float.parseFloat(monitor.getText().toString()) == 0)) {
                value.append(".");
                monitor.setText(value.getText());
                isPoint = true;
                isSqrt = false;
                isNumber = true;
            }

        });
    }

    public void onClickNumberButton(Button button) {
        button.setOnClickListener(v -> {
            if (Float.parseFloat(monitor.getText().toString()) == 0 && Float.parseFloat(button.getText().toString()) == 0 && !isPoint) {
                return;
            } else {
                if ((isDivision && isMinus && isMultiply && isEqual && isPlus && isSqrt) || !isNumber) {
                    value.setText("");
                    monitor.setText("");

                }
                if ((value.length() <= 7 && !isPoint) || (value.length() <= 8 && isPoint)) {
                    isEqual = false;
                    isNumber = true;
                    isSqrt = false;
                    value.append(button.getText());
                    monitor.setText(value.getText());
                }
            }
        });
    }


}