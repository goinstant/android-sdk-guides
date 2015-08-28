package com.salesforce.androidsosguides.sos;

import android.app.Activity;

import com.salesforce.android.sos.api.Sos;
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
   * @return The new SosSession instance.
   */
  public static void startSession(Activity activity, SosOptions options) {
    Sos.session(options).start(activity);
  }

}
