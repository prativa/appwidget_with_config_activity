package com.example.appwidget;

import static android.appwidget.AppWidgetManager.EXTRA_APPWIDGET_ID;
import static android.appwidget.AppWidgetManager.INVALID_APPWIDGET_ID;
import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.appwidget.WidgetProviderLarge.UpdateWidgetService;

public class ConfigurationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_config_activity);
		setResult(RESULT_CANCELED);

		initListViews();
	}

	public void initListViews() {

		Button okButton = (Button) findViewById(R.id.okButton);
		okButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				handleOkButton();
			}
		});

	}

	private void handleOkButton() {
		showAppWidget();

	}

	int mAppWidgetId;

	private void showAppWidget() {

		mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		if (extras != null) {
			mAppWidgetId = extras.getInt(EXTRA_APPWIDGET_ID,
					INVALID_APPWIDGET_ID);

			AppWidgetProviderInfo providerInfo = AppWidgetManager.getInstance(
					getBaseContext()).getAppWidgetInfo(mAppWidgetId);
			String appWidgetLabel = providerInfo.label;

			Intent startService = new Intent(ConfigurationActivity.this,
					UpdateWidgetService.class);
			startService.putExtra(EXTRA_APPWIDGET_ID, mAppWidgetId);
			startService.setAction("FROM CONFIGURATION ACTIVITY");
			setResult(RESULT_OK, startService);
			startService(startService);

			finish();
		}
		if (mAppWidgetId == INVALID_APPWIDGET_ID) {
			Log.i("I am invalid", "I am invalid");
			finish();
		}

	}

}
