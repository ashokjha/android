package com.ashu.external.androidnaviandfrag.frag;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ashu.external.androidnaviandfrag.ISubjectMessage;
import com.ashu.external.androidnaviandfrag.R;


public class Message_Fragment extends android.app.Fragment {
    Button smsButton;
    Button emailButton;
    EditText messageEditText;

    ISubjectMessage messageInterface;

    @Override
    public void onAttach(Activity activity) {
        // We verify that our activity implements the listener
        if (!(activity instanceof ISubjectMessage))
            throw new ClassCastException();
        else
            messageInterface = (ISubjectMessage) getActivity();
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.message, container,
                false);
        smsButton = (Button) rootView.findViewById(R.id.smsbutton);
        emailButton = (Button) rootView.findViewById(R.id.emailbutton);
        messageEditText = (EditText) rootView.findViewById(R.id.message);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        smsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                messageInterface.setMessage(messageEditText.getText()
                                                    .toString(), 1);

            }
        });
        emailButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                messageInterface.setMessage(messageEditText.getText()
                                                    .toString(), 2);

            }
        });
        super.onActivityCreated(savedInstanceState);
    }

}
