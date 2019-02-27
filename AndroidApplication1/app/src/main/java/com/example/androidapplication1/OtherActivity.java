package com.example.androidapplication1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class OtherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);


        Toast.makeText(this,"Landed from other app",Toast.LENGTH_LONG).show();
        Bundle bundle = getIntent().getExtras();

        Toast.makeText(this,""+bundle.get("paramkey1"),Toast.LENGTH_LONG).show();
        Toast.makeText(this,""+bundle.get("paramkey2"),Toast.LENGTH_LONG).show();

    }
}
