package com.sharma.deepak.bakerzz.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sharma.deepak.bakerzz.R;

public class ImageUtil {

    /**
     * @param context   the context object
     * @param url       the url string
     * @param imageView the image place holder
     * @description method to load the image from web URL
     * @author deepaks
     * @date 3 august 2018
     */
    public static void loadImage(Context context, String url, ImageView imageView) {
        Glide
                .with(context)
                .load(url)
                .apply(new RequestOptions()
                        .centerCrop()
                        .error(R.drawable.placeholder)
                        .placeholder(R.drawable.placeholder))
                .into(imageView);
    }

    /**
     * @param context   the context object
     * @param drawable  the drawable resource folder
     * @param imageView the image place holder
     * @description method to load the image from drawable folder
     * @author deepaks
     * @date 3 august 2018
     */
    public static void loadImage(Context context, Drawable drawable
            , ImageView imageView) {
        Glide
                .with(context)
                .load(drawable)
                .apply(new RequestOptions()
                        .centerCrop()
                        .error(R.drawable.placeholder)
                        .placeholder(R.drawable.placeholder))
                .into(imageView);
        ;
    }


    /**
     * @param context    the context object
     * @param url        the url string
     * @param imageView  the image place holder
     * @param recipeName the name of recipe
     * @author deepaks
     * @date 10 august 2018
     */
    public static void setUpImageResource(Context context, String url
            , String recipeName, ImageView imageView) {

        if (!TextUtils.isEmpty(url)) {
            ImageUtil.loadImage(context, url, imageView);
        } else {
            if (recipeName.equalsIgnoreCase(GlobalConstants.NUTELLA_PIE)) {
                ImageUtil.loadImage(context
                        , ContextCompat.getDrawable(context, R.drawable.nutella_pie)
                        , imageView);
            } else if (recipeName.equalsIgnoreCase(GlobalConstants.BROWNIES)) {
                ImageUtil.loadImage(context
                        , ContextCompat.getDrawable(context, R.drawable.brownies)
                        , imageView);
            } else if (recipeName.equalsIgnoreCase(GlobalConstants.YELLOW_CAKE)) {
                ImageUtil.loadImage(context
                        , ContextCompat.getDrawable(context, R.drawable.yellow_cake)
                        , imageView);
            } else if (recipeName.equalsIgnoreCase(GlobalConstants.CHEESE_CAKE)) {
                ImageUtil.loadImage(context
                        , ContextCompat.getDrawable(context, R.drawable.cheese_cake)
                        , imageView);
            } else {
                ImageUtil.loadImage(context
                        , ContextCompat.getDrawable(context, R.drawable.placeholder)
                        , imageView);
            }
        }
    }

}
