package edu.uoc.android.contacts.entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

/**
 * Created by Marc on 07/10/2015.
 */
@ParseClassName("Contacts")
public class Contact extends ParseObject {

    /* Column key */
    private static final String CONTACT_GENDER = "gender";
    private static final String CONTACT_NAME = "name";
    private static final String CONTACT_CITY = "city";
    private static final String CONTACT_LOCATION = "location";
    private static final String CONTACT_EMAIL = "email";
    private static final String CONTACT_DESCRIPTION = "description";
    private static final String CONTACT_IMAGE = "image";
    private static final String CONTACT_PHONE = "phone";
    private static final String CONTACT_COUNTRY = "country";
    private static final String CONTACT_BIRTHDAY = "birthday";

    public String getContactGender() {
        return getString(CONTACT_GENDER);
    }

    public String getContactName() {
        return getString(CONTACT_NAME);
    }

    public String getContactCity() {
        return getString(CONTACT_CITY);
    }

    public ParseGeoPoint getContactLocation() {
        return getParseGeoPoint(CONTACT_LOCATION);
    }

    public String getContactEmail() {
        return getString(CONTACT_EMAIL);
    }

    public String getContactDescription() {
        return getString(CONTACT_DESCRIPTION);
    }

    public Bitmap getContactImageBitmap() {
        Bitmap bitmap = null;
        try {
            byte[] bitmapdata = getParseFile(CONTACT_IMAGE).getData();
            bitmap = BitmapFactory.decodeByteArray(bitmapdata, 0, bitmapdata.length);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public ParseFile getContactImageParseFile() {
        return getParseFile(CONTACT_IMAGE);
    }

    public String getContactPhone() {
        return getString(CONTACT_PHONE);
    }

    public String getContactCountry() {
        return getString(CONTACT_COUNTRY);
    }

    public String getContactBirthday() {
        return getString(CONTACT_BIRTHDAY);
    }
}
