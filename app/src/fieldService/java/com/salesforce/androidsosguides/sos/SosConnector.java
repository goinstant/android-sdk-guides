package com.salesforce.androidsosguides.sos;

import android.app.Activity;

import com.salesforce.android.sos.api.Sos;
import com.salesforce.android.sos.api.SosConfiguration;
import com.salesforce.android.sos.api.SosOptions;

/**
 * This class contains all the logic for starting an SOS session in the simple flavor: with no
 * customization.
 */
public class SosConnector {

  /**
   * Start a new SOS session.
   *
   * @param activity The activity that is starting the session.
   * @param options  The information needed to start the session.
   */
  public static void startSession(Activity activity, SosOptions options) {
    // Enable field services mode, which enables only the camera.
    SosConfiguration configuration = SosConfiguration.builder()
            .fieldServices(true)
            .build();

    Sos.session(options).configuration(configuration).start(activity);
  }

}
