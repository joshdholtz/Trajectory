package com.joshdholtz.trajectory.activities;

import java.util.Timer;
import java.util.TimerTask;

import com.joshdholtz.trajectory.R;
import com.joshdholtz.trajectory.Trajectory;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

public class BreweryListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_brewery_list);
		
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				Trajectory.call("/brewery/6");
			}
		}, 5000);
	}
	
}
