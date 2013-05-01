package com.joshdholtz.trajectory.activities;

import java.util.Timer;
import java.util.TimerTask;

import com.joshdholtz.trajectory.R;
import com.joshdholtz.trajectory.Trajectory;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

public class BeerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beer);
		
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				Trajectory.call("/brewery/6/beer/7");
			}
		}, 5000);
	}
	
}
