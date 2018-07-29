package com.sharma.deepak.bakerzz.presenter.splash;

import android.content.Context;

import com.sharma.deepak.bakerzz.R;
import com.sharma.deepak.bakerzz.bean.RecipeListResponse;
import com.sharma.deepak.bakerzz.model.splash.SplashModel;
import com.sharma.deepak.bakerzz.model.splash.SplashModelInteractor;
import com.sharma.deepak.bakerzz.util.NetworkUtil;
import com.sharma.deepak.bakerzz.view.splash.SplashScreenActivityInteractor;

import java.util.List;

public class SplashPresenter implements SplashPresenterInteractor {

    private final SplashScreenActivityInteractor mActivityInteractor;
    private final SplashModelInteractor mModelInteractor;
    private final Context mContext;

    public SplashPresenter(Context context, SplashScreenActivityInteractor activityInteractor) {
        mActivityInteractor = activityInteractor;
        mModelInteractor = new SplashModel(this);
        this.mContext = context;
    }

    @Override
    public void callRecipeWebService() {
        if (NetworkUtil.isConnectedToNetwork(mContext))
            mModelInteractor.callRecipeWebService();
        else
            mActivityInteractor.showNetworkError(mContext.getString(R.string.no_network_error));
    }

    @Override
    public void getRecipeList(List<RecipeListResponse> recipeList) {
        mActivityInteractor.getRecipeList(recipeList);
    }

    @Override
    public void getApiErrorMessage(String errorMessage) {
        mActivityInteractor.showNetworkError(errorMessage);
    }
}
