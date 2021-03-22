package com.example.mycalculator.business_logic;

import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.math.BigDecimal;

public class MyCalc implements Serializable {


    public String result = "0";

    public BigDecimal firstNumber;
    public BigDecimal secondNumber;

    public boolean isPoint = false;
    public boolean isPlus = false;
    public boolean isMinus = false;
    public boolean isMultiply = false;
    public boolean isDivision = false;
    public boolean isEqual = true;
    public boolean isNumber = false;
    public boolean isSqrt = false;

}