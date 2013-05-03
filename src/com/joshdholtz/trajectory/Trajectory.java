package com.joshdholtz.trajectory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class Trajectory {
	
	Context context;
	
	Map<String, Route> routes;
	List<PatternRoute> patternRoutes;

	private Trajectory() {
		super();
		routes = new HashMap<String, Route>();
		patternRoutes = new ArrayList<PatternRoute>();
	}
	
	private static Trajectory getInstance() {
		return LazyHolder.instance;
	}

	private static class LazyHolder {
		private static Trajectory instance = new Trajectory();
	}
	
	public static void setCurrentActivityContext(Context context) {
		synchronized (LazyHolder.instance) {
			Trajectory.getInstance().context = context;
		}
	}
	
	public static void removeCurrentActivityContext(Context context) {
		synchronized (LazyHolder.instance) {
			if (Trajectory.getInstance().context == context) {
				Trajectory.getInstance().context = null;
			}
		}
	}
	
	public static Context getCurrentActivityContext() {
		synchronized (LazyHolder.instance) {
			return Trajectory.getInstance().context;
		}
	}
	
	public static void setRoute(String routePattern, Route route) {
		Trajectory.getInstance()._setRoute(routePattern, route);
	}
	
	private void _setRoute(String routePattern, Route route) {
		routes.put(routePattern, route);
	}
	
	public static void setRoute(Pattern routePattern, Route route) {
		Trajectory.getInstance()._setRoute(routePattern, new String[]{}, route);
	}
	
	public static void setRoute(Pattern routePattern, String[] namedParams, Route route) {
		Trajectory.getInstance()._setRoute(routePattern, namedParams, route);
	}
	
	private void _setRoute(Pattern routePattern, String[] namedParams, Route route) {
		patternRoutes.add(new PatternRoute(routePattern, route, namedParams));
	}
	
	public static void call(String route) {
		Trajectory.getInstance()._call(route);
	}
	
	private void _call(String routeWithQueryParams) {
		String route = routeWithQueryParams;
		HashMap<String, String> matches = new HashMap<String, String>();
		HashMap<String, String> queryParams = _parseQueryParams(routeWithQueryParams);
		
		int indexOfQuery = routeWithQueryParams.indexOf("?");
		if (indexOfQuery != -1) {
			route = routeWithQueryParams.substring(0, indexOfQuery);
		}
		
		Route r = routes.get(route);
		if (r != null) {
			r.onRoute(route, matches, queryParams);
			return;
		}
		
		for (PatternRoute patternRoute : patternRoutes) {
			Matcher matcher = patternRoute.pattern.matcher(route);
			
			boolean found = false;
			while (matcher.find()) {
				for (int i = 1; i < matcher.groupCount() + 1; ++i) {
					
					String name = String.valueOf(matches.size());
					if (patternRoute.namedParams.length > matches.size()) {
						name = patternRoute.namedParams[matches.size()];
					}
					
					matches.put(name, matcher.group(i));
					
				}
				
				found = true;
			}
			
			if (found) {
				patternRoute.route.onRoute(route, matches, queryParams);
				return;
			}
		}
	}
	
	private HashMap<String, String> _parseQueryParams(String route) {
		HashMap<String, String> queryParams = new HashMap<String, String>();
		
		int indexOfQuery = route.indexOf("?");
		if (indexOfQuery == -1) {
			return queryParams;
		}
		
		String queryParamsStr = route.substring(indexOfQuery + 1);
		String[] queryParamParts = queryParamsStr.split("&");
		for (String queryParamPart : queryParamParts) {
			String key = queryParamPart;
			String value = "";
			int indexOfEquals = queryParamPart.indexOf("=");
			if (indexOfEquals != -1) {
				key = queryParamPart.split("=")[0];
				value = queryParamPart.split("=")[1];
			}
			
			queryParams.put(key, value);
		}
		
		return queryParams;
	}
	
	private class PatternRoute {
		
		Pattern pattern;
		Route route;
		String[] namedParams;
		
		public PatternRoute(Pattern pattern, Route route, String[] namedParams) {
			super();
			this.pattern = pattern;
			this.route = route;
			this.namedParams = namedParams;
		}
		
	}
	
	public abstract static class Route {
		public abstract void onRoute(String route, HashMap<String, String> routeParams, HashMap<String, String> queryParams);
	}
	
	public final static String INTENT_ROUTE = "route";
	public final static String INTENT_ROUTE_PARAMS = "routeParams";
	public final static String INTENT_QUERY_PARAMS = "queryParams";
	
	public static void registerForRoute(final Class<? extends Activity> activity, String route) {
		Trajectory.setRoute(route, new Route() {

			@Override
			public void onRoute(String route, HashMap<String, String> routeParams, HashMap<String, String> queryParams) {
				Trajectory.startActivity(activity, route, routeParams, queryParams);
			}
			
		});
	}
	
	public static void registerForRoute(final Class<? extends Activity> activity, Pattern route) {
		Trajectory.registerForRoute(activity, route, new String[]{});
	}
	
	public static void registerForRoute(final Class<? extends Activity> activity, Pattern route, String[] namedParams) {
		Trajectory.setRoute(route, namedParams, new Route() {

			@Override
			public void onRoute(String route, HashMap<String, String> routeParams, HashMap<String, String> queryParams) {
				Trajectory.startActivity(activity, route, routeParams, queryParams);
			}
			
		});
	};
	
	private static void startActivity(Class<? extends Activity> activity, String route, HashMap<String, String> routeParams, HashMap<String, String> queryParams) {
		Context context = Trajectory.getCurrentActivityContext();
		Intent intent = new Intent(context, activity);
		intent.putExtra(INTENT_ROUTE, route);
		intent.putExtra(INTENT_ROUTE_PARAMS, routeParams);
		intent.putExtra(INTENT_QUERY_PARAMS, queryParams);
		context.startActivity(intent);
	}
	
}
