package com.example.testingwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class MyAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.my_app_widget);
        remoteViews.setOnClickPendingIntent(R.id.parentView, pendingIntent);
        SharedPreferences mySharedPref = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String place = mySharedPref.getString("Place", null);
        String date = mySharedPref.getString("Date", null);
        String temp = mySharedPref.getString("Temp", null);
        remoteViews.setTextViewText(R.id.textView,place);
        remoteViews.setTextViewText(R.id.textView1,date);
        remoteViews.setTextViewText(R.id.textView2,temp);

        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
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

