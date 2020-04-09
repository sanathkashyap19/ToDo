package com.sanath.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Sanath on 31-10-2016.
 */
public class DBAdapter {

    private static final String TAG = "DBAdapter";
    private static final String DATABASE_NAME = "USER";   //Database Name
    private static final int DATABASE_VERSION = 1; //Change Version to upgrade db

    private final Context context;

    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {

            db.execSQL("CREATE TABLE LISTS(ID INTEGER NOT NULL PRIMARY KEY," +
                    "TITLE TEXT NOT NULL, BODY TEXT NOT NULL)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion)
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion
                    + " to "
                    + newVersion + ", which will destroy all old data");

            //Admin Table
            db.execSQL("DROP TABLE IF EXISTS MMR_USER");

            onCreate(db);
        }
    }

    //---opens the database---
    public DBAdapter open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public void close()
    {
        DBHelper.close();
    }


    //---insert a User into the database---
    public void insert(String TITLE,String BODY)
    {
        ContentValues initialValues = new ContentValues();

        initialValues.put("TITLE", TITLE);
        initialValues.put("BODY", BODY);

        db.insert("LISTS", null, initialValues);
    }


    //---deletes a particular Invoice---
    public boolean delete(int rowId)
    {
        return db.delete("LISTS",  "ID" +
                "=" + rowId, null) > 0;
    }

    //---Truncate table (delete all record from selected table), if already exists---
    public void truncateTable(String TableName)
    {
        db.execSQL("DELETE FROM "+TableName);
    }

//    //---retrieves all the Payment---
//    public Cursor getAllPayment()
//    {
//        return db.query("Payment", new String[] {
//         "_id",
//         "cusname",
//                "invoiceno",
//                "amt",
//                "cusid",
//                "paymode",
//                "chqno",
//                "chqdate",
//                "bank",
//                "discper",
//                "discval",
//                "year",
//                "bankid"},
//                null,
//                null,
//                null,
//                null,
//                "cusname");
//    }

    //---updates a LOGIN_STATUS : Y or N
    public boolean updateUserStatus(String Status )
    {
        ContentValues args = new ContentValues();
        args.put("NAME", Status);
        return db.update("MMR_USER", args,
                null, null) > 0;
    }

    //---updates a LOGIN_STATUS : LOGIN or LOGOUT
    public boolean update(String t, String b, int Id )
    {
        ContentValues args = new ContentValues();
        args.put("TITLE", t);
        args.put("BODY", b);
        return db.update("LISTS", args,
                "ID='" + Id+"'", null) > 0;
    }


    //executing query and it returns fields which are specified in the query ..........
    public Cursor getQueryResult(String MY_QUERY) throws SQLException
    {
        return db.rawQuery(MY_QUERY, null);
    }
}
