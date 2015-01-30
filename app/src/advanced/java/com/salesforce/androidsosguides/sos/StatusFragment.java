package com.salesforce.androidsosguides.sos;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.salesforce.android.sos.api.SosDialogPresenter;
import com.salesforce.androidsosguides.R;

/**
 * This fragment is used to show the SOS connection status on the main activity.
 */
public class StatusFragment extends Fragment implements View.OnClickListener {

  private View view;
  private String message;
  private SosDialogPresenter.Callback callback;

  public static StatusFragment newInstance(String message, SosDialogPresenter.Callback callback) {
    StatusFragment fragment = new StatusFragment();

    Bundle args = new Bundle();
    args.putString("message", message);
    args.putSerializable("callback", callback);
    fragment.setArguments(args);

    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // If the message had been previously set via a call to setMessage then it'll be present in the
    // saved instance state. Otherwise read the message from the arguments provided to the fragment.
    if (savedInstanceState != null && savedInstanceState.get("message") != null) {
      message = savedInstanceState.getString("message");
    } else {
      message = getArguments().getString("message");
    }

    // Read the callback from the fragment arguments.
    callback = (SosDialogPresenter.Callback) getArguments().get("callback");
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putString("message", message);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_status, container, false);

    TextView status = (TextView) view.findViewById(R.id.fragment_status_text);
    status.setText(message);

    Button cancel = (Button) view.findViewById(R.id.fragment_status_button);
    cancel.setOnClickListener(this);

    return view;
  }

  @Override
  public void onClick(View v) {
    // When the user clicks the button, tell the SOS framework that they chose to cancel the
    // connection by calling the provided callback.
    callback.complete(SosDialogPresenter.Type.CONNECTING_STATUS, false, getActivity());
  }

  public void setMessage(String message) {
    this.message = message;
    TextView status = (TextView) view.findViewById(R.id.fragment_status_text);
    status.setText(message);
  }
}
