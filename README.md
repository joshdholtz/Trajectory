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


## Example for registering Activities for routes

#### 1. - Extend Application to register routes
````java

public class TestApplication extends Application {

	public void onCreate() {
		Trajectory.registerForRoute(BreweryListActivity.class, "/brewery");
		Trajectory.registerForRoute(BreweryActivity.class, Pattern.compile("^/brewery/(\\d+)$"), new String[]{"brewery_id"});
		Trajectory.registerForRoute(BeerActivity.class, Pattern.compile("^/brewery/(\\d+)/beer/(\\d+)$"), new String[]{"brewery_id", "beer_id"});
	}
	
}

````

#### 2. - Extend TrajectoryActivity on all your activites
Note: Extending TrajectoryActivity does nothing more than remember the current Activities "context" so Trajectory can start an Activity
````java

public class BreweryListActivity extends TrajectoryActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_brewery_list);
		
		// Gets all passed in information from the registered TrajectoryActivity intent
		String route = this.getIntent().getStringExtra(Trajectory.INTENT_ROUTE);
		HashMap<String, String> params = (HashMap<String, String>) this.getIntent().getSerializableExtra(Trajectory.INTENT_ROUTE_PARAMS);
		HashMap<String, String> queryParams = (HashMap<String, String>) this.getIntent().getSerializableExtra(Trajectory.INTENT_QUERY_PARAMS);
		
		Toast.makeText(this, route, Toast.LENGTH_SHORT).show();
	}
	
}

````

#### 3. - Example AndroidManifest.xml (set the <application android:name />)
````xml

<application
    android:name="com.joshdholtz.trajectory.activities.TestApplication"
    android:allowBackup="true"
    android:icon="@drawable/ic_launcher"
    android:label="@string/app_name"
    android:theme="@style/AppTheme" >
    
    <activity
        android:name="com.joshdholtz.trajectory.activities.TestActivity"
        android:label="@string/app_name" >
        <intent-filter>
            <action android:name="android.intent.action.MAIN" />

            <category android:name="android.intent.category.LAUNCHER" />
        </intent-filter>
    </activity>
    
    <activity
        android:name="com.joshdholtz.trajectory.activities.BreweryActivity"
        android:label="@string/app_name" >
    </activity>
    
    <activity
        android:name="com.joshdholtz.trajectory.activities.BreweryListActivity"
        android:label="@string/app_name" >
    </activity>
    
    <activity
        android:name="com.joshdholtz.trajectory.activities.BeerActivity"
        android:label="@string/app_name" >
    </activity>
    
</application>

````

#### 4. - Call your route from somewhere
````java

// Starts the BreweryActivity.java
Trajectory.call("/brewery");

````
