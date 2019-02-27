package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button pushMeButton;
    Button pushMeButton2;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pushMeButton = findViewById(R.id.pushmebtn);
        textView = findViewById(R.id.textid);
        pushMeButton2 = findViewById(R.id.pushmebtn2);
  /*      pushMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Push Me Button","Some one clicked  button 1");
                textView.setText("Some one clicked  button 1");
            }
        });
        pushMeButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Push Me Button","Some one clicked  button 2");
                textView.setText("Some one clicked button 2");
            }
        });*/
        pushMeButton.setOnClickListener(this);
        pushMeButton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pushmebtn:
                Log.d("Push Me Button", "Some one clicked  button 1..");
                textView.setText("Some one clicked push me button 1..");
                break;
            case R.id.pushmebtn2:
                Log.d("Push Me Button", "Some one clicked  button 2..");
                textView.setText("Some one clicked push me button 2 ..");
                break;

        }
    }
}
