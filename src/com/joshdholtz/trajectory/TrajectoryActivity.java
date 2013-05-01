package com.joshdholtz.trajectory;

import android.app.Activity;
import android.os.Bundle;

public class TrajectoryActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Trajectory.setCurrentActivityContext(this);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		Trajectory.setCurrentActivityContext(this);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	
}
