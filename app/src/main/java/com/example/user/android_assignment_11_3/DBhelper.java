package com.example.user.android_assignment_11_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by User on 06-02-2018.
 */

public class DBhelper {
    //created class as DBHelper

    public static final String EMP_ID = "id";
    public static final String EMP_NAME = "name";
    public static final String EMP_AGE = "age";
    public static final String EMP_PHOTO = "photo";
    //intializing the table id name age and photo

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String DATABASE_NAME = "EmployessDB.db";
    private static final int DATABASE_VERSION = 3;

    private static final String EMPLOYEES_TABLE = "Employees";
    //creating the database employee table
    private static final String CREATE_EMPLOYEES_TABLE = "create table "
            + EMPLOYEES_TABLE + " (" + EMP_ID
            + " integer primary key autoincrement, " + EMP_PHOTO
            + " blob not null, " + EMP_NAME + " text not null unique, "
            + EMP_AGE + " integer );";

    private final Context mCtx;
    //created final class mCtx.
    private static class DatabaseHelper extends SQLiteOpenHelper {
        //SQLiteOpenHelper:Exposes methods to manage a SQLite database.
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        //onCreate method
        public void onCreate(SQLiteDatabase db) {
            //Called when the database is created for the first time. This is where the creation of tables and the initial population of the tables should happen.
            // Parameters
            // db	SQLiteDatabase: The database.
            db.execSQL(CREATE_EMPLOYEES_TABLE);
        }
        //onUpgrade method
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //Called when the database needs to be upgraded. The implementation should use this method to drop tables, add tables, or do
            // anything else it needs to// upgrade to the new schema version.
            //db	SQLiteDatabase: The database.
            //  oldVersion	int: The old database version.
            // newVersion	int: The new database version.
            db.execSQL("DROP TABLE IF EXISTS " + EMPLOYEES_TABLE);
            onCreate(db);
        }
    }
    //reset method
    public void Reset() {
        mDbHelper.onUpgrade(this.mDb, 1, 1);
    }
    //constructor
    public DBhelper(Context ctx) {
        mCtx = ctx;
        mDbHelper = new DatabaseHelper(mCtx);
    }
    //opening database
    public DBhelper open() throws SQLException {
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }
    //closing database
    public void close() {
        mDbHelper.close();
    }
    //Insert method to perform insert operation
    public void insertEmpDetails(Employee employee) {
        mDb  = mDbHelper.getWritableDatabase();
        ////writing method in database
        ContentValues cv = new ContentValues();
        //created new object
        cv.put(EMP_PHOTO, Utility.getBytes(employee.getBitmap()));
        //getting the photo which is placed in utility
        cv.put(EMP_NAME, employee.getName());
        //getting the employment name
        cv.put(EMP_AGE, employee.getAge());
        //getting the employment age
        long id=mDb.insert(EMPLOYEES_TABLE, null, cv);
        //inserting all details in table
        if(id==-1){
            Toast.makeText(mCtx, "Not inserted...", Toast.LENGTH_SHORT).show();
            //giving as message as not inserted.
        }else{
            Toast.makeText(mCtx, "inserted successfully", Toast.LENGTH_SHORT).show();
            //giving as message as inserted succesfully
        }
    }

    public Employee retriveEmpDetails() throws SQLException {
        //retrive method to get employee details
        mDb = mDbHelper.getReadableDatabase();
        //SQLException:An exception that provides information on a database access error or other errors.
        Cursor cur = mDb.query(EMPLOYEES_TABLE, new String[] { EMP_PHOTO,
                EMP_NAME, EMP_AGE }, null, null, null, null, null, null);
        //This interface provides random read-write access to the result set returned by a database query.
        //cursor will move to first until cursor is in last column
        //it need to verify column index and need to move to next index
        if (cur.moveToFirst()) {
            byte[] blob = cur.getBlob(cur.getColumnIndex(EMP_PHOTO));
            //column of index of photo which is in byte
            //column of index of emp name in string
            String name = cur.getString(cur.getColumnIndex(EMP_NAME));
            int age = cur.getInt(cur.getColumnIndex(EMP_AGE));
           //close if cursor
            return new Employee(Utility.getPhoto(blob), name, age);
            //returns the photo ,name,age
        }
        cur.close();
        return null;//return null
    }
}

