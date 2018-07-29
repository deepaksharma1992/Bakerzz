package com.sharma.deepak.bakerzz.presenter.splash;

import com.sharma.deepak.bakerzz.bean.RecipeListResponse;

import java.util.List;

public interface SplashPresenterInteractor {
    void callRecipeWebService();

    void getRecipeList(List<RecipeListResponse> recipeList);

    void getApiErrorMessage(String errorMessage);
}
