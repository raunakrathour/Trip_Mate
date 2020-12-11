package com.groupname.tripmate;

import android.app.Application;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;


import java.util.ArrayList;


public class FirstClass extends Application {
    public static final String APPLICATION_ID = "F5F17969-D7F7-0120-FF01-5F52D56F7700";
    public static final String API_KEY = "CC6214F4-CC69-4D8F-9F0D-9A70E9B6EE8C";
    public static final String SERVER_URL = "https://api.backendless.com";


    public static BackendlessUser user;
    public static ArrayList<bus> busses;

    @Override
    public void onCreate() {
        super.onCreate();
        Backendless.setUrl( SERVER_URL );
        Backendless.initApp( getApplicationContext(),
                APPLICATION_ID,
                API_KEY );

    }
}
