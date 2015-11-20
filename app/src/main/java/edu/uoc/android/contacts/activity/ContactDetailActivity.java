package edu.uoc.android.contacts.activity;

import edu.uoc.android.contacts.ContactApplication;
import edu.uoc.android.contacts.R;
import edu.uoc.android.contacts.entities.Contact;
import edu.uoc.android.contacts.util.ParseDBUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactDetailActivity extends AppCompatActivity
{
    private ImageView contactPhotoDetail      ;
    private TextView  contactNameDetail       ;
    private TextView  contactPhoneDetail      ;
    private TextView  contactEmailDetail      ;
    private TextView  contactCityDetail       ;
    private TextView  contactDescriptionDetail;

    private ImageButton makeCall ;
    private ImageButton sendEmail;

    private Contact contact;

    //*****************************************************************************************************************
    // Private section
    //*****************************************************************************************************************

    /**
     * Initializes visual components
     */

    private void initializeVisualComponents()
    {
        contactPhotoDetail       = (ImageView) findViewById(R.id.contactPhotoDetail      );
        contactNameDetail        = (TextView)  findViewById(R.id.contactNameDetail       );
        contactPhoneDetail       = (TextView)  findViewById(R.id.contactPhoneDetail      );
        contactEmailDetail       = (TextView)  findViewById(R.id.contactEmailDetail      );
        contactCityDetail        = (TextView)  findViewById(R.id.contactCityDetail       );
        contactDescriptionDetail = (TextView)  findViewById(R.id.contactDescriptionDetail);

        makeCall                 = (ImageButton)  findViewById(R.id.makeCall );
        sendEmail                = (ImageButton)  findViewById(R.id.sendEmail);
    }

    //*****************************************************************************************************************
    // Private inner classes
    //*****************************************************************************************************************

    /**
     * Listener class activated when pushing make call button
     */

    public class MakeCallClickListener implements OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            if (contact != null)
            {
                String uri = "tel:" + contact.getContactPhone();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(uri));
                ContactDetailActivity.this.startActivity(intent);
            }
        }
    }

    /**
     * Listener class activated when pushing send email button
     */

    public class SendEmailListener implements OnClickListener
    {
        @Override
        public void onClick(View v) {
            if (contact != null)
            {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL, contact.getContactEmail());

                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        }
    }

    //*****************************************************************************************************************
    // Protected section
    //*****************************************************************************************************************

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.contact_detail);

        initializeVisualComponents();

        Intent intent = getIntent();

        String object_id = intent.getStringExtra(ContactApplication.OBJECT_ID);

        contact = ParseDBUtil.getContactById(object_id);

        if (contact != null)
        {
            contactPhotoDetail      .setImageBitmap(contact.getContactImageBitmap());
            contactNameDetail       .setText(contact.getContactName());
            contactPhoneDetail      .setText(contact.getContactPhone());
            contactEmailDetail      .setText(contact.getContactEmail());
            contactCityDetail       .setText(contact.getContactCity());
            contactDescriptionDetail.setText(contact.getContactDescription());
        }

        makeCall.setOnClickListener(new MakeCallClickListener());
        sendEmail.setOnClickListener(new SendEmailListener());
    }
}
