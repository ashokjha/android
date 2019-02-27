package com.example.androidapplication1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.URI;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView;
    Button dialNumber, toast,showIntent, letGetPro, showLocation, openWebPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.welcome_to_android_world);
        dialNumber = findViewById(R.id.dialNumber);
        toast = findViewById(R.id.toast);
        showLocation = findViewById(R.id.showLocation);
        openWebPage = findViewById(R.id.openWebPage);
        showIntent = findViewById(R.id.viewIntent);
        letGetPro = findViewById(R.id.letsGetPro);
        dialNumber.setOnClickListener(this);
        toast.setOnClickListener(this);
        showLocation.setOnClickListener(this);
        openWebPage.setOnClickListener(this);
        showIntent.setOnClickListener(this);
        letGetPro.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialNumber:
                // Dialer is android component
                Intent intent1 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+919831587781"));
                startActivity(intent1);
                break;
            case R.id.toast:
                Intent intent2= new Intent(MainActivity.this,OtherActivity.class);
                startActivity(intent2);
                break;
            case R.id.showLocation:
                //geo:22.4771955,88.4080862?z=15
                // z => zoom position in google map
                Intent intent3 = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:22.4771955,88.4080862?z=15"));
                startActivity(intent3);
                break;
            case R.id.openWebPage:
                Intent intent4 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/ashok-kumar-jha-83706b18/"));
                startActivity(intent4);
                break;
            case R.id.viewIntent:
                // Bring All application using ACTION_VIEW
                Intent intent5 = new Intent(Intent.ACTION_VIEW);
                startActivity(intent5);
                break;
            case R.id.letsGetPro:
                // This happens after defining intent-filter  com.example.androidapplication1.OtherActivity
                // in OtherActivity  manifest
                //Intent intent6 =  new Intent("com.example.androidapplication1.OtherActivity");
                //startActivity(intent6);

                // This happens after defining action with name android.intent.action.VIEW under intent-filter  for
                //  OtherActivity  manifest
                Intent intent7 = new Intent(Intent.ACTION_VIEW);
                intent7.putExtra("paramkey1",12345);
                intent7.putExtra("paramkey2","Parameter 2 value");
                startActivity(intent7);
                break;

        }

    }
}