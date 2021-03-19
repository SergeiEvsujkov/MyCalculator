package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

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

    private float firstNumber;
    private float secondNumber;

    private boolean isPoint = false;
    private boolean isPlus = false;
    private boolean isMinus = false;
    private boolean isMultiply = false;
    private boolean isDivision = false;
    private boolean isEqual = false;
    private boolean isNumber = false;
    private boolean isSqrt = false;

    private StringBuffer monitorString = new StringBuffer();

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        onClickResetButton();
        onClickOffButton();
        onClickSqrtButton();
        onClickMultiplyButton();
        onClickDivisionButton();
        onClickMinusButton();
        onClickPlusButton();
        onClickPointButton();
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
    }

    public void onClickResetButton() {
        resetButton.setOnClickListener(v -> {
            firstNumber = 0;
            secondNumber = 0;
            monitorString.setLength(0);
            monitor.setText("0");
            isPoint = false;
            isPlus = false;
            isMinus = false;
            isMultiply = false;
            isEqual = false;
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

    public void onClickSqrtButton() {
        sqrtButton.setOnClickListener(v -> {
            monitorString.setLength(0);
            firstNumber = Float.parseFloat((String) monitor.getText());
            if (firstNumber >= 0) {
                monitorString.append(Math.sqrt(firstNumber));
            } else {
                monitorString.append("ERROR");
            }
            isSqrt = true;
            updateMonitor();
            monitorString.setLength(0);


        });
    }


    public void onClickDivisionButton() {
        divisionButton.setOnClickListener(v -> {
            if (!isDivision) {
                firstNumber = Float.parseFloat((String) monitor.getText());
                monitorString.setLength(0);
                isPoint = false;
                isPlus = false;
                isMinus = false;
                isMultiply = false;
                isEqual = false;
                isDivision = true;
                isNumber = false;
                isSqrt = false;
            }
        });
    }

    public void onClickMultiplyButton() {
        multiplyButton.setOnClickListener(v -> {
            if (!isMultiply) {
                firstNumber = Float.parseFloat((String) monitor.getText());
                monitorString.setLength(0);
                isPoint = false;
                isDivision = false;
                isMinus = false;
                isPlus = false;
                isEqual = false;
                isMultiply = true;
                isNumber = false;
                isSqrt = false;
            }
        });
    }

    public void onClickPlusButton() {
        plusButton.setOnClickListener(v -> {
            if (!isPlus) {
                firstNumber = Float.parseFloat((String) monitor.getText());
                monitorString.setLength(0);
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

    public void onClickMinusButton() {
        minusButton.setOnClickListener(v -> {
            if (!isMinus) {
                firstNumber = Float.parseFloat((String) monitor.getText());
                monitorString.setLength(0);
                isPoint = false;
                isDivision = false;
                isPlus = false;
                isMultiply = false;
                isEqual = false;
                isMinus = true;
                isNumber = false;
                isSqrt = false;
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void onClickEqualButton() {
        equalButton.setOnClickListener(v -> {
            if (!isEqual) {
                secondNumber = Float.parseFloat((String) monitor.getText());
                monitorString.setLength(0);

                if (isPlus) {
                    monitorString.append(firstNumber + secondNumber);
                } else if (isMinus) {
                    monitorString.append(firstNumber - secondNumber);
                } else if (isDivision) {
                    if (secondNumber != 0) {
                        monitorString.append(firstNumber / secondNumber);
                    } else {
                        monitorString.append("ERROR");
                    }
                } else if (isMultiply) {
                    monitorString.append(firstNumber * secondNumber);
                } else {
                    return;
                }
                isPlus = false;
                isMinus = false;
                isMultiply = false;
                isDivision = false;
                isNumber = false;
                isSqrt = false;
                isEqual = true;

                if ((Float.parseFloat(String.valueOf(monitorString)) <100000000) && (Float.parseFloat(String.valueOf(monitorString)) > -100000000)) {
                    updateMonitor();
                } else {
                    monitorString.setLength(0);
                    monitor.setText("ERROR");
                }
                monitorString.setLength(0);

            }
        });
    }

    public void onClickPointButton() {
        pointButton.setOnClickListener(v -> {
            if (!isPoint && isNumber) {
                monitorString.append(".");
                updateMonitor();
                isPoint = true;
                isSqrt = false;
            }

        });
    }

    public void onClickNumberButton(Button button) {
        button.setOnClickListener(v -> {
            if ((monitorString.length() <= 7 && !isPoint) || (monitorString.length() <= 8 && isPoint)) {
                monitorString.append(button.getText());
                isEqual = false;
                isNumber = true;
                isSqrt = false;
                updateMonitor();
            }
        });
    }


    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    private void updateMonitor() {
        if (Float.parseFloat(String.valueOf(monitorString)) % 1 == 0 && (isEqual || isSqrt)) {
            monitor.setText(String.format("%.0f", Float.parseFloat(String.valueOf(monitorString))));
        } else if (Float.parseFloat(String.valueOf(monitorString)) % 1 == 0 && !isEqual) {
            monitor.setText(monitorString);
        } else if (isNumber && !isSqrt) {
            monitor.setText(monitorString);
        } else {
            monitorString.setLength(9);
            monitor.setText(monitorString);
        }
    }
    
}