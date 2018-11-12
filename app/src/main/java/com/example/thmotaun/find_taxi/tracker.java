package com.example.thmotaun.find_taxi;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.UserHandle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by thmotaun on 2018/01/07.
 */

public class tracker extends Service implements LocationListener {

    private final Context context;

    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    boolean canGetLocation = false;

    Location location;
    protected LocationManager location_manager;

    public tracker(Context context) {
        this.context = context;
    }

    public Location getLocation() {
        try {
            location_manager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
            isGPSEnabled = location_manager.isProviderEnabled(location_manager.GPS_PROVIDER);
            isNetworkEnabled = location_manager.isProviderEnabled(location_manager.NETWORK_PROVIDER);

            if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
               if (location == null) {
                   if (isGPSEnabled) {
                       location_manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 10, this);
                       if (location_manager != null) {
                           location = location_manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                       }
                   } else if (isNetworkEnabled) {
                       location_manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 10, this);
                       if (location_manager != null) {
                           location = location_manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                       }
                   }
               }
            }
        } catch(Exception e) {

        }
        return location;
    }

    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String Provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public IBinder onBind(Intent arg0) {
        return null;
    }
}
