package com.example.admin.contentproviderapp.view;

import android.content.ContentValues;
import android.content.Context;
import android.widget.Toast;

import com.example.admin.contentproviderapp.ProviderClass;
import com.example.admin.contentproviderapp.adapter.StudentAdapter;
import com.example.admin.contentproviderapp.data.local.DbHelper;
import com.example.admin.contentproviderapp.model.Student;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Admin on 8/23/2017.
 */

public class MainActivityPresenter implements MainActivityContract.Presenter{

    MainActivityContract.View view;
    Context context;
    //Adapter
    StudentAdapter adapter;
    //Firebase
    FirebaseDatabase database;
    DatabaseReference studentReference;

    @Override
    public void attachView(MainActivityContract.View view) {
        this.view = view;
        init();
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

    @Override
    public void saveStudent(Student student) {
        student.setSchoolId(getStudentId());
        String childName = student.getStudentFirstName() +
                String.valueOf(student.getSchoolId()).substring(0,4);
        studentReference.child(childName).setValue(student);

        //Save using Content Provider
        ContentValues cv = new ContentValues();
        cv.put(DbHelper.STUDENT_ID, student.getSchoolId());
        cv.put(DbHelper.FIRSTNAME, student.getStudentFirstName());
        cv.put(DbHelper.LASTNAME, student.getStudentLastName());
        cv.put(DbHelper.GRAD_DATE, student.getExpectedGradDate());
        cv.put(DbHelper.GPA, student.getGpa());
        context.getContentResolver().insert(ProviderClass.CONTENT_URL, cv);

        Toast.makeText(context, "Successfully Added to Local and Remote!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadStudents() {
        final List<Student> students = new ArrayList<>();

        studentReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Student student = snapshot.getValue(Student.class);
                    students.add(student);
                }

                initRecyclerView(students);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void init() {
        database = FirebaseDatabase.getInstance();
        studentReference = database.getReference("students");
    }

    @Override
    public int getStudentId() {
        Random random = new Random();
        return random.nextInt(100000000) + 1;
    }

    @Override
    public void initRecyclerView(List<Student> bookList) {
        StudentAdapter adapter = new StudentAdapter(bookList);
        view.loadStudents(adapter);
    }
}
