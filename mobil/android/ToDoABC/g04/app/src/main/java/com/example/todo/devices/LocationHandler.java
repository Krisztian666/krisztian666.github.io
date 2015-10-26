package com.example.todo.devices;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todo.R;

/**
 * LocationHandler Provides gps coordinates
 */
public class LocationHandler {
    private static final String TAG = "LocationHandler";

    private static LocationHandler instance = new LocationHandler();

    Context context;
    LocationManager locationManager;
    LocationListener locationListener;
    private double longitude;
    private double latitude;

    public static LocationHandler getInstance() {
        return instance;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void startLocationListener(){
        Log.i(TAG, "Start the listener");
        // Acquire a reference to the system Location Manager
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

// Define a listener that responds to location updates
        locationListener = new LocationListener() {

            // Called when the location has changed.
            public void onLocationChanged(Location location) {
                Log.i(TAG,"Location: long:" + location.getLongitude() + " lat:" + location.getLatitude());
                longitude = location.getLongitude();
                latitude = location.getLatitude();
                TextView textViewlong = (TextView) ((Activity) context).findViewById(R.id.gps_long);
                textViewlong.setText(""+ longitude);
                TextView textViewlat = (TextView) ((Activity) context).findViewById(R.id.gps_lat);
                textViewlat.setText(""+ latitude);
            }

            // Called when the provider status changes.
            // This method is called when a provider is unable to fetch a location or
            // if the provider has recently become available after a period of unavailability.
            public void onStatusChanged(String provider, int status, Bundle extras) {}

            // Called when the provider is enabled by the user.
            public void onProviderEnabled(String provider) {}

            //  Called when the provider is disabled by the user.
            public void onProviderDisabled(String provider) {}
        };

// Register the listener
//  Use LocationManager.GPS_PROVIDER or NETWORK_PROVIDER
        try {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        } catch (Exception e) {
            Log.e(TAG, "NETWORK_PROVIDER", e);
            try {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            } catch (Exception e1){
                Toast.makeText(context, "Can not register GPS provider.", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "GPS_PROVIDER", e1);
            }
        }



    }

    public void stopLocationListener(){
        Log.i(TAG, "Remove the listener");
        // Remove the listener
        try {
            locationManager.removeUpdates(locationListener);
        } catch (Exception e) {
            Log.i(TAG, e.getMessage());
        }
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
}
