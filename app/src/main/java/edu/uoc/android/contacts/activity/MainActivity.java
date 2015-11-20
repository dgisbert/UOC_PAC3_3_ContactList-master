package edu.uoc.android.contacts.activity;

import edu.uoc.android.contacts.R;
import edu.uoc.android.contacts.adapter.ContactListAdapter;
import edu.uoc.android.contacts.util.ParseDBUtil;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity
{
    private ListView listview;

    //*****************************************************************************************************************
    // Protected section
    //*****************************************************************************************************************

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.contactList);

        ContactListAdapter contactListAdapter  = new ContactListAdapter(this, ParseDBUtil.getAllContacts());

        listview.setAdapter(contactListAdapter);
        listview.setOnItemClickListener(contactListAdapter);
    }
}
