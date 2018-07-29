package com.sharma.deepak.bakerzz.view.splash;

import com.sharma.deepak.bakerzz.bean.RecipeListResponse;

import java.util.List;

public interface SplashScreenActivityInteractor {
    void getRecipeList(List<RecipeListResponse> recipeList);

    void showNetworkError(String errorMessage);
}
