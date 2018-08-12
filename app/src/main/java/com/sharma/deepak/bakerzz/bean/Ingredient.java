package com.sharma.deepak.bakerzz.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.sharma.deepak.bakerzz.util.GlobalConstants;

public class Ingredient implements Parcelable {
    private double quantity;
    private String measure, ingredient;


    protected Ingredient(Parcel in) {
        quantity = in.readDouble();
        measure = in.readString();
        ingredient = in.readString();
    }

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(quantity);
        parcel.writeString(measure);
        parcel.writeString(ingredient);
    }

    public double getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public static Creator<Ingredient> getCREATOR() {
        return CREATOR;
    }

    @Override
    public String toString() {
        return quantity + GlobalConstants.SPACE_1 + measure + GlobalConstants.SPACE_1 + ingredient;
    }
}

