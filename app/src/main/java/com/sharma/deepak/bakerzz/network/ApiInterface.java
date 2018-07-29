package com.sharma.deepak.bakerzz.network;

import com.sharma.deepak.bakerzz.bean.RecipeListResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by deepak on 26-05-2018.
 */

public interface ApiInterface {

    String FILE_EXTENSION = "baking.json";

    @GET(FILE_EXTENSION)
    Single<List<RecipeListResponse>> getRecipeList();
}
