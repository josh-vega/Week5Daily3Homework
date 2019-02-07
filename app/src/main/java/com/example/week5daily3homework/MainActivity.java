package com.example.week5daily3homework;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements PermissionsManager.IPermissionManager, ContactsManager.IContactsManager {
    PermissionsManager permissionsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permissionsManager = new PermissionsManager(this, this);
        permissionsManager.checkPermission();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.checkResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onPermissionResult(boolean isGranted) {
        Log.d("TAG", "onPermissionResult: YEP");
        if(isGranted){
            getContacts();
        } else {
            Toast.makeText(this, "Can not proceed.", Toast.LENGTH_SHORT).show();
        }
    }

    public void getContacts(){
        ContactsManager contactsManager = new ContactsManager(this);
        contactsManager.getContacts();
    }

    @Override
    public void getContacts(List<Contact> contacts) {
        for(Contact contact : contacts) {
            Log.d("TAG", "getContacts: " + contact.toString());
            if(!contact.getEmail().isEmpty()) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:developer@example.com"));
                Log.d("TAG", "getContacts: send email!");
                startActivity(emailIntent);
            }
        }
    }
}