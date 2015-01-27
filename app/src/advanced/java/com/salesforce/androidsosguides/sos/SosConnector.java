package com.salesforce.androidsosguides.sos;

import android.app.Activity;

import com.salesforce.android.sos.api.Sos;
import com.salesforce.android.sos.api.SosConfiguration;
import com.salesforce.android.sos.api.SosDialogPresenter;
import com.salesforce.android.sos.api.SosOptions;
import com.salesforce.android.sos.api.SosSession;

/**
 * This class contains all the logic for starting an SOS session in the advanced flavor: with custom
 * logic used to replace default SOS behaviour.
 */
public class SosConnector {

  /**
   * Start a new SOS session.
   *
   * @param activity The activity that is starting the session.
   * @param options  The information needed to start the session.
   * @return The new SosSession instance.
   */
  public static SosSession startSession(Activity activity, SosOptions options) {
    // Talk to the backend that will be used when SOS becomes generally available.
    SosConfiguration configuration =
        new SosConfiguration.Builder().backend(SosConfiguration.Backend.GA).build();

    // Override the dialog presenter logic with our custom class.
    SosDialogPresenter presenter = new ExampleDialogPresenter();

    return Sos.startSession(activity, options, presenter, configuration);
  }

}
