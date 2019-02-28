package com.ashu.external.androidloginhttp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.ashu.external.androidloginhttp.helper.JSONParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CurrencyConversion extends AppCompatActivity {

    private Button convert;
    private JSONObject json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_conversion);
        System.out.println(new CcyConvert().execute());

    }


    private class CcyConvert extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... args) {
            JSONParser jsonParser = new JSONParser();
            JSONArray jarray = jsonParser.getJSONFromUrl(getResources().getString(R.string.wburl));
            ((EditText) findViewById(R.id.jid)).setText(jarray.toString());
            try {
                Log.d("WSREST", jarray.join(":"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jarray.toString();
        }

    }
}

