package com.sharma.deepak.bakerzz.model.splash;

import com.sharma.deepak.bakerzz.bean.RecipeListResponse;
import com.sharma.deepak.bakerzz.network.ApiClient;
import com.sharma.deepak.bakerzz.network.ApiInterface;
import com.sharma.deepak.bakerzz.presenter.splash.SplashPresenterInteractor;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SplashModel implements SplashModelInteractor {
    private final SplashPresenterInteractor mPresenterInteractor;
    private Disposable mDisposable;

    public SplashModel(SplashPresenterInteractor presenterInteractor) {
        mPresenterInteractor = presenterInteractor;
    }

    @Override
    public void callRecipeWebService() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Single<List<RecipeListResponse>> recipeListResponseObservable =
                apiInterface.getRecipeList();

        recipeListResponseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getRecipeObservable());
    }

    private SingleObserver<List<RecipeListResponse>> getRecipeObservable() {

        return new SingleObserver<List<RecipeListResponse>>() {

            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onSuccess(List<RecipeListResponse> recipeListResponses) {
                mPresenterInteractor.getRecipeList(recipeListResponses);
                mDisposable.dispose();
            }

            @Override
            public void onError(Throwable e) {
                mPresenterInteractor.getApiErrorMessage(e.getMessage());
                mDisposable.dispose();
            }
        };
    }
}
