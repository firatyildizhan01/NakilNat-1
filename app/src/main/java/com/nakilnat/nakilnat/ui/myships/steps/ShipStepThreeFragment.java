package com.nakilnat.nakilnat.ui.myships.steps;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.ui.BaseFragment;
import com.nakilnat.nakilnat.ui.profile.map.directionhelpers.FetchURL;
import com.nakilnat.nakilnat.ui.profile.map.directionhelpers.TaskLoadedCallBack;


public class ShipStepThreeFragment extends BaseFragment implements OnMapReadyCallback, TaskLoadedCallBack, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    EditText purchaseAdress, deliveryAdress, drive, contact;
    Button next, findLocation;
    RelativeLayout mapLayout;
    GoogleMap map;
    private Polyline currentPolyline;
    private LatLng KMaras, Kayseri, Example;
    private FusedLocationProviderClient mLocationClient;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_ships_steps_three);
        bottomBarSetup(R.id.bottomMyShipping);
        initTopBarContents("Taşımalarım");
        initScreen();
    }

    private void initScreen() {
        purchaseAdress = (EditText) findViewById(R.id.my_ships_adress_purchase_edt);
        deliveryAdress = (EditText) findViewById(R.id.my_ships_adress_delivery_edt);
        drive = (EditText) findViewById(R.id.my_ships_drive_edt);
        contact = (EditText) findViewById(R.id.my_ships_contact_edt);
        mapLayout = (RelativeLayout) findViewById(R.id.ships_map_layout);

        purchaseAdress.setText("Antalya/Kızılay");
        deliveryAdress.setText("Antalya/Elmalı");
        drive.setText("Hasan Solmaz");
        contact.setText("05121233456");

        next = (Button) findViewById(R.id.ships_approve_button);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Onay adımına geçiliyor.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ShipStepThreeFragment.this, ShipStepFourFragment.class);
                startActivity(intent);

            }
        });

        findLocation = (Button) findViewById(R.id.ships_find_location);

        findLocation.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                findLocation.setVisibility(View.GONE);
                next.setVisibility(View.VISIBLE);
                mapLayout.setVisibility(View.VISIBLE);
                mLocationClient.getLastLocation().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Location location = task.getResult();
                        System.out.println(task.getResult());
                        if(task.getResult() == null) {
                            Toast.makeText(ShipStepThreeFragment.this, "GPS açık değildir!", Toast.LENGTH_LONG).show();
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
        Example = new LatLng(36.734802, 36.467987);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.shipsMap);
        mapFragment.getMapAsync(this);

        new FetchURL(ShipStepThreeFragment.this).execute(getUrl(Kayseri, KMaras, "driving"), "driving");
    }

    private void gotoLocation(double latitude, double longitude) {
        LatLng latLng = new LatLng(latitude, longitude);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 12);
        map.moveCamera(cameraUpdate);
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        map.addMarker(new MarkerOptions().position(KMaras).title("Kahramanmaraş").icon(BitmapDescriptorFactory.fromResource(R.drawable.location_start)));
        map.addMarker(new MarkerOptions().position(Kayseri).title("Kayseri").icon(BitmapDescriptorFactory.fromResource(R.drawable.location_end)));
        map.addMarker(new MarkerOptions().position(Example).title("Tır konumu").icon(BitmapDescriptorFactory.fromResource(R.drawable.location_current)));
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(KMaras, 6);
        map.moveCamera(cameraUpdate);
        //map.setMyLocationEnabled(true);

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
        currentPolyline.setColor(Color.BLUE);
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
}