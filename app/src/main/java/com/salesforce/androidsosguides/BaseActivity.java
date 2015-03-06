package com.salesforce.androidsosguides;

import android.support.v7.app.ActionBarActivity;

import com.salesforce.android.sos.api.SosOptions;
import com.salesforce.android.sos.api.SosSession;
import com.salesforce.androidsosguides.sos.SosConnector;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * The Baseclass for all activities in this application.
 */
public abstract class BaseActivity extends ActionBarActivity {


  public SosSession startSos() {
    // Read the properties file.
    Properties properties = new Properties();

    try {
      properties.load(getAssets().open("SOSSettings.properties"));
    } catch (IOException e) {
      throw new RuntimeException("Unable to open properties file", e);
    }

    // You could fill this map with any custom data your application wants to pass along to the
    // service agent.
    Map<String, String> customData = new HashMap<>();

    // Pull the information from the properties file and use it to instantiate the SosOptions needed
    // to start the session.
    SosOptions options = new SosOptions(
        customData,
        properties.getProperty("liveagent_pod", "la-1.d.salesforce.com"),
        properties.getProperty("organization_id", "1234567890abcde"),
        properties.getProperty("deployment_id", "abcde1234567890")
    );

    // Start the session using the constructed options, using the flavor-specific logic.
    SosSession session = SosConnector.startSession(this, options);

    return session;
  }

  boolean atleastBasic() {
    return BuildConfig.FLAVOR.equals("basic") || BuildConfig.FLAVOR.equals("advanced");
  }
}
