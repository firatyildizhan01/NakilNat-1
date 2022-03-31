package com.nakilnat.nakilnat.ui.profile.map;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.SyncStatusObserver;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.view.textclassifier.TextSelection;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.ui.addad.AddAdFragment;
import com.nakilnat.nakilnat.ui.application.ApplicationPageFragment;
import com.nakilnat.nakilnat.ui.profile.ProfilePageFragment;
import com.nakilnat.nakilnat.ui.profile.map.directionhelpers.FetchURL;
import com.nakilnat.nakilnat.ui.profile.map.directionhelpers.TaskLoadedCallBack;


public class MapFragment extends AppCompatActivity implements OnMapReadyCallback, TaskLoadedCallBack, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    CardView sendOffers, incomingOffers;
    TextView topBarText;
    ImageView topBarBack;
    GoogleMap map;
    Button findLocation;
    private Polyline currentPolyline;
    private LatLng KMaras, Kayseri;
    private FusedLocationProviderClient mLocationClient;

    @SuppressLint("MissingPermission")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_location);
        InitSubContents();
        topBarInit();
        findLocation = (Button) findViewById(R.id.find_location_button);
        findLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLocationClient.getLastLocation().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Location location = task.getResult();
                        System.out.println(task.getResult());
                        if(task.getResult() == null) {
                            Toast.makeText(MapFragment.this, "GPS açık değildir!", Toast.LENGTH_LONG).show();
                        }
                        else {
                            gotoLocation(location.getLatitude(), location.getLongitude());
                        }

                    }
                });
            }
        });
        mLocationClient = new FusedLocationProviderClient(this);
        KMaras = new LatLng(37.575275, 36.922821);
        Kayseri = new LatLng(38.734802, 35.467987);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        new FetchURL(MapFragment.this).execute(getUrl(Kayseri, KMaras, "driving"), "driving");

    }

    private void gotoLocation(double latitude, double longitude) {
        LatLng latLng = new LatLng(latitude, longitude);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15);
        map.moveCamera(cameraUpdate);
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        map.addMarker(new MarkerOptions().position(KMaras).title("Kahramanmaraş"));
        map.addMarker(new MarkerOptions().position(Kayseri).title("Kayseri"));
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(KMaras, 8);
        map.moveCamera(cameraUpdate);
        map.setMyLocationEnabled(true);

    }

    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.map_key);
        return url;
    }

    @Override
    public void onTaskDone(Object... values) {
        if (currentPolyline != null)
            currentPolyline.remove();
        currentPolyline = map.addPolyline((PolylineOptions) values[0]);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void InitSubContents() {
        //region initialize sub menus
        sendOffers = (CardView) findViewById(R.id.my_offers_send);
        incomingOffers = (CardView) findViewById(R.id.my_offers_incoming);
    }

    private void topBarInit() {
        topBarText = findViewById(R.id.top_bar_title);
        topBarText.setText("Adres Seç");

        topBarBack = findViewById(R.id.top_bar_back);
        topBarBack.setVisibility(View.VISIBLE);
        topBarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}