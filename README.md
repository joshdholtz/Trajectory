# Trajectory

````java

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

Trajectory.call("/brewery/6");
Trajectory.call("/brewery/6/beer/7");

````
