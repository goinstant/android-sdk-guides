package com.salesforce.androidsosguides;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;

import com.salesforce.android.sos.api.Sos;
import com.salesforce.android.sos.api.SosEndReason;
import com.salesforce.android.sos.api.SosListener;
import com.salesforce.android.sos.api.SosState;

/**
 * Parent class for the sub-screen activities in this application. Contains some common logic for
 * dealing with the action bar.
 * <p>
 * This class will hide and show the SOS button in the action bar depending on the state of the
 * SOS session. There are four main steps to toggling the button:
 * </p>
 * <ol>
 * <li>Check for an existing session when creating the button. A session may have been started by
 * a previous activity in your application. Hide the button if there is an active session.</li>
 * <li>Hide the button when a new session starts.</li>
 * <li>Show the button when a session ends.</li>
 * <li>Make sure to remove the activity as a listener when it is destroyed.</li>
 * </ol>
 */
public abstract class SubActivity extends BaseActivity implements SosListener {

  private Menu menu;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActionBar actionBar = getSupportActionBar();
    actionBar.setDisplayHomeAsUpEnabled(true);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_sub, menu);

    // Add this activity as a listener so that it will hide or show the SOS action bar icon as needed.
    Sos.addListener(this);

    if (Sos.isSessionActive()) {
      // Hide the SOS action bar icon since we already have an existing session.
      menu.findItem(R.id.action_sos).setVisible(false);
    }

    this.menu = menu;
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.action_sos) {
      startSos();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();

    // Always remove ourselves as a listener. This ensures that the Activity reference is not leaked.
    Sos.removeListener(this);
  }

  @Override
  public void onSessionCreated() {
    // Hide the action bar icon when the session starts.
    menu.findItem(R.id.action_sos).setVisible(false);
  }

  @Override
  public void onSessionEnded(SosEndReason sosEndReason) {
    // Show the action bar icon when the session ends.
    menu.findItem(R.id.action_sos).setVisible(true);
  }

  @Override
  public void onSessionStateChange(SosState state, SosState state2) {

  }
}
