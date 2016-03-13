package com.faravy.bitmtrainer401.sqliteexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nameET;
    EditText phoneNoET;
    private Contact contact;
    private DataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        nameET = (EditText) findViewById(R.id.inputName);
        phoneNoET = (EditText) findViewById(R.id.inputPhoneNo);
        dataSource = new DataSource(this);

    }

    public void saveContact(View view) {

        String name = nameET.getText().toString();
        String phoneNo = phoneNoET.getText().toString();

        contact = new Contact(name, phoneNo);

        boolean inserted = dataSource.addContact(contact);

        if (inserted) {
            Toast.makeText(getApplicationContext(), "Contact Added", Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(getApplicationContext(), "Contact Insertion Failed", Toast.LENGTH_LONG).show();
    }
}
