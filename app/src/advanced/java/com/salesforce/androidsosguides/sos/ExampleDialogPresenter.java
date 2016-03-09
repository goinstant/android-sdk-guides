package com.salesforce.androidsosguides.sos;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.salesforce.android.sos.api.SosDefaultDialogPresenter;
import com.salesforce.androidsosguides.MainActivity;
import com.salesforce.androidsosguides.R;

import java.lang.ref.WeakReference;

/**
 * This example dialog presenter is used to replace some of the default dialog presentation logic
 * in SOS. By extending SosDefaultDialogPresenter, we can selectively override some dialogs but not
 * others. We could have also chosen to implement the SosDialogPresenter interface directly to
 * not use any of the default dialog presenting logic.
 */
public class ExampleDialogPresenter extends SosDefaultDialogPresenter {
  static WeakReference<Snackbar> sSnackbar = new WeakReference<>(null);



  /**
   * {@inheritDoc}
   */
  @Override
  public void show (Type type, Activity activity, OnSelectionListener listener) {

    // Suppress the connect prompt and disconnect dialogs by immediately calling the callback when
    // it is asked to be shown.
    // For the disconnect prompt, this results in the session ending immediately when the user hits
    // the close button. Be careful to ensure that the correct values are passed to the callback;
    // passing false for the "positive" argument here would result in the user being unable to end
    // the session.
    if (type == Type.CONNECT_PROMPT || type == Type.DISCONNECT_PROMPT) {
      listener.onSelectionMade(type, true, activity);
      return;
    }

    // Use a snackbar to show the connection status UI on the main activity only. Other
    // activities will use the default logic.
    if (type == Type.CONNECTING_STATUS && activity instanceof MainActivity) {
      showSnackbar(activity, listener);
      return;
    }

    // Call through to the default logic for all other dialog types.
    super.show(type, activity, listener);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setMessage (Type type, Activity activity, int resourceId) {
    // Update our snackbar on the main activity.
    if (type == Type.CONNECTING_STATUS) {
      setSnackbarMessage(resourceId);
      if (activity instanceof MainActivity) {
        return;
      }
    }

    // Call through to the default logic for all other dialog types.
    super.setMessage(type, activity, resourceId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void dismiss (Type type, Activity activity) {
    // Remove our snackbar on the main activity.
    if (type == Type.CONNECTING_STATUS) {
      dismissSnackbar();
      if (activity instanceof MainActivity) {
        return;
      }
    }

    // Call through to the default logic for all other dialog types.
    super.dismiss(type, activity);
  }

  // Snackbar methods.

  private void showSnackbar (final Activity activity, final OnSelectionListener listener) {
    // Get the message from the type.
    String message = Type.CONNECTING_STATUS.getMessage(activity);

    Snackbar snackbar = Snackbar.make(activity.findViewById(R.id.start_sos), message, Snackbar.LENGTH_INDEFINITE);
    snackbar.setAction(R.string.sos_connect_negative, new View.OnClickListener() {
      @Override
      public void onClick (View v) {
        listener.onSelectionMade(Type.CONNECTING_STATUS, false, activity);
      }
    });
    snackbar.show();

    sSnackbar = new WeakReference<>(snackbar);
  }

  private void setSnackbarMessage (int resourceId) {
    Snackbar snackbar = sSnackbar.get();
    if (snackbar != null) {
      snackbar.setText(resourceId);
    }
  }

  private void dismissSnackbar () {
    Snackbar snackbar = sSnackbar.get();
    if (snackbar != null) {
      snackbar.dismiss();
    }
  }
}
