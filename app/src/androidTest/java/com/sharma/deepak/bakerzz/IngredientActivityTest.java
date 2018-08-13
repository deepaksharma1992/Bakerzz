package com.sharma.deepak.bakerzz;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.sharma.deepak.bakerzz.view.ingredients.IngredientsActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class IngredientActivityTest {

    @Rule
    public ActivityTestRule<IngredientsActivity> mIngredientActivityRule =
            new ActivityTestRule<>(IngredientsActivity.class);

    @Test
    public void openActivity() {
        onView(withId(R.id.btn_steps)).perform(click());

//        onView(withId(R.id.iv_recipe_image)).check(matches(isDisplayed()));
    }
}
