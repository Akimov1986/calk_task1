package com.example.homework2;

import android.os.Parcel;
import android.os.Parcelable;

public class Calculator implements Parcelable {
    public String number1;
    public String number2;
    public double result;

    protected Calculator(Parcel in) {
        number1 = in.readString();
        number2 = in.readString();
        result = in.readDouble();
    }

    public static final Creator<Calculator> CREATOR = new Creator<Calculator>() {
        @Override
        public Calculator createFromParcel(Parcel in) {
            return new Calculator(in);
        }

        @Override
        public Calculator[] newArray(int size) {
            return new Calculator[size];
        }
    };
    public Calculator() {
        number1 = "";
        number2 = "";
        result = 0.0;
    }
    public void Sum() {
        result = Double.parseDouble(number1) + Double.parseDouble(number2);
    }
    public void Min() {
        result = Double.parseDouble(number1) - Double.parseDouble(number2);
    }
    public void Mult() {
        result = Double.parseDouble(number1) * Double.parseDouble(number2);
    }
    public void Div() {
        result = Double.parseDouble(number1) / Double.parseDouble(number2);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(number1);
        dest.writeString(number2);
        dest.writeDouble(result);
    }
}
