package com.salesforce.androidsosguides.sos;

import android.app.Activity;

import com.salesforce.android.sos.api.SosDefaultDialogPresenter;

/**
 * This example dialog presenter is used to replace some of the default dialog presentation logic
 * in SOS. By extending SosDefaultDialogPresenter, we can selectively override some dialogs but not
 * others. We could have also chosen to implement the SosDialogPresenter interface directly to
 * not use any of the default dialog presenting logic.
 */
public class ExampleDialogPresenter extends SosDefaultDialogPresenter {
  @Override
  public void show(Type type, Activity activity, Callback callback) {

    // Suppress the disconnect dialog by immediately calling the callback when it is asked to be
    // shown. This results in the session ending immediately when the user hits the close button.
    // Be careful to ensure that the correct values are passed to the callback; passing false for
    // the "positive" argument here would result in the user being unable to end the session.
    if (type == Type.DISCONNECT_PROMPT) {
      callback.complete(type, true, activity);
      return;
    }

    // Call through to the default logic for all other dialog types.
    super.show(type, activity, callback);
  }
}
