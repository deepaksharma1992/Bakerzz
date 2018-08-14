package com.sharma.deepak.bakerzz.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.sharma.deepak.bakerzz.R;
import com.sharma.deepak.bakerzz.util.GlobalConstants;
import com.sharma.deepak.bakerzz.util.PrefManagerUtil;
import com.sharma.deepak.bakerzz.view.splash.SplashScreenActivity;

/**
 * Implementation of App Widget functionality.
 */
public class IngredientWidget extends AppWidgetProvider {

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                       int appWidgetId, String ingredientsText) {

        CharSequence widgetText = ingredientsText;
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingredient_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);

        Intent intent = new Intent(context, SplashScreenActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent
                , 0);
        views.setOnClickPendingIntent(R.id.id_launch, pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        String ingredientsText = PrefManagerUtil.getString(GlobalConstants.INGREDIENT_PREFERENCE_KEY);
        if(ingredientsText.equals(GlobalConstants.BLANK))
            ingredientsText=context.getString(R.string.no_recipe_selected);
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, ingredientsText);
        }


    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

