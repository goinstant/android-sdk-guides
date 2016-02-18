package com.salesforce.androidsosguides;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class ComposeFragment extends Fragment {

  private static final String EDIT_TEXT_EMAIL = "COMPOSE_ACTIVITY_EMAIL";
  private static final String EDIT_TEXT_SUBJECT = "COMPOSE_ACTIVITY_SUBJECT";
  private static final String EDIT_TEXT_MESSAGE = "COMPOSE_ACTIVITY_MESSAGE";

  private EditText mEmail;
  private EditText mSubject;
  private EditText mMessage;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_compose, container, false);

    // Restore the text fields after activity recreation (eg device changes orientation)
    mEmail = (EditText) view.findViewById(R.id.compose_email);
    mSubject = (EditText) view.findViewById(R.id.compose_subject);
    mMessage = (EditText) view.findViewById(R.id.compose_message);

    if (savedInstanceState != null) {
      mEmail.setText(savedInstanceState.getString(EDIT_TEXT_EMAIL));
      mSubject.setText(savedInstanceState.getString(EDIT_TEXT_SUBJECT));
      mMessage.setText(savedInstanceState.getString(EDIT_TEXT_MESSAGE));
    }

    ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
    if (actionBar != null) {
      actionBar.setTitle(R.string.title_compose);
    }

    return view;
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);

    // Save the value of each text field
    outState.putString(EDIT_TEXT_EMAIL, mEmail.getText().toString());
    outState.putString(EDIT_TEXT_SUBJECT, mSubject.getText().toString());
    outState.putString(EDIT_TEXT_MESSAGE, mMessage.getText().toString());
  }
}
