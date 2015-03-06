package com.salesforce.androidsosguides.sos;

import android.app.Activity;
import android.app.FragmentManager;

import com.salesforce.android.sos.api.SosDefaultDialogPresenter;
import com.salesforce.androidsosguides.MainActivity;
import com.salesforce.androidsosguides.R;

/**
 * This example dialog presenter is used to replace some of the default dialog presentation logic
 * in SOS. By extending SosDefaultDialogPresenter, we can selectively override some dialogs but not
 * others. We could have also chosen to implement the SosDialogPresenter interface directly to
 * not use any of the default dialog presenting logic.
 */
public class ExampleDialogPresenter extends SosDefaultDialogPresenter {
  /**
   * @{inheritDoc}
   */
  @Override
  public void show(Type type, Activity activity, OnSelectionListener listener) {

    // Suppress the disconnect dialog by immediately calling the callback when it is asked to be
    // shown. This results in the session ending immediately when the user hits the close button.
    // Be careful to ensure that the correct values are passed to the callback; passing false for
    // the "positive" argument here would result in the user being unable to end the session.
    if (type == Type.DISCONNECT_PROMPT) {
      listener.onSelectionMade(type, true, activity);
      return;
    }

    // Use a custom fragment to show the connection status UI on the main activity only. Other
    // activities will use the default logic.
    if (type == Type.CONNECTING_STATUS && activity instanceof MainActivity) {
      showCustomFragment(activity, listener);
      return;
    }

    // Call through to the default logic for all other dialog types.
    super.show(type, activity, listener);
  }

  /**
   * @{inheritDoc}
   */
  @Override
  public void setMessage(Type type, Activity activity, int resourceId) {
    // Update our custom status fragment on the main activity.
    if (type == Type.CONNECTING_STATUS && activity instanceof MainActivity) {
      setCustomFragmentMessage(activity, resourceId);
      return;
    }

    // Call through to the default logic for all other dialog types.
    super.setMessage(type, activity, resourceId);
  }

  /**
   * @{inheritDoc}
   */
  @Override
  public void dismiss(Type type, Activity activity) {
    // Remove our custom status fragment on the main activity.
    if (type == Type.CONNECTING_STATUS && activity instanceof MainActivity) {
      dismissCustomFragment(activity);
      return;
    }

    // Call through to the default logic for all other dialog types.
    super.dismiss(type, activity);
  }

  // Custom fragment methods.

  private void showCustomFragment(Activity activity, OnSelectionListener listener) {
    // Get the message from the type.
    String message = Type.CONNECTING_STATUS.getMessage(activity);

    // Instantiate the fragment we're going to be showing.
    StatusFragment fragment = StatusFragment.newInstance(message, listener);

    // Add the fragment to the activity.
    activity.getFragmentManager().beginTransaction()
        .add(R.id.status_fragment_container, fragment)
        .commit();
  }

  private void setCustomFragmentMessage(Activity activity, int resourceId) {
    // Find the fragment in the activity.
    FragmentManager manager = activity.getFragmentManager();
    StatusFragment fragment = (StatusFragment) manager.findFragmentById(R.id.status_fragment_container);

    // Set the message in the fragment.
    if (fragment != null) {
      fragment.setMessage(activity.getResources().getString(resourceId));
    }
  }

  private void dismissCustomFragment(Activity activity) {
    // Find the fragment in the activity.
    FragmentManager manager = activity.getFragmentManager();
    StatusFragment fragment = (StatusFragment) manager.findFragmentById(R.id.status_fragment_container);

    // Remove the fragment.
    if (fragment != null) {
      manager.beginTransaction()
          .remove(fragment)
          .commit();
    }
  }
}
