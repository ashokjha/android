package com.ashu.external.androidloginhttp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.ashu.external.androidloginhttp.helper.JSONParser;
import com.google.gson.JsonParser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CurrencyConversion extends AppCompatActivity {
    private EditText fromCCY, toCCY;
    private Button convert;

    private JSONObject json;
    private static String ccyCnvUrl = "https://free.currencyconverterapi.com/api/v6/convert?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_conversion);
        new CcyConvert().execute();
    }


    private class CcyConvert extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... args) {

            String fromCCY = "USD";// fromCCy.getText().toString();
            String toCCY = "INR";// toCCy.getText().toString();-
            JSONParser jsonParser = new JSONParser();
            String url = ccyCnvUrl + "q=" + fromCCY + "_" + toCCY + "&compact=ultra&apiKey=6a65bb2786ed73e05520";
            JSONArray jarray = jsonParser.getJSONFromUrl(url);
            System.out.println(jarray);
            try {
                Log.d("CNV", jarray.join(":"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

    }
}

