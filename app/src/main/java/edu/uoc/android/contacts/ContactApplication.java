package edu.uoc.android.contacts;

import edu.uoc.android.contacts.entities.Contact;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;

/**
 * Created by Marc on 07/10/2015.
 */

public class ContactApplication extends Application
{
    private static final String APPLICATION_ID = "bCnafKBrpOZAj6PviAfKjKEwfqSOD9H5j05Ku16n";
    private static final String CLIENT_ID = "GszB7TWHoKxL0AzrEB1FRjqpQIQ9CY5HdZnoedhl";

    public static final String OBJECT_ID = "OBJECT_ID";

    @Override
    public void onCreate() {
        super.onCreate();

        // Enabling to store data using Parse API
        Parse.enableLocalDatastore(this);
        // Registering Contacts class to be able to download data from Parse (it's a must)
        ParseObject.registerSubclass(Contact.class);
        // Add your initialization code here
        Parse.initialize(getApplicationContext(), APPLICATION_ID, CLIENT_ID);
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
