package com.sharma.deepak.bakerzz.view.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sharma.deepak.bakerzz.R;
import com.sharma.deepak.bakerzz.bean.RecipeListResponse;
import com.sharma.deepak.bakerzz.util.GlobalConstants;
import com.sharma.deepak.bakerzz.util.ImageUtil;
import com.sharma.deepak.bakerzz.util.OnRowButtonClickListener;

import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.MyViewHolder> {

    private final List<RecipeListResponse> mRecipeList;
    private final Context context;
    private final OnRowButtonClickListener mListButtonClickListener;


    public class MyViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        final TextView recipeName;
        final TextView totalServing;
        final TextView totalIngredients;
        final ImageView recipeImage;

        public MyViewHolder(View view) {
            super(view);
            recipeName = view.findViewById(R.id.tv_recipe_name);
            totalServing = view.findViewById(R.id.tv_serving);
            totalIngredients = view.findViewById(R.id.tv_ingredients);
            recipeImage = view.findViewById(R.id.iv_recipe);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListButtonClickListener.onRowButtonClicked(getAdapterPosition());
        }
    }

    public RecipeListAdapter(Context context, OnRowButtonClickListener mListButtonClickListener
            , List<RecipeListResponse> mRecipeList) {
        this.mRecipeList = mRecipeList;
        this.context = context;
        this.mListButtonClickListener = mListButtonClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recepie_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        RecipeListResponse recipeData = mRecipeList.get(holder.getAdapterPosition());

        if (recipeData.getIngredients() != null)
            holder.totalIngredients.setText(recipeData.getIngredients().size()
                    + GlobalConstants.SPACE_1
                    + context.getString(R.string.ingredients_label)
            );
        holder.totalServing.setText(context.getString(R.string.serving_label)
                + GlobalConstants.SPACE_1
                + recipeData.getServings()
        );

        holder.recipeName.setText(recipeData.getName());

        String url = recipeData.getImage();
        String recipeName = recipeData.getName();

        ImageUtil.setUpImageResource(context, url, recipeName, holder.recipeImage);
    }


    /**
     * @return the list count
     * @date 13 feb 2018
     * @author deepaks
     * @description method for getting the item count
     */
    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }
}
