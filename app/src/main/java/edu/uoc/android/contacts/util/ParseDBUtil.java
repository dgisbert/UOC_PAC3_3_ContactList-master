package edu.uoc.android.contacts.util;

import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

import edu.uoc.android.contacts.entities.Contact;

/**
 * Created by Marc on 07/10/2015.
 */
public class ParseDBUtil {
    private static final String TAG = ParseDBUtil.class.getSimpleName();

    /**
     * Method to delete all the data from Parse DataBase
     */
    public static void unpinContacts() {
        try {
            Contact.unpinAll(Contact.class.getSimpleName());
        } catch (ParseException er) {
            er.printStackTrace();
        }
    }

    /**
     * Method to save all the data in Parse DataBase
     */
    public static void pinContacts(List<Contact> contactsList) {
        try {
            Contact.pinAll(Contact.class.getSimpleName(), contactsList);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to get all the contacts from Parse DataBase
     *
     * @return Contact list
     */
    public static List<Contact> getAllContacts() {
        List<Contact> contactList = null;
        ParseQuery<Contact> query = ParseQuery.getQuery(Contact.class);
        query.fromLocalDatastore();
        try {
            contactList = query.find();
        } catch (ParseException e) {
            Log.e(TAG, "ParseException: " + e.getMessage());
        }
        return contactList;
    }

    /**
     * Method to get a contacts from Parse DataBase
     *
     * @param objectId of contact
     * @return Contact item
     */
    public static Contact getContactById(String objectId) {
        Contact contact = null;
        ParseQuery<Contact> query = ParseQuery.getQuery(Contact.class);
        query.fromLocalDatastore();
        try {
            contact = query.get(objectId);
        } catch (ParseException e) {
            Log.e(TAG, "ParseException: " + e.getMessage());
        }
        return contact;
    }
}
