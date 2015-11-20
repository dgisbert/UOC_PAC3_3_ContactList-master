package edu.uoc.android.contacts.adapter;

import edu.uoc.android.contacts.ContactApplication;
import edu.uoc.android.contacts.R;
import edu.uoc.android.contacts.activity.ContactDetailActivity;
import edu.uoc.android.contacts.entities.Contact;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Adapter class to show contact list on screen.
 */

public class ContactListAdapter extends ArrayAdapter<Contact> implements AdapterView.OnItemClickListener
{
    private List<Contact> contactList;

    //*****************************************************************************************************************
    // Private inner classes
    //*****************************************************************************************************************

    private static class ViewHolder
    {
        protected ImageView contactPhoto;
        protected TextView  contactName ;
        protected TextView  contactPhone;
    }

    //*****************************************************************************************************************
    // Public section
    //*****************************************************************************************************************

    @Override public int     getCount ()             { return contactList.size(); }
//    @Override public long    getItemId(int position) { return contactList.get(position).getObjectId(); }
    @Override public Contact getItem  (int position) { return contactList.get(position); }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Contact contact = contactList.get(position);

        Context context = getContext();

        Intent intent = new Intent(context, ContactDetailActivity.class);

        intent.putExtra(ContactApplication.OBJECT_ID, contact.getObjectId());

        context.startActivity(intent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view;

        final ViewHolder viewHolder;

        if (convertView == null || convertView.getTag() == null)
        {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(getContext()).inflate(R.layout.contact_item, parent, false);

            viewHolder.contactPhoto = (ImageView) view.findViewById(R.id.contactPhoto);
            viewHolder.contactName  = (TextView)  view.findViewById(R.id.contactName);
            viewHolder.contactPhone = (TextView)  view.findViewById(R.id.contactPhone);

            view.setTag(viewHolder);

        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
            view = convertView;
        }

        // Set text with the item name

        Contact contact = contactList.get(position);

        viewHolder.contactPhoto.setImageBitmap(contact.getContactImageBitmap());
        viewHolder.contactName.setText(contact.getContactName());
        viewHolder.contactPhone.setText(contact.getContactPhone());

        return view;
    }

    //*****************************************************************************************************************
    // Constructor
    //*****************************************************************************************************************

    public ContactListAdapter(Context context, List<Contact> contactList)
    {
        super(context, R.layout.contact_item);

        this.contactList = contactList;
    }
}
