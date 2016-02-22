package com.salesforce.androidsosguides.sos;

import android.app.Activity;

import com.salesforce.android.sos.api.Sos;
import com.salesforce.android.sos.api.SosConfiguration;
import com.salesforce.android.sos.api.SosDialogPresenter;
import com.salesforce.android.sos.api.SosOptions;

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
  public static void startSession (Activity activity, SosOptions options) {
    // Override the dialog presenter logic with our custom class.
    SosDialogPresenter presenter = new ExampleDialogPresenter();

    // Enable the Halo UI.
    SosConfiguration configuration = SosConfiguration.builder()
        .onboardingUi(true)
        .haloUi(true)
        .build();

    Sos.session(options).dialogs(presenter).configuration(configuration).start(activity);
  }

}
