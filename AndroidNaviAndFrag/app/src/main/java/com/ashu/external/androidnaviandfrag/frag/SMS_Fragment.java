package com.ashu.external.androidnaviandfrag.frag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ashu.external.androidnaviandfrag.ISubjectMessage;
import com.ashu.external.androidnaviandfrag.R;

public class SMS_Fragment extends android.app.Fragment {
    Button sendButton;
    Button clearButton;
    EditText textPhoneNo;
    EditText textSMS;

    String message = null;

    @Override
    public void onAttach(Activity activity) {
        if (!(activity instanceof ISubjectMessage))
            throw new ClassCastException();
        message = ((ISubjectMessage) getActivity()).getMessage();
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sms_fragment, container,
                false);
        sendButton = (Button) rootView.findViewById(R.id.sendsmsbutton);
        clearButton = (Button) rootView.findViewById(R.id.clearsms);
        textPhoneNo = (EditText) rootView.findViewById(R.id.editTextPhoneNo);
        textSMS = (EditText) rootView.findViewById(R.id.editTextSMS);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        textSMS.setText(message);
        sendButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent smsVIntent = new Intent(Intent.ACTION_VIEW);
                // prompts only sms-mms clients

                smsVIntent.setType("vnd.android-dir/mms-sms");
                // extra fields for number and message respectively
                smsVIntent
                        .putExtra("address", textPhoneNo.getText().toString());
                smsVIntent.putExtra("sms_body", textSMS.getText().toString());
                try {
                    startActivity(smsVIntent);
                } catch (Exception ex) {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Your sms has failed...", Toast.LENGTH_LONG).show();
                    ex.printStackTrace();
                }
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                textSMS.setText("");

            }
        });
        super.onActivityCreated(savedInstanceState);
    }

    public void setSMSMessage(String str) {
        message = str;
    }

}
