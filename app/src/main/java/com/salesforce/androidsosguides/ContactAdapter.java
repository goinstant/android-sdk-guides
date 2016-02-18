package com.salesforce.androidsosguides;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * RecyclerView.Adapter for displaying the contact list.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {


  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    final Contact contact = Contact.ALL[position];
    holder.mContactName.setText(contact.name);
    holder.mCompanyName.setText(contact.company);

    holder.mView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Context context = v.getContext();
        Intent intent = new Intent(context, ProfileActivity.class);
        intent.putExtra(ProfileActivity.EXTRA_CONTACT, contact);
        context.startActivity(intent);
      }
    });
  }

  @Override
  public int getItemCount() {
    return Contact.ALL.length;
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    public final View mView;
    public final TextView mContactName;
    public final TextView mCompanyName;

    public ViewHolder(View view) {
      super(view);
      mView = view;
      mContactName = (TextView) view.findViewById(R.id.contact_name);
      mCompanyName = (TextView) view.findViewById(R.id.contact_company);
    }
  }

}
