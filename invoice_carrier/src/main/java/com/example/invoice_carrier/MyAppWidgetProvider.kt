package com.example.invoice_carrier

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews

/****************************************************
 * Copyright (C) Alan Corporation. All rights reserved.
 *
 * Author: AlanLai
 * Create Date: 2020/7/13
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 ****************************************************/

class MyAppWidgetProvider : AppWidgetProvider() {

    //在主線程中執行，如果處理需要花費時間多於10秒，處理應在服務中完成
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }

        Log.e("TAG","koijuijok")
    }

    //當接收廣播時觸發
    override fun onReceive(context: Context?, intent: Intent?) {
    }

    //當第一個 widget被創建時觸發
    override fun onEnabled(context: Context?) {
    }

    //當最後一個widget被刪除時觸發
    override fun onDisabled(context: Context?) {
    }

    //當widget被刪除時觸發
    override fun onDeleted(context: Context?, appWidgetIds: IntArray?) {
    }
}

internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
    val widgetText = context.getString(R.string.appwidget_text)
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.my_appwidget)
    views.setTextViewText(R.id.song_name, widgetText)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}