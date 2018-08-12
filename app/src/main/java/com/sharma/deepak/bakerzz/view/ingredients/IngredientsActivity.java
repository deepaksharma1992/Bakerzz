package com.sharma.deepak.bakerzz.view.ingredients;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.baoyachi.stepview.VerticalStepView;
import com.sharma.deepak.bakerzz.R;
import com.sharma.deepak.bakerzz.bean.Ingredient;
import com.sharma.deepak.bakerzz.bean.RecipeListResponse;
import com.sharma.deepak.bakerzz.util.ImageUtil;
import com.sharma.deepak.bakerzz.view.home.MainActivity;
import com.sharma.deepak.bakerzz.view.recipe_detail.RecipeStepsActivity;

import java.util.ArrayList;
import java.util.List;

public class IngredientsActivity extends AppCompatActivity implements View.OnClickListener {
    private RecipeListResponse mRecipeResponse;
    public static final String INGREDIENT_ACTIVITY_EXTRA = "ingredient-activity-extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);
        setUpActivityComponents();
    }

    private void setUpActivityComponents() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        VerticalStepView ingredientsList = findViewById(R.id.step_view0);
        Button recipeStepButton = findViewById(R.id.btn_steps);
        recipeStepButton.setOnClickListener(this);
        ImageView recipeImage = findViewById(R.id.iv_recipe_image);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (getIntent() != null) {
            if (getIntent().hasExtra(MainActivity.RECIPE_CONTENT_EXTRA)) {
                mRecipeResponse = getIntent()
                        .getParcelableExtra(MainActivity.RECIPE_CONTENT_EXTRA);

                ImageUtil.setUpImageResource(this, mRecipeResponse.getImage()
                        , mRecipeResponse.getName(), recipeImage);
                setTitle(mRecipeResponse.getName());
                List<Ingredient> ingredientList = mRecipeResponse.getIngredients();
                List<String> displayIngredientList = new ArrayList<>();
                for (Ingredient ingredient : ingredientList) {
                    displayIngredientList.add(ingredient.toString());
                }

                ingredientsList
                        .reverseDraw(false)
                        .setTextSize(16)
                        .setStepViewTexts(displayIngredientList)
                        .setStepsViewIndicatorCompletedLineColor(
                                ContextCompat.getColor(this, R.color.colorAccent))
                        .setStepsViewIndicatorUnCompletedLineColor(
                                ContextCompat.getColor(this, R.color.colorAccent))
                        .setStepViewComplectedTextColor(
                                ContextCompat.getColor(this, R.color.black))
                        .setStepViewUnComplectedTextColor(
                                ContextCompat.getColor(this, R.color.black))
                        .setStepsViewIndicatorCompleteIcon(
                                ContextCompat.getDrawable(this, R.drawable.ingredient_purple))
                        .setStepsViewIndicatorDefaultIcon(
                                ContextCompat.getDrawable(this, R.drawable.ingredient_purple))
                        .setStepsViewIndicatorAttentionIcon(
                                ContextCompat.getDrawable(this, R.drawable.ingredient_purple));

            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_steps) {
            Intent recipeStepIntent = new Intent(this, RecipeStepsActivity.class);
            recipeStepIntent.putExtra(INGREDIENT_ACTIVITY_EXTRA, mRecipeResponse);
            startActivity(recipeStepIntent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        }
    }
}
