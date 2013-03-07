package com.example.appwidget;

import static android.appwidget.AppWidgetManager.EXTRA_APPWIDGET_ID;
import static android.appwidget.AppWidgetManager.INVALID_APPWIDGET_ID;

import java.util.ArrayList;

import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils.TruncateAt;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.RemoteViews;
import android.widget.TextView;

public class WidgetProviderLarge extends AppWidgetProvider {
	Context context;

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		if (appWidgetIds != null) {
			int N = appWidgetIds.length;

			for (int mAppWidgetId : appWidgetIds) {

				Intent intent = new Intent(context, UpdateWidgetService.class);

				intent.putExtra(EXTRA_APPWIDGET_ID, mAppWidgetId);
				intent.setAction("FROM WIDGET PROVIDER");
				context.startService(intent);
			}

		}

	}

	public static class UpdateWidgetService extends IntentService {
		public UpdateWidgetService() {
			// only for debug purpose
			super("UpdateWidgetService");

		}

		@Override
		protected void onHandleIntent(Intent intent) {
			AppWidgetManager appWidgetManager = AppWidgetManager
					.getInstance(UpdateWidgetService.this);

			int incomingAppWidgetId = intent.getIntExtra(EXTRA_APPWIDGET_ID,
					INVALID_APPWIDGET_ID);

			if (incomingAppWidgetId != INVALID_APPWIDGET_ID) {
				try {
					updateNewsAppWidget(appWidgetManager, incomingAppWidgetId,
							intent);
				} catch (NullPointerException e) {
				}

			}

		}

		public void updateNewsAppWidget(AppWidgetManager appWidgetManager,
				int appWidgetId, Intent intent) {
			Log.v("String package name", this.getPackageName());
			RemoteViews views = new RemoteViews(this.getPackageName(),
					R.layout.layout_appwidget_large);

			appWidgetManager.updateAppWidget(appWidgetId, views);
		}
	}
}
