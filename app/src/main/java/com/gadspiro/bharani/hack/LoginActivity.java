package com.gadspiro.bharani.hack;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gadspiro.bharani.hack.Async.Register;
import com.gadspiro.bharani.hack.Async.Signin;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LoginActivity extends AppCompatActivity {

    EditText mob,pass;
    String mobs,passs;
    TextView signup;

    EditText mobiler,aadhaarr,emailr,passr,repassr,unamer;





    LocationManager locationManager;

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                /*new AlertDialog.Builder(this)
                        .setTitle("Location permission is needed to continue")
                        .setMessage("Allow to continue")
                        .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(LoginActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();*/


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                        LocationListener locationListener = new MyLocationListener();

                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER, 5000, 10, locationListener);                    }

                } else {

                    finish();

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return;
            }

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);





        locationManager= (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);


        if (checkLocationPermission()) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {

                //Request location updates:
                LocationListener locationListener = new MyLocationListener();

                locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
            }
        }

        signup=(TextView) findViewById(R.id.signup);
        mob= (EditText) findViewById(R.id.nume);
        pass= (EditText) findViewById(R.id.passe);
        Button button = (Button) findViewById(R.id.signin);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mobs=mob.getText().toString();
                passs=pass.getText().toString();
                //Boolean valid=Verhoeff.validateVerhoeff(num);
                //Log.d("Validity"," "+valid.toString());


                Signin signin=new Signin(mobs,passs,getApplicationContext());
                signin.execute((Void) null);



                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();


            }
        });





        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                // Get the layout inflater
                LayoutInflater inflater = getWindow().getLayoutInflater();


                // Inflate and set the layout for the dialog



                View dia=inflater.inflate(R.layout.register,null);
                mobiler=(EditText) dia.findViewById(R.id.number);
                unamer=(EditText) dia.findViewById(R.id.uname);

                aadhaarr=(EditText) dia.findViewById(R.id.aadhaar);

                emailr=(EditText) dia.findViewById(R.id.email);
                passr=(EditText) dia.findViewById(R.id.regpassword);
                repassr=(EditText) dia.findViewById(R.id.regpassword2);
                // Pass null as the parent view because its going in the dialog layout
                builder.setView(dia)

                        // Add action buttons
                        .setPositiveButton("Register", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                                // sign in the user ...
                                if(passr.getText().toString().equals(repassr.getText().toString()))
                                    if(Verhoeff.validateVerhoeff(aadhaarr.getText().toString())) {
                                        Log.d("Valid", "true");
                                        Register registercheck=new Register(unamer.getText().toString(),mobiler.getText().toString(),aadhaarr.getText().toString(),emailr.getText().toString(),passr.getText().toString(),getApplicationContext());
                                        registercheck.execute((Void) null);

                                        //startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                    }
                                    else Log.d("Valid","Invalid aadhaar");
                                else Log.d("Valid","Password Mismatch");

                                /*Registercheck registercheck=new Registercheck(name.getText().toString());
                                registercheck.execute((Void) null);*/
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });

                builder.show();






            }
        });

    }








    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location loc) {

            Toast.makeText(
                    getBaseContext(),
                    "Location changed: Lat: " + loc.getLatitude() + " Lng: "
                            + loc.getLongitude(), Toast.LENGTH_SHORT).show();
            String longitude = "Longitude: " + loc.getLongitude();
            String latitude = "Latitude: " + loc.getLatitude();

            String location=""+loc.getLatitude()+","+loc.getLongitude();
            SharedPreferences pref = getSharedPreferences("MyPref", 0); // 0 - for private mode
            SharedPreferences.Editor editor = pref.edit();
                editor.putString("loc",location);

            editor.commit();
            Log.v("location", " "+latitude+"\\,"+longitude);

        /*------- To get city name from coordinates -------- */
            String cityName = null;
            Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
            List<Address> addresses;
            try {
                addresses = gcd.getFromLocation(loc.getLatitude(),
                        loc.getLongitude(), 1);
                if (addresses.size() > 0) {
                    System.out.println(addresses.get(0).getLocality());
                    cityName = addresses.get(0).getLocality();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            String s = longitude + "\n" + latitude + "\n\nMy Current City is: "
                    + cityName;

            Log.d("location city"," "+s);
        }

        @Override
        public void onProviderDisabled(String provider) {}

        @Override
        public void onProviderEnabled(String provider) {}

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {}
    }



}
