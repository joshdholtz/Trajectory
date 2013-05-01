package com.joshdholtz.trajectory.activities;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.joshdholtz.trajectory.R;
import com.joshdholtz.trajectory.Trajectory;
import com.joshdholtz.trajectory.Trajectory.Route;

import android.os.Bundle;
import android.app.Activity;
import android.text.TextUtils;
import android.view.Menu;
import android.widget.Toast;

public class TestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		
		Trajectory.setRoute("/brewery", new Route() {

			@Override
			public void onRoute(String route, List<String> routeParams, Map<String, String> queryParams) {
				Toast.makeText(TestActivity.this, "Called route - " + route, Toast.LENGTH_SHORT).show();
			}
			
		});
		
		Trajectory.setRoute(Pattern.compile("^/brewery/(\\d+)$"), new Route() {

			@Override
			public void onRoute(String route, List<String> routeParams, Map<String, String> queryParams) {
				Toast.makeText(TestActivity.this, "Called route - " + route  + ", Route params - " + TextUtils.join(",", routeParams), Toast.LENGTH_SHORT).show();
			}
			
		});
		
		Trajectory.setRoute(Pattern.compile("^/brewery/(\\d+)/beer/(\\d+)$"), new Route() {

			@Override
			public void onRoute(String route, List<String> routeParams, Map<String, String> queryParams) {
				Toast.makeText(TestActivity.this, "Called route - " + route  + ", Route params - " + TextUtils.join(",", routeParams), Toast.LENGTH_SHORT).show();
			}
			
			
		});
		
		Trajectory.call("/brewery");
		Trajectory.call("/brewery/6");
		Trajectory.call("/brewery/6/beer/7");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_test, menu);
		return true;
	}
	

}
