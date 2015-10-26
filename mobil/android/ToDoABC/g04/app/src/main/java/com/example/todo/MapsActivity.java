package com.example.todo;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.todo.data.ToDo;
import com.example.todo.datahandler.DataManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private static String TAG = "MapsActivity";
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        long toDoId = getIntent().getLongExtra("id", 0);
        ToDo todo =  DataManager.getInstance().getTodo(toDoId);
        Log.i(TAG, "name: " + todo.getName() +
                    " long: " + todo.getLongitude() +
                    " lat: " + todo.getLatitude());

        // Add a marker and move the camera
        try {
            LatLng latLng = new LatLng(
                    Double.parseDouble(todo.getLatitude()),
                    Double.parseDouble(todo.getLongitude()));
            mMap.addMarker(new MarkerOptions().position(latLng).title(todo.getName()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        } catch (Exception e) {
            Toast.makeText(this, "No GPS coordinates.", Toast.LENGTH_LONG).show();
        }
    }
}
