package com.example.admin.contentproviderapp.view;

import android.content.Context;

import com.example.admin.contentproviderapp.BasePresenter;
import com.example.admin.contentproviderapp.BaseView;
import com.example.admin.contentproviderapp.adapter.StudentAdapter;
import com.example.admin.contentproviderapp.model.Student;

import java.util.List;

/**
 * Created by Admin on 8/23/2017.
 */

public interface MainActivityContract {

    interface View extends BaseView{

        void loadStudents(StudentAdapter adapter);
        void updateDB();
    }

    interface Presenter extends BasePresenter<View>{

        void getContext(Context context);
        void saveStudent(Student student);
        void loadStudents();
        void init();
        int getStudentId();
        void initRecyclerView(List<Student> bookList);

    }
}
