package com.ashu.external.androidnaviandfrag.frag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ashu.external.androidnaviandfrag.ISubjectMessage;
import com.ashu.external.androidnaviandfrag.R;

public class EMail_Fragment extends android.app.Fragment {
    Button sendButton;
    Button clearButton;
    EditText textTo;
    EditText textSubject;
    EditText textMessage;
    String subject = null;

    @Override
    public void onAttach(Activity activity) {
        if (!(activity instanceof ISubjectMessage))
            throw new ClassCastException();
        subject = ((ISubjectMessage) getActivity()).getMessage();
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.email_fragment, container,
                false);
        sendButton = (Button) rootView.findViewById(R.id.sendemailButton);
        clearButton = (Button) rootView.findViewById(R.id.clearmsg);
        textTo = (EditText) rootView.findViewById(R.id.editTextTo);
        textSubject = (EditText) rootView.findViewById(R.id.editTextSubject);
        textMessage = (EditText) rootView.findViewById(R.id.editTextMessage);

        return rootView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        textMessage.setText(subject);
        sendButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String to = textTo.getText().toString();
                String subject = textSubject.getText().toString();
                String message = textMessage.getText().toString();

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[] { to });
                // email.putExtra(Intent.EXTRA_CC, new String[]{ to});
                // email.putExtra(Intent.EXTRA_BCC, new String[]{to});
                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(Intent.EXTRA_TEXT, message);

                // need this to prompts email client only
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email,
                        "Choose an Email client :"));

            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                textMessage.setText("");

            }
        });
        super.onActivityCreated(savedInstanceState);
    }

    public void setEMailMessage(String str) {
        subject = str;
    }

}
