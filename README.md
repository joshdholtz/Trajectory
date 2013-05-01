# Trajectory

````java

protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_test);

	Trajectory.setRoute("/beer", new Route() {

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
