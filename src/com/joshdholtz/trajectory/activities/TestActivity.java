package com.joshdholtz.trajectory.activities;

import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

import com.joshdholtz.trajectory.R;
import com.joshdholtz.trajectory.Trajectory;
import com.joshdholtz.trajectory.Trajectory.Route;
import com.joshdholtz.trajectory.TrajectoryActivity;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.text.TextUtils;
import android.view.Menu;
import android.widget.Toast;

public class TestActivity extends TrajectoryActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				Trajectory.call("/brewery");
			}
		}, 5000);
		
//		Trajectory.call("/brewery/6");
//		Trajectory.call("/brewery/6/beer/7");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_test, menu);
		return true;
	}
	

}
