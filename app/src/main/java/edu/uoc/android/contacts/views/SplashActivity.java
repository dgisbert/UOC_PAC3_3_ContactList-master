package edu.uoc.android.contacts.views;

import edu.uoc.android.contacts.R;
import edu.uoc.android.contacts.activity.MainActivity;
import edu.uoc.android.contacts.entities.Contact;
import edu.uoc.android.contacts.util.NetworkUtil;
import edu.uoc.android.contacts.util.ParseDBUtil;
import edu.uoc.android.contacts.util.SharePreferenceUtil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by Marc on 07/10/2015.
 *
 * This Activity is used to shown an image while it is downloading the contact information
 * from Parse server.
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // check if there are connectivity in the device
        if (NetworkUtil.isConnectionAvailable(this)) {
            needToUpdateContacts();
        } else {
            goToNextActivity();
        }
    }

    /**
     * This method checks if app's needs to download contact information or not. If one contact has
     * been updated, the app's download all the information again.
     */
    public void needToUpdateContacts() {
        ParseQuery<Contact> query = ParseQuery.getQuery(Contact.class);
        query.orderByDescending("updatedAt");
        query.getFirstInBackground(new GetCallback<Contact>() {
            @Override
            public void done(Contact contact, ParseException e) {
                if (contact == null) {
                    // If server hasn't have contacts, go to next activity
                    goToNextActivity();
                } else {
                    // check if app needs to update contacts
                    long millis = SharePreferenceUtil.getLongValue(getApplicationContext(),
                            SharePreferenceUtil.CONTACTS_LAST_UPDATE, 0l);
                    // compare contact time stamp and time stamp saved to know if contacts information
                    // have to be updated
                    if (contact.getUpdatedAt().getTime() <= millis) {
                        // go to next activity
                        goToNextActivity();
                    } else {
                        // delete contacts from database and get all the contacts from server
                        ParseDBUtil.unpinContacts();
                        getContactsFromServer();
                    }
                }
            }
        });
    }

    /**
     * Method to get all the contact information from server
     */
    public void getContactsFromServer() {
        ParseQuery<Contact> query = ParseQuery.getQuery(Contact.class);
        query.orderByDescending("updatedAt");
        query.findInBackground(new FindCallback<Contact>() {
            @Override
            public void done(List<Contact> contactsList, ParseException error) {
                if (contactsList != null) {
                    // save contact in parse database
                    ParseDBUtil.pinContacts(contactsList);
                    // update SharePreference with last time stamp
                    SharePreferenceUtil.setLongValue(getApplicationContext(),
                            SharePreferenceUtil.CONTACTS_LAST_UPDATE,
                            contactsList.get(0).getUpdatedAt().getTime());
                }
                // go to next activity
                goToNextActivity();
            }
        });
    }

    /**
     * Method to go to next activity
     */
    private void goToNextActivity()
    {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }
}
