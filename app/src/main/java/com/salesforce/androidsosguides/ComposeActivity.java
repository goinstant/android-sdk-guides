package com.salesforce.androidsosguides;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.salesforce.android.sos.api.Sos;

public class ComposeActivity extends SubActivity {

  private static final String EDIT_TEXT_EMAIL = "COMPOSE_ACTIVITY_EMAIL";
  private static final String EDIT_TEXT_SUBJECT = "COMPOSE_ACTIVITY_SUBJECT";
  private static final String EDIT_TEXT_MESSAGE = "COMPOSE_ACTIVITY_MESSAGE";

  private EditText mEmail;
  private EditText mSubject;
  private EditText mMessage;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_compose);

    // Restore the text fields after activity recreation (eg device changes orientation)
    mEmail = (EditText) findViewById(R.id.compose_email);
    mSubject = (EditText) findViewById(R.id.compose_subject);
    mMessage = (EditText) findViewById(R.id.compose_message);

    if (savedInstanceState != null) {
      mEmail.setText(savedInstanceState.getString(EDIT_TEXT_EMAIL));
      mSubject.setText(savedInstanceState.getString(EDIT_TEXT_SUBJECT));
      mMessage.setText(savedInstanceState.getString(EDIT_TEXT_MESSAGE));
    }
  }

  @Override
  protected void onSaveInstanceState (Bundle outState) {
    super.onSaveInstanceState(outState);

    // Save the value of each text field
    outState.putString(EDIT_TEXT_EMAIL, mEmail.getText().toString());
    outState.putString(EDIT_TEXT_SUBJECT, mSubject.getText().toString());
    outState.putString(EDIT_TEXT_MESSAGE, mMessage.getText().toString());
  }

  @Override
  public boolean onCreateOptionsMenu (Menu menu) {
    // This activity has a different menu than other activities, so we don't call throught to the super.
    getMenuInflater().inflate(R.menu.menu_compose, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected (MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_send) {
      // Mock out sending an actual email, display a toast saying that we have done this.
      Toast toast = Toast.makeText(this, getString(R.string.email_success), Toast.LENGTH_LONG);

      // `Sos.captureExtra` will fail if the session is not actually running, since we can get to this
      // activity and we allow users to send messages whether the session is running or not, we make
      // sure to check whether or not the session is running before trying to send the agent a copy
      // of our toast
      if (Sos.isSessionActive() && atleastBasic()) {
        Sos.captureExtra(toast);
      }

      // Remember to send the toast after we have told SOS that we would like to capture it.
      toast.show();
    }

    return super.onOptionsItemSelected(item);
  }
}
