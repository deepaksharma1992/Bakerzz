package com.sharma.deepak.bakerzz.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
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


}
