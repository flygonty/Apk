package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.BatteryManager;
import android.os.Build;
import android.telephony.TelephonyManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnStart ;
    TextView varText ;
    String info ;
    String strPhoneType = "" ;
    static final int PERMISSION_READ_STATE = 123 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Start(View view) {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        if ( permissionCheck == PackageManager.PERMISSION_GRANTED) {
            MyTelephonyManager() ;
        } // if
        else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_PHONE_STATE},PERMISSION_READ_STATE);
        } // else
    } // Start()

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_READ_STATE :
            {
                if (grantResults.length >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    MyTelephonyManager();
                } // if
                else {
                    Toast.makeText(this,"You don't have required permission",Toast.LENGTH_SHORT).show();
                } // else
            } // case
        } // switch
    } // onRequestPermissionResult()


    private void MyTelephonyManager() {
        private SensorManager sensorManager;
        private Sensor sensor; // sensor

        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = registerReceiver(null,ifilter); // battery

        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE) ;
        int phoneType = manager.getPhoneType() ;
        

        switch (phoneType) {
            case (TelephonyManager.PHONE_TYPE_CDMA) :
                strPhoneType = "CDMA" ;
                break;
            case (TelephonyManager.PHONE_TYPE_GSM) :
                strPhoneType = "GSM" ;
                break;
            case (TelephonyManager.PHONE_TYPE_NONE) :
                strPhoneType = "NONE" ;
                break;
        } // switch
        // phone status
        String PhoneType = strPhoneType;
        String IMEINumber = manager.getImei();
        String subscribeID = manager.getSubscriberId();
        String phoneNumber = manager.getLine1Number();
        String SimOperatorName = manager.getSimOperatorName();

        // Systems Properties
        String HardwareName = Build.HARDWARE;

        // Battery Level
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        float batteryPct = level / (float)scale;

        String battery = String.valueOf(batteryPct);

        // Motion Sensor

        info = "Phone Details: \n" ;
        info+="\n Phone Network Type " +PhoneType;
        info+="\n IMEI Number:  " +IMEINumber;
        info+="\n IMSI Number: " +subscribeID;
        info+="\n phoneNumber: " +phoneNumber;
        info+="\nSimOperatorName "+SimOperatorName;

        info+="\nHardware: "+HardwareName ; // name of android5.0 is ranchu

        info+="\nBattery: "+battery;

        btnStart = (Button) findViewById(R.id.idBtnstart);
        varText = (TextView) findViewById(R.id.idTextView);
        varText.setText(info);
    } // MyTelephonyManager()

}

