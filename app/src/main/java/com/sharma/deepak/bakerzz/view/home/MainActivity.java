package com.sharma.deepak.bakerzz.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.sharma.deepak.bakerzz.R;
import com.sharma.deepak.bakerzz.bean.RecipeListResponse;
import com.sharma.deepak.bakerzz.util.OnRowButtonClickListener;
import com.sharma.deepak.bakerzz.view.ingredients.IngredientsActivity;
import com.sharma.deepak.bakerzz.view.splash.SplashScreenActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnRowButtonClickListener {

    private List<RecipeListResponse> mRecipeList;
    private RecyclerView mRecipeRecyclerView;
    private TextView mEmptyListText;
    public static final String RECIPE_CONTENT_EXTRA = "RECIPE-CONTENT-EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent() != null)
            if (getIntent().hasExtra(SplashScreenActivity.SPLASH_LIST_EXTRA))
                mRecipeList = getIntent()
                        .getParcelableArrayListExtra(SplashScreenActivity.SPLASH_LIST_EXTRA);
        setContentView(R.layout.activity_main);
        setUpActivityComponents();
    }

    /**
     * @author deepaks
     * @date 3 august 2018
     * @descripton method to set up the activity components
     */
    private void setUpActivityComponents() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecipeRecyclerView = findViewById(R.id.rv_recipe_list);
        mEmptyListText = findViewById(R.id.tv_empty_list);
        if (mRecipeList == null)
            showEmptyView();
        else
            setUpRecipeAdapter();
    }

    /**
     * @author deepaks
     * @date 3 august 2018
     * @description method to set up the recipe adapter
     */
    private void setUpRecipeAdapter() {
        mEmptyListText.setVisibility(View.GONE);
        mRecipeRecyclerView.setVisibility(View.VISIBLE);
        RecipeListAdapter mAdapter = new RecipeListAdapter(this
                , this, mRecipeList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecipeRecyclerView.setLayoutManager(mLayoutManager);
        mRecipeRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecipeRecyclerView.setAdapter(mAdapter);
    }

    /**
     * @author deepaks
     * @date 3 august 2018
     * @description method to show the empty view
     */
    private void showEmptyView() {
        mRecipeRecyclerView.setVisibility(View.GONE);
        mEmptyListText.setVisibility(View.VISIBLE);
    }

    /**
     * @param position the position of list
     * @author deepaks
     * @date 5 august 2018
     * @description method called on row button clicked
     */
    @Override
    public void onRowButtonClicked(int position) {
        Intent recipeIntent = new Intent(this, IngredientsActivity.class);
        recipeIntent.putExtra(RECIPE_CONTENT_EXTRA, mRecipeList.get(position));
        startActivity(recipeIntent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }
}
