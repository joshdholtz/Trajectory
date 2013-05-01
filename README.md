# Trajectory

Trajectory is an Android routing library.

````java

protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_test);

	// Sets a route that excutes when Trajectory.call("/brewery") is called
	Trajectory.setRoute("/brewery", new Route() {

		@Override
		public void onRoute(String route, List<String> routeParams, Map<String, String> queryParams) {
			Toast.makeText(TestActivity.this, "Called route - " + route  + ", Route params - " + TextUtils.join(",", routeParams), Toast.LENGTH_SHORT).show();
		}
	});

	// Sets a route that excutes when Trajectory.call("/brewery/N") is called where N is any integer
	Trajectory.setRoute(Pattern.compile("^/brewery/(\\d+)$"), new Route() {

		@Override
		public void onRoute(String route, List<String> routeParams, Map<String, String> queryParams) {
			Toast.makeText(TestActivity.this, "Called route - " + route  + ", Route params - " + TextUtils.join(",", routeParams), Toast.LENGTH_SHORT).show();
		}
	});

	// Sets a route that excutes when Trajectory.call("/brewery/N/beer/M") is called where N and M are any integers
	Trajectory.setRoute(Pattern.compile("^/brewery/(\\d+)/beer/(\\d+)$"), new Route() {

		@Override
		public void onRoute(String route, List<String> routeParams, Map<String, String> queryParams) {
			Toast.makeText(TestActivity.this, "Called route - " + route  + ", Route params - " + TextUtils.join(",", routeParams), Toast.LENGTH_SHORT).show();
		}
	});

}

public void someOnClickMethod() {
	Trajectory.call("/brewery");
}

public void someOtherOnClickMethod() {
	Trajectory.call("/brewery/6");
}

public void andOtherOnClickMethod() {
	Trajectory.call("/brewery/6/beer/7");
}

````
