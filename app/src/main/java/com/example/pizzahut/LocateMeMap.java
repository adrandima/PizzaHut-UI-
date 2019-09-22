package com.example.pizzahut;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
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

import com.example.pizzahut.Service.DirectionsParser;
import com.example.pizzahut.Service.MyDBHandler;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
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
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class LocateMeMap extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener{

    Marker prevMarker;
    LatLng origin = new LatLng(6.904766, 79.950325);
    LatLng dest = new LatLng(6.938591, 79.986942);
    public  double aa ;
    public  double bb ;
    public  double currentLatitude;
    public  double currentLongertude;
    public  String title;
    public  String Topic = "Delivery";
    Button confirmLocation;
    public ArrayList<com.example.pizzahut.Model.Location> loc;
    MyDBHandler db;
    private GoogleMap mMap;
    private static final int LOCATION_REQUEST = 500;
    ArrayList<LatLng> listPoints;

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

            mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                @Override
                public void onMapLongClick(LatLng latLng) {
                    //Reset marker when already 2
                    if (listPoints.size() == 1) {
                        listPoints.clear();
                        mMap.clear();
                    }
                    //Save first point select
                    listPoints.add(latLng);
                    //Create marker
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(latLng);

                    if (listPoints.size() == 1) {
                        //Add first marker to the map
                        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                    }
                    mMap.addMarker(markerOptions);

                    if (listPoints.size() == 1) {
                        //Create the URL to get request from first marker to second marker
                        String url = getRequestUrl(listPoints.get(0));
                        System.out.println("UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU"+url);
                        currentLatitude = listPoints.get(0).latitude;
                        currentLongertude = listPoints.get(0).longitude;
                       /* TaskRequestDirections taskRequestDirections = new TaskRequestDirections();

                        taskRequestDirections.execute(url);*/
                        createMarkers();
                    }
                }
            });



            loc = db.getAllLocation();

            System.out.println("Data base:++++++++++++++"+loc);


            createMarkers();







            init();
            getRequestUrl(origin);

        }
    }


    private void createMarkers(){
        double distance = 0;
        double minValue = 1000;
        int index = 0;

        for (int i=0;i<loc.size();i++){
            distance = distance(loc.get(i).getLocation().latitude,loc.get(i).getLocation().longitude,currentLatitude,currentLongertude);
            System.out.println("Data base:++++++++++++++"+loc.get(i).getName());
            if(distance < minValue){
                minValue = distance;
                index = i;
            }
        }
        System.out.println(index);
        for (int a = 0;a<loc.size();a++){
            if(a==index){
                aa = loc.get(a).getLocation().latitude;
                bb = loc.get(a).getLocation().longitude;
                title = loc.get(a).getName();
                mMap.addMarker(new MarkerOptions()
                        .position(loc.get(a).getLocation())
                        .title(loc.get(a).getName())
                        .rotation((float) 3.5)
                        .icon(BitmapDescriptorFactory
                                .defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                );
            }else{
                mMap.addMarker(new MarkerOptions()
                        .position(loc.get(a).getLocation())
                        .title(loc.get(a).getName())
                        .rotation((float) 3.5)
                );
            }

        }
    }

    private static final String TAG = "DirectionPage";

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
    // private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private PlaceAutocompleteAdapter mPlaceAutocompleteAdapter;
    private GoogleApiClient mGoogleApiClient;
    private AutoCompleteTextView mSearchText;
    Dialog myDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction_page);

        // mSearchText = (AutoCompleteTextView) findViewById(R.id.search_Mylocation);
        db = new MyDBHandler(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        db.insertDate();
        mGps = (ImageView) findViewById(R.id.ic_gps);
        listPoints = new ArrayList<>();
        confirmLocation = (Button) findViewById(R.id.btnGetDirection);
        // getDeviceLocation();
        confirmLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //myDialog = new Dialog(DirectionPage.this);
                double distanceKm;
                distanceKm = distance(currentLatitude, currentLongertude, aa, bb);
                if (distanceKm>=100 || distanceKm <= 0){
                    Toast.makeText(LocateMeMap.this, "Please select location", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onPlaceSelected: Null LatLng");
                }else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(LocateMeMap.this);

                    LayoutInflater inflater = getLayoutInflater();
                    View dialogView = inflater.inflate(R.layout.activity_delivery_confirmation, null);
                    builder.setView(dialogView);

                    TextView locationName = (TextView) dialogView.findViewById(R.id.locationName);
                    TextView endLocation = (TextView) dialogView.findViewById(R.id.endLocation);
                    TextView distance = (TextView) dialogView.findViewById(R.id.distance);
                    final AlertDialog dialog = builder.create();
                    locationName.setText("Your Location");
                    DirectionPage.destinationLocationName = title;
                    endLocation.setText(title);
                    distance.setText(String.format("%.2f KM", distanceKm));
                    // Set the custom layout as alert dialog view
                    dialog.show();

                }

            }
        });


        //mSearchText = (AutoCompleteTextView) findViewById(R.id.input_search);

        getLocationPermission();
        //   init();
        // getLocationPermission();
        initMap();
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



    private void init(){
        Log.d(TAG, "init: initializing");

        Log.d(TAG, "init: " + mLocationPermissionsGranted);

        if (mLocationPermissionsGranted){

            if (!Places.isInitialized()) {
                Places.initialize(getApplicationContext(), "AIzaSyDhzdQ-XK_mfTBQye5L5I6XYVKWl-FAeT8");
            }

            // Initialize the AutocompleteSupportFragment.
            AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                    getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

            autocompleteFragment.setCountry("PH");

  /*          autocompleteFragment.setLocationRestriction(RectangularBounds.newInstance(
                    new LatLng(7.8731, 80.7718),
                    new LatLng(7.9731, 80.9718)));
*/
            autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS));

            autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {

                @Override
                public void onPlaceSelected(@NonNull Place place) {
                    /* TODO: Get info about the selected place. */
                    String placeName = place.getName();
                    LatLng placeLatLng = place.getLatLng();
                    String placeAddress = place.getAddress();

                    if (placeAddress.contains("General Santos City")){
                        Log.d(TAG, "onPlaceSelected: Moving to " + placeName + " @ " + placeLatLng + " on " + placeAddress);

                        if (placeLatLng != null) {
                            moveCamera(placeLatLng, DEFAULT_ZOOM, placeName);
                            Toast.makeText(LocateMeMap.this, "Moving to " + placeName, Toast.LENGTH_SHORT).show();
                            //geoLocate();
                            //                            distanceBetween();
                        }
                        else {
                            Toast.makeText(LocateMeMap.this, "Error: cant find Location", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "onPlaceSelected: Null LatLng");
                        }
                    }
                    else{
                        Toast.makeText(LocateMeMap.this, "Please choose a location in Gensan", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onError(@NonNull Status status) {
                    // TODO: Handle the error.
                    Toast.makeText(LocateMeMap.this, "Error: " + status, Toast.LENGTH_SHORT).show();
                }
            });

        }



        mGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked gps icon");
                getDeviceLocation();
                /*Intent i = new Intent(getApplicationContext(), DirectionPage.class);
                startActivity(i);*/
            }
        });

        hideSoftKeyboard();
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
                            currentLatitude = currentLocation.getLatitude();
                            currentLongertude = currentLocation.getLongitude();
                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()),
                                    DEFAULT_ZOOM,
                                    "My Location");
                            // getAddress(currentLocation.getLatitude(),currentLocation.getLongitude());

                        }else{
                            Log.d(TAG, "onComplete: current location is null");
                            Toast.makeText(LocateMeMap.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }catch (SecurityException e){
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage() );
        }
    }

    private void moveCamera(LatLng latLng, float zoom, String title){


        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

        if(!title.equals("My Location")){
            MarkerOptions options = new MarkerOptions()
                    .position(latLng)
                    .title(title);
            mMap.addMarker(options);
        }

        hideSoftKeyboard();
    }

    private void initMap(){
        Log.d(TAG, "initMap: initializing map");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(LocateMeMap.this);
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



    private void hideSoftKeyboard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }


    private String getRequestUrl(LatLng origin) {
        //Value of origin
        String str_org = "origin=" + origin.latitude +","+origin.longitude;
        //Value of destination
        String str_dest = "destination=" + dest.latitude+","+dest.longitude;
        //Set value enable the sensor
        String sensor = "sensor=false";
        //Mode for find direction
        String mode = "mode=driving";
        //Build the full param
        String param = str_org +"&" + str_dest + "&" +sensor+"&" +mode;
        //Output format
        String output = "json";
        //Create url to request
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + param;
        return url;
    }

    private String requestDirection(String reqUrl) throws IOException {
        String responseString = "";
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        try{
            URL url = new URL(reqUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            //Get the response result
            inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuffer stringBuffer = new StringBuffer();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }

            responseString = stringBuffer.toString();
            bufferedReader.close();
            inputStreamReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            httpURLConnection.disconnect();
        }
        return responseString;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case LOCATION_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mMap.setMyLocationEnabled(true);
                }
                break;
        }
    }

    public class TaskRequestDirections extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String responseString = "";
            try {
                responseString = requestDirection(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  responseString;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Parse json here
            TaskParser taskParser = new TaskParser();
            taskParser.execute(s);
        }
    }

    public class TaskParser extends AsyncTask<String, Void, List<List<HashMap<String, String>>> > {

        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... strings) {
            JSONObject jsonObject = null;
            List<List<HashMap<String, String>>> routes = null;
            try {
                jsonObject = new JSONObject(strings[0]);
                DirectionsParser directionsParser = new DirectionsParser();
                routes = directionsParser.parse(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> lists) {
            //Get list route and display it into the map

            ArrayList points = null;

            PolylineOptions polylineOptions = null;

            for (List<HashMap<String, String>> path : lists) {
                points = new ArrayList();
                polylineOptions = new PolylineOptions();

                for (HashMap<String, String> point : path) {
                    double lat = Double.parseDouble(point.get("lat"));
                    double lon = Double.parseDouble(point.get("lon"));

                    points.add(new LatLng(lat,lon));
                }

                polylineOptions.addAll(points);
                polylineOptions.width(15);
                polylineOptions.color(Color.BLUE);
                polylineOptions.geodesic(true);
            }

            if (polylineOptions!=null) {
                mMap.addPolyline(polylineOptions);
            } else {
                Toast.makeText(getApplicationContext(), "Direction not found!", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
