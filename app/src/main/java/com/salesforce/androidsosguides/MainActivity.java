package com.salesforce.androidsosguides;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.salesforce.android.sos.api.Sos;
import com.salesforce.android.sos.api.SosEndReason;
import com.salesforce.android.sos.api.SosListener;
import com.salesforce.android.sos.api.SosState;


public class MainActivity extends BaseActivity implements SosListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Sos.addListener(this);

    if (Sos.isSessionActive()) {
      // Hide the SOS button if there is an existing session.
      View view = findViewById(R.id.start_sos_button);
      view.setVisibility(View.GONE);
    }
  }

  @Override
  protected void onDestroy() {
    Sos.removeListener(this);

    super.onDestroy();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    if (id == R.id.action_contacts) {
      Intent intent = new Intent(this, ContactsActivity.class);
      startActivity(intent);
      return true;
    } else if (id == R.id.action_compose) {
      Intent intent = new Intent(this, ComposeActivity.class);
      startActivity(intent);
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  public void startSos(View view) {
    startSos();
  }

  @Override
  public void onSessionCreated() {
    View view = findViewById(R.id.start_sos_button);
    view.setVisibility(View.GONE);
  }

  @Override
  public void onSessionEnded(SosEndReason sosEndReason) {
    View view = findViewById(R.id.start_sos_button);
    view.setVisibility(View.VISIBLE);
  }

  @Override
  public void onSessionStateChange(SosState state, SosState state2) {

  }
}
