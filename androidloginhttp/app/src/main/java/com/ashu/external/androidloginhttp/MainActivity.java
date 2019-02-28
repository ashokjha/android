package com.ashu.external.androidloginhttp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ashu.external.androidloginhttp.helper.JSONParser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText uname, password;
    private Button submit;
    // Creating JSON Parser object
    private JSONParser jParser = new JSONParser();

    private JSONObject json;

    //JSONArray incoming_msg = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViewsById();
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                new Login().execute();
                System.out.println("post login");
            }
        });
    }

    private void initializeViewsById() {
        uname = (EditText) findViewById(R.id.txtUser);
        password = (EditText) findViewById(R.id.txtPass);
        submit = (Button) findViewById(R.id.button1);
    }

    private class Login extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... args) {
            // Getting username and password from user input
            String username = uname.getText().toString();
            String pass = password.getText().toString();

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));
            params.add(new BasicNameValuePair("p", pass));
            json = jParser.makeHttpRequest(getResources().getString(R.string.lgnurl), "GET", params);
            try {
                Log.d("Msg", json.getString("info"));
                if (json.getString("info").equals("success")) {
                    Intent login = new Intent(getApplicationContext(), Welcome.class);
                    login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(login);
                    finish();
                } else {
                    ((EditText)findViewById(R.id.infoid)).setText("Either User Id or Password is wrong.");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
