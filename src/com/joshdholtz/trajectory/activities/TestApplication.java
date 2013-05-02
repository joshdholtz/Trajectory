package com.joshdholtz.trajectory.activities;

import java.util.regex.Pattern;

import com.joshdholtz.trajectory.Trajectory;

import android.app.Application;

public class TestApplication extends Application {

	public void onCreate() {
		Trajectory.registerForRoute(BreweryListActivity.class, "/brewery");
		Trajectory.registerForRoute(BreweryActivity.class, Pattern.compile("^/brewery/(\\d+)$"), new String[]{"brewery_id"});
		Trajectory.registerForRoute(BeerActivity.class, Pattern.compile("^/brewery/(\\d+)/beer/(\\d+)$"), new String[]{"brewery_id", "beer_id"});
	}
	
}
