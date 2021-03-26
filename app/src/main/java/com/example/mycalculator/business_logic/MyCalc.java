package com.example.mycalculator.business_logic;


import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

public class MyCalc implements Parcelable {


    private String result = "0";

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

    public MyCalc() {
    }

    protected MyCalc(Parcel in) {
        result = in.readString();
        isPoint = in.readByte() != 0;
        isPlus = in.readByte() != 0;
        isMinus = in.readByte() != 0;
        isMultiply = in.readByte() != 0;
        isDivision = in.readByte() != 0;
        isEqual = in.readByte() != 0;
        isNumber = in.readByte() != 0;
        isSqrt = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(result);
        dest.writeByte((byte) (isPoint ? 1 : 0));
        dest.writeByte((byte) (isPlus ? 1 : 0));
        dest.writeByte((byte) (isMinus ? 1 : 0));
        dest.writeByte((byte) (isMultiply ? 1 : 0));
        dest.writeByte((byte) (isDivision ? 1 : 0));
        dest.writeByte((byte) (isEqual ? 1 : 0));
        dest.writeByte((byte) (isNumber ? 1 : 0));
        dest.writeByte((byte) (isSqrt ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MyCalc> CREATOR = new Creator<MyCalc>() {
        @Override
        public MyCalc createFromParcel(Parcel in) {
            return new MyCalc(in);
        }

        @Override
        public MyCalc[] newArray(int size) {
            return new MyCalc[size];
        }
    };

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public BigDecimal getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(BigDecimal firstNumber) {
        this.firstNumber = firstNumber;
    }

    public BigDecimal getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(BigDecimal secondNumber) {
        this.secondNumber = secondNumber;
    }

    public boolean isPoint() {
        return isPoint;
    }

    public void setPoint(boolean point) {
        isPoint = point;
    }

    public boolean isPlus() {
        return isPlus;
    }

    public void setPlus(boolean plus) {
        isPlus = plus;
    }

    public boolean isMinus() {
        return isMinus;
    }

    public void setMinus(boolean minus) {
        isMinus = minus;
    }

    public boolean isMultiply() {
        return isMultiply;
    }

    public void setMultiply(boolean multiply) {
        isMultiply = multiply;
    }

    public boolean isDivision() {
        return isDivision;
    }

    public void setDivision(boolean division) {
        isDivision = division;
    }

    public boolean isEqual() {
        return isEqual;
    }

    public void setEqual(boolean equal) {
        isEqual = equal;
    }

    public boolean isNumber() {
        return isNumber;
    }

    public void setNumber(boolean number) {
        isNumber = number;
    }

    public boolean isSqrt() {
        return isSqrt;
    }

    public void setSqrt(boolean sqrt) {
        isSqrt = sqrt;
    }


    public void setAllButtonIsNotClicked() {
        isPoint = false;
        isPlus = false;
        isMinus = false;
        isMultiply = false;
        isEqual = false;
        isDivision = false;
        isNumber = false;
        isSqrt = false;
    }
}