package com.sharma.deepak.bakerzz.view.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;

import com.sharma.deepak.bakerzz.R;
import com.sharma.deepak.bakerzz.bean.RecipeListResponse;
import com.sharma.deepak.bakerzz.presenter.splash.SplashPresenter;
import com.sharma.deepak.bakerzz.presenter.splash.SplashPresenterInteractor;
import com.sharma.deepak.bakerzz.util.MessageUtil;
import com.sharma.deepak.bakerzz.view.home.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * created by deepak on 29 july 2018
 */
public class SplashScreenActivity extends AppCompatActivity implements SplashScreenActivityInteractor {

    public static final String SPLASH_LIST_EXTRA = "SPLASH_LIST_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        setUpActivityComponents();
    }

    /**
     * @author deepaks
     * @date 29 july 2018
     * @description method to set up the activity components
     */
    private void setUpActivityComponents() {
        callRecipeWebService();
    }

    /**
     * @author deepaks
     * @date 29 july 2018
     * @description method to call the web service api
     */
    private void callRecipeWebService() {
        SplashPresenterInteractor presenterInteractor = new SplashPresenter(this, this);
        presenterInteractor.callRecipeWebService();
    }


    /**
     * @param recipeList the list of recipes
     * @author deepaks
     * @date 29 july 2018
     * @description method to get the recipe list from web service
     */
    @Override
    public void getRecipeList(List<RecipeListResponse> recipeList) {
        Intent recipeIntent = new Intent(this, MainActivity.class);
        recipeIntent.putParcelableArrayListExtra(SPLASH_LIST_EXTRA
                , (ArrayList<? extends Parcelable>) recipeList);
        startActivity(recipeIntent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        finish();
    }

    /**
     * @param errorMessage the error message string
     * @author deepaks
     * @date 29 july 2018
     * @description method to show the network error message
     */
    @Override
    public void showNetworkError(String errorMessage) {
        MessageUtil.showToastMessage(this, errorMessage);
    }
}
