package com.example.todo;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class ARCompassActivity extends Activity implements LocationListener {
	protected RelativeLayout preView;
	protected ARSurface previewSurface;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		preView = new RelativeLayout(this);
		setContentView(preView);
	}
	
	protected void onResume() {
		super.onResume();
		LayoutParams previewLayoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		
		// creating camera preview surface
		previewSurface = new ARSurface(this);
		preView.addView(previewSurface, 0, previewLayoutParams);
		
		// listening to GPS updates
		LocationManager locationManager = (LocationManager)getSystemService(Activity.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);
	}
	
	protected void onPause() {
		super.onPause();
		
		// stop camera preview
		previewSurface.stop();
		preView.removeView(previewSurface);
		
		// stop GPS monitoring
		LocationManager locationManager = (LocationManager)getSystemService(Activity.LOCATION_SERVICE);
		locationManager.removeUpdates(this);
	}
	
	/**
	 * This is part of the LocationListener interface implementation
	 */
	public void onProviderEnabled(String provider) {
    }
    
	/**
	 * This is part of the LocationListener interface implementation
	 */
    public void onProviderDisabled(String provider) {
    }
    
	/**
	 * This is part of the LocationListener interface implementation
	 */
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
    
	/**
	 * This is part of the LocationListener interface implementation
	 * Location changed, update the compass
	 */
    public void onLocationChanged(Location location) {
    	previewSurface.setPosition(location.getLatitude(), location.getLongitude(), Math.PI * location.getBearing() / 180);
    }
    
	
}
