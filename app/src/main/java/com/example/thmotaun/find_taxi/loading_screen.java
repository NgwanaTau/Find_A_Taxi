package com.example.thmotaun.find_taxi;

import android.*;
import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class loading_screen extends AppCompatActivity {

    private static final String TAG = "loading_screen";
    private static final int ERROR_DIALOG_REQUEST = 9001;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if(isServicesOK()) {
            init();
        }
    }

    private void init() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION) &&
                    ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                ActivityCompat.requestPermissions(loading_screen.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
                ActivityCompat.requestPermissions(loading_screen.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
                runAct();
            } else {
                ActivityCompat.requestPermissions(loading_screen.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
                ActivityCompat.requestPermissions(loading_screen.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
                runAct();
            }
            return;
        } else {
            runAct();
        }
    }

    public boolean isServicesOK()
    {
        Log.d(TAG, "isServicesOK: Checking Google Play Services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(loading_screen.this);

        if(available == ConnectionResult.SUCCESS) {
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            Log.d(TAG, "isServicesOK: Error occured but can be fixed");

            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(loading_screen.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this,"No map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void runAct() {
        Thread welcomeThread = new Thread() {

            @Override
            public void run() {
                try {
                    super.run();
                    sleep(5000);  //Delay of 5 seconds
                } catch (Exception e) {

                } finally {
                    Intent j;
                   // j = new Intent(loading_screen.this, Navigation.class);
                    j = new Intent(loading_screen.this, maps.class);
                    startActivity(j);
                    finish();
                }
            }
        };
        welcomeThread.start();
    }
}
