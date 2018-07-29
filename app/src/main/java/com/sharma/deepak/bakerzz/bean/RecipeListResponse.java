package com.sharma.deepak.bakerzz.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class RecipeListResponse implements Parcelable {
    private int id, servings;
    private String name, image;
    private List<Ingredient> ingredients;
    private List<RecipeStep> steps;


    protected RecipeListResponse(Parcel in) {
        id = in.readInt();
        servings = in.readInt();
        name = in.readString();
        image = in.readString();
    }

    public static final Creator<RecipeListResponse> CREATOR = new Creator<RecipeListResponse>() {
        @Override
        public RecipeListResponse createFromParcel(Parcel in) {
            return new RecipeListResponse(in);
        }

        @Override
        public RecipeListResponse[] newArray(int size) {
            return new RecipeListResponse[size];
        }
    };

    public int getId() {
        return id;
    }

    public int getServings() {
        return servings;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<RecipeStep> getSteps() {
        return steps;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(servings);
        parcel.writeString(name);
        parcel.writeString(image);
    }
}


