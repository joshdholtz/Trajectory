# Trajectory

Trajectory is an Android routing library.

## Basic example on setting up routes

````java

protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_test);

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

````
