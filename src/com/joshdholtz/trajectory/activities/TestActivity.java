package com.joshdholtz.trajectory.activities;

import java.util.HashMap;
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
		
		// Used for registered TrajectoryActivity testing
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				Trajectory.call("/brewery");
			}
		}, 5000);
	
		addRoutes();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_test, menu);
		return true;
	}
	
	private void addRoutes() {
		
		// Sets a route that excutes when Trajectory.call("/route") is called
		Trajectory.setRoute("/route", new Route() {

			@Override
			public void onRoute(String route, HashMap<String, String> routeParams, HashMap<String, String> queryParams) {
				Toast.makeText(TestActivity.this, route + " - " + routeParams.toString() + " - " + queryParams.toString(), Toast.LENGTH_SHORT).show();
			}
			
		});
		
		// Sets a route that executes when Trajectory.call("/route/N") is called where N is any integer
		Trajectory.setRoute(Pattern.compile("^/route/(\\d+)$"), new Route() {

			@Override
			public void onRoute(String route, HashMap<String, String> routeParams, HashMap<String, String> queryParams) {
				Toast.makeText(TestActivity.this, route + " - " + routeParams.toString() + " - " + queryParams.toString(), Toast.LENGTH_SHORT).show();
			}
			
		});
		
		// Sets a route that executes when Trajectory.call("/route/N/subroute/M") is called where N and M are any integers
		Trajectory.setRoute(Pattern.compile("^/route/(\\d+)/subroute/(\\d+)$"), new String[]{"route_id", "subroute_id"}, new Route() {

			@Override
			public void onRoute(String route, HashMap<String, String> routeParams, HashMap<String, String> queryParams) {
				Toast.makeText(TestActivity.this, route + " - " + routeParams.toString() + " - " + queryParams.toString(), Toast.LENGTH_SHORT).show();
			}
			
		});
		
	}
	
	public void someOnClickMethod() {
	    Trajectory.call("/route");
	}

	public void someOtherOnClickMethod() {
	    Trajectory.call("/route/6");
	}

	public void andOtherOnClickMethod() {
	    Trajectory.call("/route/6/subroute/7");
	}

}
