package com.salesforce.androidsosguides;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;

import com.salesforce.android.sos.api.Sos;
import com.salesforce.android.sos.api.SosSession;
import com.salesforce.android.sos.api.SosSessionListener;
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
 * <li>Hide the button when its used to start a new session.</li>
 * <li>Add the activity as a listener to the session, and show the button when the session state
 * changes to DISCONNECTED, indicating the session has ended.</li>
 * <li>Make sure to remove the activity as a listener when it is destroyed.</li>
 * </ol>
 */
public abstract class SubActivity extends BaseActivity implements SosSessionListener {

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

    if (Sos.isSessionActive()) {
      // Add this activity as a listener for the existing SOS session. This will allow us to
      // re-show the
      Sos.getActiveSession().addListener(this);

      // Hide the SOS button since we already have an existing session.
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

    // Always remove ourselves as listeners from the active session. This ensures that the Activity
    // reference is not leaked. You only need to remove a listener from sessions that are active,
    // sessions that end will automatically remove all their listeners.
    if (Sos.isSessionActive()) {
      Sos.getActiveSession().removeListener(this);
    }
  }

  @Override
  public SosSession startSos() {
    SosSession session = super.startSos();

    // Hide the button used to start the session.
    menu.findItem(R.id.action_sos).setVisible(false);

    // Add this activity as a listener for the new session so that we can re-show the button when
    // it's over.
    session.addListener(this);

    return session;
  }

  /**
   * Handle state changes in the SOS session.
   *
   * @param state    The new state of the session.
   * @param oldState The old state of the session.
   */
  @Override
  public void stateChanged(SosState state, SosState oldState) {
    // Update the visibility of the SOS button based on the state of the session.
    menu.findItem(R.id.action_sos).setVisible(state == SosState.Disconnected);
  }

}
