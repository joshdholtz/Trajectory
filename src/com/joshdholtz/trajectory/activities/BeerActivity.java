package com.joshdholtz.trajectory.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import com.joshdholtz.trajectory.R;
import com.joshdholtz.trajectory.Trajectory;
import com.joshdholtz.trajectory.TrajectoryActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Toast;

public class BeerActivity extends TrajectoryActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beer);
		
		// Gets all passed in information from the registered TrajectoryActivity intent
		String route = this.getIntent().getStringExtra(Trajectory.INTENT_ROUTE);
		HashMap<String, String> params = (HashMap<String, String>) this.getIntent().getSerializableExtra(Trajectory.INTENT_ROUTE_PARAMS);
		HashMap<String, String> queryParams = (HashMap<String, String>) this.getIntent().getSerializableExtra(Trajectory.INTENT_QUERY_PARAMS);
		
		Toast.makeText(this, route + " - " + params.toString(), Toast.LENGTH_SHORT).show();
	}
	
}
