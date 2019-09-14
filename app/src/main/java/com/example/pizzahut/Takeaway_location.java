package com.example.pizzahut;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.pizzahut.Service.Distance;
import com.example.pizzahut.Service.MyDBHandler;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;


public class Takeaway_location extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener{

        Marker prevMarker;
        MyDBHandler db;
    public double aa ;
    public double bb ;
    public double currentLatitude;
    public double currentLongertude;
    public String title;
    public String Topic = "Delivery";
    private Button btnGetDirection;
    ArrayList<com.example.pizzahut.Model.Location> loc;
@Override
public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        }


@Override
public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(this, "Map is Ready", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onMapReady: map is ready");
        mMap = googleMap;

        if (mLocationPermissionsGranted) {
            getDeviceLocation();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);


        loc = db.getAllLocation();

            for (int a = 0;a<loc.size();a++){


                    mMap.addMarker(new MarkerOptions()
                            .position(loc.get(a).getLocation())
                            .title(loc.get(a).getName())
                            .rotation((float) 3.5)
                    );


            }




        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

@Override
public boolean onMarkerClick(Marker marker) {

            aa = marker.getPosition().latitude;
            bb = marker.getPosition().longitude;
            title = marker.getTitle();


        if (prevMarker != null) {
        //Set prevMarker back to default color
        prevMarker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        Toast.makeText(Takeaway_location.this, "Clicked:"+marker.getTitle(), Toast.LENGTH_SHORT).show();
        }

        //leave Marker default color if re-click current Marker
        if (!marker.equals(prevMarker)) {
        marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        prevMarker = marker;
        }
        prevMarker = marker;
        return false;
        }

        });


        }
        }

private static final String TAG = "Takeaway_location";

private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
private static final float DEFAULT_ZOOM = 15f;
private static final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(
        new LatLng(-40, -168), new LatLng(71, 136));


//widgets
//  private AutoCompleteTextView mSearchText;
private ImageView mGps;

//vars
private Boolean mLocationPermissionsGranted = false;
private GoogleMap mMap;
private FusedLocationProviderClient mFusedLocationProviderClient;
private PlaceAutocompleteAdapter mPlaceAutocompleteAdapter;
private GoogleApiClient mGoogleApiClient;
private AutoCompleteTextView mSearchText;
        Dialog myDialog;
    ImageView imageView;
@Override
protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takeaway_location);

        // mSearchText = (AutoCompleteTextView) findViewById(R.id.search_Mylocation);
        mGps = (ImageView) findViewById(R.id.ic_gps1);
        Distance distance = new Distance();

    db = new MyDBHandler(this);

    db.insertDate();
     aa =0;
     bb =0;
     currentLatitude=0;
     currentLongertude=0;
    //String title;
        Topic = "Takeaway";
    btnGetDirection = (Button) findViewById(R.id.btnGetDirection);
    btnGetDirection.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //myDialog = new Dialog(DirectionPage.this);
            double distanceKm;
            distanceKm = distance(currentLatitude, currentLongertude, aa, bb);

            if (distanceKm>=100 || distanceKm <= 0){
                Toast.makeText(Takeaway_location.this, "Please select location", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onPlaceSelected: Null LatLng");
            }else {
                AlertDialog.Builder builder = new AlertDialog.Builder(Takeaway_location.this);

                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.activity_tackeaway_confirmation, null);
                builder.setView(dialogView);

                TextView locationName = (TextView) dialogView.findViewById(R.id.locationName);
                TextView endLocation = (TextView) dialogView.findViewById(R.id.endLocation);
                TextView distance = (TextView) dialogView.findViewById(R.id.distance);

                final AlertDialog dialog = builder.create();
                locationName.setText("Your Location");
                endLocation.setText(title);
                distance.setText(String.format("%.2f KM", distanceKm));
                // Set the custom layout as alert dialog view
                dialog.show();
            }


        }
    });




    //mSearchText = (AutoCompleteTextView) findViewById(R.id.input_search);
        mGps.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            getDeviceLocation();
        }
         });
        getLocationPermission();
        //   init();


        }

    public void back(View view){


        Intent intent = new Intent(this,DirectionPage.class);
        startActivity(intent);
    }

    public void openMain(View view){


        Intent intent = new Intent(this,MainMenu.class);
        startActivity(intent);
    }

    public double distance(double lat1, double lng1, double lat2, double lng2) {

        double earthRadius = 6371;//3958.75; // in miles, change to 6371 for kilometer output

        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);

        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);

        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        double dist = earthRadius * c;

        return dist; // output distance, in MILES
    }

    private void getDeviceLocation(){
        Log.d(TAG, "getDeviceLocation: getting the devices current location");

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try{
            if(mLocationPermissionsGranted){

                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            Log.d(TAG, "onComplete: found location!");
                            Location currentLocation = (Location) task.getResult();

                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()),
                                    DEFAULT_ZOOM);

                        }else{
                            Log.d(TAG, "onComplete: current location is null");
                            Toast.makeText(Takeaway_location.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }catch (SecurityException e){
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage() );
        }
    }

    private void moveCamera(LatLng latLng, float zoom){
        currentLatitude = latLng.latitude;
        currentLongertude =latLng.longitude;
        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }

    private void initMap(){
        Log.d(TAG, "initMap: initializing map");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(Takeaway_location.this);
    }

    private void getLocationPermission(){
        Log.d(TAG, "getLocationPermission: getting location permissions");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationPermissionsGranted = true;
                initMap();
            }else{
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        }else{
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: called.");
        mLocationPermissionsGranted = false;

        switch(requestCode){
            case LOCATION_PERMISSION_REQUEST_CODE:{
                if(grantResults.length > 0){
                    for(int i = 0; i < grantResults.length; i++){
                        if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
                            mLocationPermissionsGranted = false;
                            Log.d(TAG, "onRequestPermissionsResult: permission failed");
                            return;
                        }
                    }
                    Log.d(TAG, "onRequestPermissionsResult: permission granted");
                    mLocationPermissionsGranted = true;
                    //initialize our map
                    initMap();
                }
            }
        }
    }
}


