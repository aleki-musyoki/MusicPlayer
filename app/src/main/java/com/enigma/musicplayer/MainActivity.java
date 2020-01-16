package com.enigma.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ActivityManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private static final String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    private static final int REQUEST_PERMISSIONS = 12345;

    //Indicates how many permissions are granted
    private static final int PERMISSIONS_COUNT = 1;

    //To check if permissions are granted or not
    private boolean arePermissionsDenied(){
     for (int i = 0; i < PERMISSIONS_COUNT; i++){
         //Requires API level 23
        if (checkSelfPermission(PERMISSIONS[i]) != PackageManager.PERMISSION_GRANTED){
            return true;
        }
     }
        return false;
    }

    //Get the result of whether the user allowed or denied the permission
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (arePermissionsDenied()){
            //Clear users data if permissions are denied and closes the app
            ((ActivityManager) (this.getSystemService(ACTIVITY_SERVICE))).clearApplicationUserData();
            //asks for permission when the user clicks denied and reopens the app
            recreate();
        }else{
            onResume();
        }
    }

    //Initializes the music player
    private boolean isMusicPlayerInit;

    @Override
    protected void onResume(){
        super.onResume();
        //Check if the users SDK is equal to marshmellow (API 23) and if permissions are denied
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && arePermissionsDenied()){
            //Request for permissions
            requestPermissions(PERMISSIONS, REQUEST_PERMISSIONS);
            return;
        }

        //Initialises the music player if it is not initialised
        if (!isMusicPlayerInit){
            //To access the list view from the xml file to be able to input data into it
            final ListView listView = findViewById(R.id.listView);


            isMusicPlayerInit = true;
        }
    }
}

/* VIDEO TUTORIAL = CODE MUSIC PLAYER APP IN ANDROID STUDIO
*  CHANNEL = TECH WITH JAMES
*  RESUME FROM = 31:26  */