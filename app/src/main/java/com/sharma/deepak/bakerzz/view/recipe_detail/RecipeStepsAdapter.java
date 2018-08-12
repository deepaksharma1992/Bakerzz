package com.sharma.deepak.bakerzz.view.recipe_detail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sharma.deepak.bakerzz.R;
import com.sharma.deepak.bakerzz.bean.RecipeStep;
import com.sharma.deepak.bakerzz.util.ColorUtils;
import com.sharma.deepak.bakerzz.util.OnRowButtonClickListener;

import java.util.List;

public class RecipeStepsAdapter extends RecyclerView.Adapter<RecipeStepsAdapter.MyViewHolder> {

    private final List<RecipeStep> mRecipeList;
    private final Context mContext;
    private final boolean isTwoPane;
    private final OnRecipeStepClickListener mListButtonClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        final TextView serialNumber;
        final TextView recipeStep;


        public MyViewHolder(View view) {
            super(view);
            serialNumber = view.findViewById(R.id.tv_sl_no);
            recipeStep = view.findViewById(R.id.tv_step_name);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListButtonClickListener.onRecipeStepListener(getAdapterPosition(), isTwoPane);
        }
    }

    public RecipeStepsAdapter(Context context, boolean isTwoPane
            , OnRecipeStepClickListener mListButtonClickListener
            , List<RecipeStep> mRecipeList) {
        this.mRecipeList = mRecipeList;
        this.mContext = context;
        this.isTwoPane = isTwoPane;
        this.mListButtonClickListener = mListButtonClickListener;
    }

    @NonNull
    @Override
    public RecipeStepsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_recipe_step, parent, false);

        return new RecipeStepsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecipeStepsAdapter.MyViewHolder holder, final int position) {
        RecipeStep recipeData = mRecipeList.get(holder.getAdapterPosition());
        holder.serialNumber.setText(String.valueOf(holder.getAdapterPosition()+1));
        ColorUtils.setDrawableColor(mContext, holder.serialNumber);
        holder.recipeStep.setText(recipeData.getShortDescription());
    }


    /**
     * @return the list count
     * @author deepaks
     * @description method for getting the item count
     */
    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }


    /**
     * @author deepaks
     * @date 11 august 2018
     */
    public interface OnRecipeStepClickListener {
        void onRecipeStepListener(int position, boolean isTwoPlane);
    }
}

