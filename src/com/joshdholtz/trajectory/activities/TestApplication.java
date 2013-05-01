package com.joshdholtz.trajectory.activities;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.joshdholtz.trajectory.Trajectory;
import com.joshdholtz.trajectory.Trajectory.Route;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

public class TestApplication extends Application {

	public void onCreate() {
		
		Trajectory.setRoute("/brewery", new Route() {

			@Override
			public void onRoute(String route, List<String> routeParams, Map<String, String> queryParams) {
				Toast.makeText(Trajectory.getCurrentActivityContext(), "Called route - " + route  + ", Route params - " + TextUtils.join(",", routeParams), Toast.LENGTH_SHORT).show();
				
				Context context = Trajectory.getCurrentActivityContext();
				Intent intent = new Intent(context, BreweryListActivity.class);
				context.startActivity(intent);
			}
			
		});
		
		Trajectory.setRoute(Pattern.compile("^/brewery/(\\d+)$"), new Route() {

			@Override
			public void onRoute(String route, List<String> routeParams, Map<String, String> queryParams) {
				Toast.makeText(Trajectory.getCurrentActivityContext(), "Called route - " + route  + ", Route params - " + TextUtils.join(",", routeParams), Toast.LENGTH_SHORT).show();
				
				Context context = Trajectory.getCurrentActivityContext();
				Intent intent = new Intent(context, BreweryActivity.class);
				context.startActivity(intent);
			}
			
		});
		
		
		Trajectory.setRoute(Pattern.compile("^/brewery/(\\d+)/beer/(\\d+)$"), new Route() {

			@Override
			public void onRoute(String route, List<String> routeParams, Map<String, String> queryParams) {
				Toast.makeText(Trajectory.getCurrentActivityContext(), "Called route - " + route  + ", Route params - " + TextUtils.join(",", routeParams), Toast.LENGTH_SHORT).show();
				
				Context context = Trajectory.getCurrentActivityContext();
				Intent intent = new Intent(context, BeerActivity.class);
				context.startActivity(intent);
			}
			
			
		});
		
	}
	
}
