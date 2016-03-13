package com.faravy.bitmtrainer401.sqliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by BITM Trainer 401 on 3/10/2016.
 */
public class DataSource {

    private DataBaseHelper helper;
    private SQLiteDatabase database;
    private Contact contact;


    public DataSource(Context context) {
        helper = new DataBaseHelper(context);
    }

    private void open() {
        database = helper.getWritableDatabase();
    }

    private void close() {
        helper.close();
    }


    public boolean addContact(Contact contact) {

        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.COL_NAME, contact.getName());
        contentValues.put(DataBaseHelper.COL_PHONENO, contact.getPhoneNo());

        long inserted = database.insert(DataBaseHelper.TABLE_CONTACT_INFO, null, contentValues);

        database.close();
        this.close();

        if (inserted > 0) {
            return true;
        } else
            return false;

    }

    public Contact getContact(int id) {

        this.open();

        Cursor cursor = database.query(DataBaseHelper.TABLE_CONTACT_INFO, new String[]{DataBaseHelper.COL_ID, DataBaseHelper.COL_NAME, DataBaseHelper.COL_PHONENO},
                DataBaseHelper.COL_ID + " =" + id, null, null, null, null);

        cursor.moveToFirst();

        int mId = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_ID));
        String name = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_NAME));
        String phoneno = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_PHONENO));
        cursor.close();
        this.close();
        contact = new Contact(mId, name, phoneno);
        return contact;

    }

    public ArrayList<Contact> getAllContacts() {

        ArrayList<Contact> contactLIst = new ArrayList<>();
        this.open();

        Cursor cursor = database.query(DataBaseHelper.TABLE_CONTACT_INFO, null, null, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {

            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {

                int mId = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_NAME));
                String phoneno = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_PHONENO));
                contact = new Contact(mId, name, phoneno);
                contactLIst.add(contact);
                cursor.moveToNext();

            }
            cursor.close();
            this.close();
        }
        return contactLIst;
    }


    public boolean updateContact(int id, Contact contact) {
        this.open();

        ContentValues contentValues = new ContentValues();

        contentValues.put(DataBaseHelper.COL_NAME, contact.getName());
        contentValues.put(DataBaseHelper.COL_PHONENO, contact.getPhoneNo());

        int updated = database.update(DataBaseHelper.TABLE_CONTACT_INFO, contentValues, DataBaseHelper.COL_ID + " = " + id, null);
        this.close();

        if (updated > 0) {
            return true;
        } else return false;
    }

    public boolean deleteContact(int id) {
        this.open();

        int deleted = database.delete(DataBaseHelper.TABLE_CONTACT_INFO, DataBaseHelper.COL_ID + " = " + id, null);
        this.close();

        if (deleted > 0) {
            return true;
        } else return false;

    }

}
