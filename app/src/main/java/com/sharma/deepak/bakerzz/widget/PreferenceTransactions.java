package com.sharma.deepak.bakerzz.widget;

import android.content.Context;
import android.content.SharedPreferences;

import com.sharma.deepak.bakerzz.util.BakerApplication;
import com.sharma.deepak.bakerzz.util.GlobalConstants;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PreferenceTransactions {

    private static Disposable mInsertionDisposable;
    private static final SharedPreferences sharedPreferences = BakerApplication
            .getAppContext()
            .getSharedPreferences(GlobalConstants.BAKER_PREFERENCE
                    , Context.MODE_PRIVATE);

    public static void setStringPreference(String preferenceKey, String preferenceValue) {
        getStringInsertionObservable(preferenceKey, preferenceValue)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getInsertionObserver());
    }

    private static Single<Void> getStringInsertionObservable(final String preferenceKey
            , final String preferenceValue) {
        return Single.create(new SingleOnSubscribe<Void>() {
            @Override
            public void subscribe(SingleEmitter<Void> emitter) throws Exception {
                SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
                prefsEditor.putString(preferenceKey, preferenceValue);
                prefsEditor.apply();
            }
        });
    }

    private static SingleObserver<Void> getInsertionObserver() {
        return new SingleObserver<Void>() {
            @Override
            public void onSubscribe(Disposable d) {
                mInsertionDisposable = d;
            }

            @Override
            public void onSuccess(Void aVoid) {
                mInsertionDisposable.dispose();
            }

            @Override
            public void onError(Throwable e) {
                mInsertionDisposable.dispose();
            }
        };
    }
}
