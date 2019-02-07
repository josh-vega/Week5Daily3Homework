package com.example.week5daily3homework;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

public class ContactsManager {
    Context context;
    IContactsManager iContactsManager;

    public ContactsManager(Context context) {
        this.context = context;
        this.iContactsManager = (IContactsManager)context;
    }

    public void getContacts(){
        Uri contactsUri = ContactsContract.Contacts.CONTENT_URI;
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

        Cursor contactsCursor = context.getContentResolver().query(
                contactsUri, null, null, null, null
        );
        List<Contact> contactList = new ArrayList<>();
        while (contactsCursor.moveToNext()){

            String contactName = contactsCursor.getString(contactsCursor.getColumnIndex(DISPLAY_NAME));

            //Log.d(TAG, "getContacts: " + contactName);

            //retrieve phone numbers from contacts
            int hasNumberColumnIndex = contactsCursor.getColumnIndex(HAS_PHONE_NUMBER);
            int has_phone = contactsCursor.getInt(hasNumberColumnIndex);

            if (has_phone > 0){
                String email = "";
                List<String> numbers = new ArrayList<>();

                Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                Uri emailUri = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
                String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
                String EMAIL = ContactsContract.CommonDataKinds.Email.ADDRESS;

                Cursor phoneCursor = context.getContentResolver().query(
                        phoneUri,
                        new String[]{NUMBER},
                        DISPLAY_NAME + "=?"
                        , new String[]{contactName}
                        , NUMBER + " ASC"
                );

                Cursor emailCursor = context.getContentResolver().query(
                        emailUri,
                        null,
                        ContactsContract.CommonDataKinds.Email.CONTACT_ID
                        , null
                        , null
                );
                //Select PROJECTION from PHONEURI where SELECTION{SELECTION ARG) SORTORDER

                while (phoneCursor.moveToNext()) {

                    String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));

                    numbers.add(phoneNumber);

                }

                while (emailCursor.moveToNext()) {

                    email = emailCursor.getString(emailCursor.getColumnIndex(EMAIL));


                }
                Contact contact = new Contact(contactName, numbers, email);
                contactList.add(contact);
            }
        }
        iContactsManager.getContacts(contactList);
    }

    public interface IContactsManager{
        void getContacts(List<Contact> contact);
    }
}
