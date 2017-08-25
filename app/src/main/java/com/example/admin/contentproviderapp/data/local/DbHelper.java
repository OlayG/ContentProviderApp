package com.example.admin.contentproviderapp.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.admin.contentproviderapp.model.Student;

import java.util.ArrayList;

/**
 * Created by Admin on 8/23/2017.
 */

public class DbHelper extends SQLiteOpenHelper{

    public static final String TAG = "DatabaseHelper";

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ContactsDatabase";

    public static final String TABLE_NAME = "Contacts";
    public static final String STUDENT_ID = "StudentId";
    public static final String FIRSTNAME = "FirstName";
    public static final String LASTNAME = "LastName";
    public static final String GRAD_DATE = "PhoneNumber";
    public static final String GPA = "gpa";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            "Id" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            STUDENT_ID + " INTEGER " + FIRSTNAME +
            " TEXT," + LASTNAME + " TEXT," + GRAD_DATE + " TEXT," +
            GPA + " REAL" + ")";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if EXIST " + TABLE_NAME);
        onCreate(db);
    }

    public void saveNewContact(Student student){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(STUDENT_ID, student.getSchoolId());
        cv.put(FIRSTNAME, student.getStudentFirstName());
        cv.put(LASTNAME, student.getStudentLastName());
        cv.put(GRAD_DATE, student.getExpectedGradDate());
        cv.put(GPA, student.getGpa());

        db.insert(TABLE_NAME, null, cv);
    }

    public ArrayList<Student> getStudent(){

        ArrayList<Student> contacts = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                Student contact = new Student(
                        cursor.getInt(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4),
                        cursor.getDouble(5)
                );
                contacts.add(contact);
            } while (cursor.moveToNext());
        }

        return contacts;
    }


    public Student getOneStudent(int position){

        Student student = new Student();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE "+ STUDENT_ID + "=" + 0;

        //String[] selectionArg = new String[]{CONTACT_NAME, "John"};

        Cursor cursor = db.rawQuery(query, null);

        if(cursor == null)
            return null;

        student = new Student(
                cursor.getInt(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4),
                cursor.getFloat(5)
        );

        return student;
    }

    public void deleteContact(int position){

        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + STUDENT_ID + "=" + position;
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, STUDENT_ID + " = ?", new String[] {String.valueOf(position)});

        Cursor cursor = db.rawQuery(query, null);
    }
}
