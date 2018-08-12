package com.sharma.deepak.bakerzz.view.recipe_detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sharma.deepak.bakerzz.R;
import com.sharma.deepak.bakerzz.bean.RecipeListResponse;
import com.sharma.deepak.bakerzz.bean.RecipeStep;
import com.sharma.deepak.bakerzz.util.ImageUtil;
import com.sharma.deepak.bakerzz.view.ingredients.IngredientsActivity;
import com.sharma.deepak.bakerzz.view.recipe_detail.dummy.DummyContent;

import java.util.List;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link RecipeStepDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class RecipeStepsActivity extends AppCompatActivity implements RecipeStepsAdapter.OnRecipeStepClickListener {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private RecipeListResponse mRecipeResponse;
    private List<RecipeStep> mRecipeStepList;
    public static final String STEP_LIST_ACTIVITY_EXTRA = "STEP_LIST_ACTIVITY_EXTRA";
    private RecyclerView mRecipeStepsRecyclerView;
    public static final String RECIPE_STEP_EXTRA = "recipe_step_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        setUpViewComponents();
        setUpRecipeAdapter();
    }

    /**
     * @author deepaks
     * @date 11 august 2018
     * @description method to set up view components
     */
    private void setUpViewComponents() {
        ImageView recipeImage = findViewById(R.id.iv_recipe_image);
        mRecipeStepsRecyclerView = findViewById(R.id.rv_recipe_step);
        if (getIntent() != null) {
            if (getIntent().hasExtra(IngredientsActivity.INGREDIENT_ACTIVITY_EXTRA)) {
                mRecipeResponse = getIntent().getParcelableExtra(IngredientsActivity.INGREDIENT_ACTIVITY_EXTRA);
                ImageUtil.setUpImageResource(this, mRecipeResponse.getImage()
                        , mRecipeResponse.getName(), recipeImage);

                Toolbar toolbar = findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);
                setTitle(mRecipeResponse.getName());
                mRecipeStepList = mRecipeResponse.getSteps();
            }
        }

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }


    }

    /**
     * @author deepaks
     * @date 11 august 2018
     * @description method to set up the recipe adapter
     */
    private void setUpRecipeAdapter() {
        mRecipeStepsRecyclerView.setVisibility(View.VISIBLE);

        RecipeStepsAdapter adapter = new RecipeStepsAdapter(this, mTwoPane
                , this, mRecipeStepList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecipeStepsRecyclerView.setLayoutManager(mLayoutManager);
        mRecipeStepsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecipeStepsRecyclerView.setAdapter(adapter);
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

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, DummyContent.ITEMS, mTwoPane));
    }

    @Override
    public void onRecipeStepListener(int position, boolean isTwoPlane) {
        if (isTwoPlane) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(RecipeStepDetailFragment.ARG_ITEM_ID, mRecipeStepList.get(position));
            RecipeStepDetailFragment fragment = new RecipeStepDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit();
        } else {
            Intent intent = new Intent(this, RecipeStepDetailActivity.class);
            intent.putExtra(RecipeStepDetailFragment.ARG_ITEM_ID, mRecipeStepList.get(position));
            intent.putExtra(RECIPE_STEP_EXTRA, mRecipeResponse.getName());
            startActivity(intent);
        }
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final RecipeStepsActivity mParentActivity;
        private final List<DummyContent.DummyItem> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DummyContent.DummyItem item = (DummyContent.DummyItem) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(RecipeStepDetailFragment.ARG_ITEM_ID, item.id);
                    RecipeStepDetailFragment fragment = new RecipeStepDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, RecipeStepDetailActivity.class);
                    intent.putExtra(RecipeStepDetailFragment.ARG_ITEM_ID, item.id);

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(RecipeStepsActivity parent,
                                      List<DummyContent.DummyItem> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mIdView.setText(mValues.get(position).id);
            holder.mContentView.setText(mValues.get(position).content);

            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mIdView;
            final TextView mContentView;

            ViewHolder(View view) {
                super(view);
                mIdView = (TextView) view.findViewById(R.id.id_text);
                mContentView = (TextView) view.findViewById(R.id.content);
            }
        }
    }
}
