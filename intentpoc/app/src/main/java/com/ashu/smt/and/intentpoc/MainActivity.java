package com.ashu.smt.and.intentpoc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button callOtherActivityBtn;
    EditText editTxt;
    TextView header;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.intent_poc);
        initialiseActivity();
        applyListener();
    }

    private void applyListener() {
        callOtherActivityBtn.setOnClickListener(this);
    }

    private void initialiseActivity() {
        Bundle bundle = getIntent().getExtras();
        header = findViewById(R.id.mainHeader);
        header.setText((bundle != null) ?
                              (bundle.getString("caller") != null) ?
                                      bundle.getString("caller")
                                      : getText(R.string.MainDiffTitle)
                              : getText(R.string.MainDiffTitle));
        editTxt = findViewById(R.id.callerInfoId);
        editTxt.setText(getString(R.string.hello_intent));
        callOtherActivityBtn = findViewById(R.id.callerButtonId);
        callOtherActivityBtn.setText(R.string.callOtherActivityButton);
    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.callerButtonId:
                Intent intent = new Intent(MainActivity.this, TargetContentActivity.class);
                intent.putExtra("caller",getString(R.string.callFromMain));
                startActivity(intent);
                overridePendingTransition(R.anim.activity_in,R.anim.activity_out);
                break;
        }
    }
}
