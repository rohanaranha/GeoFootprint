package com.collective.geofootprint;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity{

	  private LocationManager locationManager;
	  private String provider;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		LocationManager locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        LocationListener locListener = new MyLocationListener();
        locManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 35000, 10,locListener);
    
	}
	
	/* Class My Location Listener */

    public class MyLocationListener implements LocationListener
    {
    	TextView currentLocationTxtView = (TextView) findViewById(R.id.currentLocationTxtView);
    	
        @Override
        public void onLocationChanged(Location loc){
            Log.d("tag", "Finding Latitude");
            double lat = loc.getLatitude();
            Log.d("tag", "Lat: "+String.valueOf(lat));
            Log.d("tag", "Finding Longitude");
            double lon = loc.getLongitude();
            Log.d("tag", "Lon: "+String.valueOf(lon));
            String Text = "My current location is: " +
            "\nLatitude = " + lat +
            "\nLongitude = " + lon;

            // Display location
            currentLocationTxtView.setText(Text);
        }

        @Override
        public void onProviderDisabled(String provider){
            Toast.makeText(getApplicationContext(), "Gps Disabled", Toast.LENGTH_SHORT ).show();
        }

        @Override
        public void onProviderEnabled(String provider){
            Toast.makeText(getApplicationContext(), "Gps Enabled", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras){

        }
    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
