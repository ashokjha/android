package com.ashu.external.androidloginhttp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Welcome extends AppCompatActivity implements View.OnClickListener {
    Button btnLogout, ccyButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(this);
        ccyButton=(Button) findViewById(R.id.btnCcyCnv);
        ccyButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogout:
                Intent login = new Intent(getApplicationContext(), MainActivity.class);
                login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(login);
                finish();
                break;
            case R.id.btnCcyCnv :
                Intent ccy = new Intent(getApplicationContext(), CurrencyConversion.class);
                ccy.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(ccy);
                finish();
                break;
        }
    }
}
