package com.salesforce.androidsosguides;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;

import com.salesforce.android.sos.api.Sos;
import com.salesforce.android.sos.api.SosEndReason;
import com.salesforce.android.sos.api.SosListener;
import com.salesforce.android.sos.api.SosState;


public class MainActivity extends BaseActivity implements SosListener {

  private DrawerLayout mDrawerLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Sos.addListener(this);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
      actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
    }

    mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(MenuItem item) {
        item.setChecked(true);
        switchFragment(item.getItemId());
        mDrawerLayout.closeDrawers();
        return true;
      }
    });

    // Setup the FloatingActionButton which will start an SOS session.
    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.start_sos);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startSos();
      }
    });

    if (Sos.isSessionActive()) {
      disableFab();
    }

    switchFragment(R.id.nav_contacts);
  }

  @Override
  protected void onDestroy() {
    Sos.removeListener(this);
    super.onDestroy();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        mDrawerLayout.openDrawer(GravityCompat.START);
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onSessionCreated() {
    disableFab();
  }

  @Override
  public void onSessionEnded(SosEndReason sosEndReason) {
    enableFab();
  }

  @Override
  public void onSessionStateChange(SosState state, SosState state2) {

  }

  private void disableFab() {
    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.start_sos);

    TypedValue typedValue = new TypedValue();
    getTheme().resolveAttribute(R.attr.colorAccent, typedValue, true);
    int fabColor = typedValue.data;

    fab.setColorFilter(Color.argb(128, Color.red(fabColor), Color.green(fabColor), Color.blue(fabColor)));
    fab.setEnabled(false);
  }

  private void enableFab() {
    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.start_sos);
    fab.clearColorFilter();
    fab.setEnabled(true);
  }

  private void switchFragment(@IdRes int menuItem) {
    Fragment fragment;

    switch (menuItem) {
      case R.id.nav_compose:
        fragment = new ComposeFragment();
        break;
      case R.id.nav_contacts:
      default:
        fragment = new ContactsFragment();
        break;
    }

    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    transaction.replace(R.id.fragment_container, fragment);
    transaction.commit();
  }
}
