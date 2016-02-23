package com.salesforce.androidsosguides.sos;

import android.app.Activity;

import com.salesforce.android.sos.api.Sos;
import com.salesforce.android.sos.api.SosConfiguration;
import com.salesforce.android.sos.api.SosOptions;

/**
 * This class contains all the logic for starting an SOS session in the two-way video flavor.
 */
public class SosConnector {

  /**
   * Start a new SOS session.
   *
   * @param activity The activity that is starting the session.
   * @param options  The information needed to start the session.
   * @return The new SosSession instance.
   */
  public static void startSession (Activity activity, SosOptions options) {
    // Enable the Halo UI with Two-way Video
    SosConfiguration configuration = SosConfiguration.builder()
        .onboardingUi(true)
        .haloUi(true)
        .twoWayVideo(true)
        .build();

    Sos.session(options).configuration(configuration).start(activity);
  }

}
