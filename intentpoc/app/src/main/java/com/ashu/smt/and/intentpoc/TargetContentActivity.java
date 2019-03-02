package com.ashu.smt.and.intentpoc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TargetContentActivity extends AppCompatActivity implements View.OnClickListener {
    Button callOtherActivityBtn;
    EditText editTxt;
    TextView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_content);
        initialiseActivity();
        registerEventListener();
    }

    private void registerEventListener() {
        callOtherActivityBtn.setOnClickListener(this);
    }


    private void initialiseActivity() {
        setTitle(R.string.intent_poc);
        header = findViewById(R.id.calleettl);
        Bundle bundle = getIntent().getExtras();
        header.setText((bundle != null) ?
                              (bundle.getString("caller") != null) ?
                                      bundle.getString("caller") :
                                      getString(R.string.CalleeDiffTitle) :
                              getString(R.string.CalleeDiffTitle));
        editTxt = findViewById(R.id.calleeInfoId);
        editTxt.setText(getString(R.string.hello_intent_target));
        callOtherActivityBtn = findViewById(R.id.backButtonId);
        callOtherActivityBtn.setText(R.string.backButton);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param view The view that was clicked.
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backButtonId:
                Intent intent1 = new Intent(TargetContentActivity.this, MainActivity.class);
                intent1.putExtra("caller", getString(R.string.callFromOther));
                startActivity(intent1);
                overridePendingTransition(R.anim.activity_in,R.anim.activity_out);
                break;
        }
    }
}
