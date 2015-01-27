package com.salesforce.androidsosguides;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Activity that displays some fake contacts in a list.
 */
public class ContactsActivity extends SubActivity implements AdapterView.OnItemClickListener {

  private static final Contact[] CONTACTS = new Contact[]{
      new Contact("Keith Allen", "salesforce.com"),
      new Contact("Harry Roberts", "salesforce.com"),
      new Contact("Raymond Patterson", "salesforce.com"),
      new Contact("Jerry Ford", "salesforce.com"),
      new Contact("Kimberly Douglas", "salesforce.com"),
      new Contact("Jose Estrada", "salesforce.com"),
      new Contact("Rebecca Morris", "salesforce.com"),
      new Contact("Joe Duncan", "salesforce.com"),
      new Contact("Geoff Teehan", "salesforce.com"),
      new Contact("Adam Castro", "salesforce.com"),
      new Contact("Billy Wildman", "salesforce.com"),
      new Contact("Roger McKinnon", "salesforce.com")
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_contacts);

    ListView view = (ListView) findViewById(R.id.contacts_list_view);
    view.setAdapter(new ContactsAdapter(this, R.layout.item_contact, CONTACTS));
    view.setOnItemClickListener(this);
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    Contact contact = (Contact) parent.getItemAtPosition(position);
    Intent intent = new Intent(this, ProfileActivity.class);
    intent.putExtra(ProfileActivity.EXTRA_CONTACT, contact);
    startActivity(intent);
  }

  /**awx
   * The ContactsAdapter creates the views for the items in the Contacts list.
   */
  private static class ContactsAdapter extends ArrayAdapter<Contact> {
    public ContactsAdapter(Context context, int resource, Contact[] objects) {
      super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      Contact contact = getItem(position);

      if (convertView == null) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_contact, parent, false);
      }

      TextView name = (TextView) convertView.findViewById(R.id.contact_name);
      TextView company = (TextView) convertView.findViewById(R.id.contact_company);

      name.setText(contact.name);
      company.setText(contact.company);

      return convertView;
    }
  }
}
