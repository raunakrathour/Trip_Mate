package com.groupname.tripmate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.geo.GeoCategory;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class TrackLocation extends AppCompatActivity  {
    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;

    Button btnRunning,btnMylocation;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_location);
        btnMylocation = findViewById(R.id.btnMylocation);
        btnRunning = findViewById(R.id.btnRunning);

       /* Backendless.Geo.addCategory("RunningBuses", new AsyncCallback<GeoCategory>() {
            @Override
            public void handleResponse(GeoCategory response) {

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(TrackLocation.this,"Error "+fault.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });*/

       if(FirstClass.user.getProperty("isDriver").equals("1"))
       {
           btnRunning.setText("Set Your Location");
           btnMylocation.setText("Back");

       }
       else
       {
           btnRunning.setText("Running Buses Location");
           btnMylocation.setText("My Location");
       }

        btnMylocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // startActivity(new Intent(TrackLocation.this,MapsActivity.class));
              //  getSupportFragmentManager().beginTransaction().replace(R.id.,new MyAccount_fragment()).commit();


                if(FirstClass.user.getProperty("isDriver").equals("0"))
                {
                    if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED||
                            ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
                    {
                        ActivityCompat.requestPermissions(TrackLocation.this,new String[] {Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},0);
                    }
                    else
                    {
                        Intent intent = new Intent(getBaseContext(), MapsActivity.class);
                        intent.putExtra("ID", "myLocation");
                        startActivity(intent);
                       // startActivity(new Intent(TrackLocation.this,MapsActivity.class));
                    }

                }
                else
                {
                    TrackLocation.super.onBackPressed();
                }
              // TrackLocation.super.onBackPressed();
             }

        });
        btnRunning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new backgroundLocation(getApplicationContext(),TrackLocation.this,getBaseContext()).execute();
            }
        });


    }


}
class backgroundLocation extends AsyncTask<Void, Void, Void> {

    Context c;
    Activity act;
    Context d;
    public backgroundLocation(Context c,Activity act,Context d) {
        this.c =c;
        this.d = d;
        this.act=act;

    }

    @Override


    protected Void doInBackground(Void... voids) {
        //Write button code here.


        if(ContextCompat.checkSelfPermission(c, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED||
                ContextCompat.checkSelfPermission(c, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(act,new String[] {Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},0);
        }
        else
        {
            // Intent intent =new Intent(TrackLocation.this,MapsActivity.class);
            Intent intent = new Intent(d, MapsActivity.class);
            intent.putExtra("ID", "busesLocation");
            act.startActivity(intent);
        }
        return null;
    }
}