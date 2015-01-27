package com.salesforce.androidsosguides;

import android.os.Bundle;
import android.widget.TextView;

/**
 *
 */
public class ProfileActivity extends SubActivity {

  public static final String EXTRA_CONTACT = "contact";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile);

    Contact contact = (Contact) getIntent().getExtras().get(EXTRA_CONTACT);

    TextView name = (TextView) findViewById(R.id.contact_name);
    name.setText(contact.name);
  }

}
