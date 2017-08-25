package com.example.admin.contentproviderapp.view;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.admin.contentproviderapp.R;
import com.example.admin.contentproviderapp.adapter.StudentAdapter;
import com.example.admin.contentproviderapp.injection.DaggerMainActivityComponent;
import com.example.admin.contentproviderapp.model.Student;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    private static final String TAG = "MainActivity";

    @Inject
    MainActivityPresenter presenter;
    @BindView(R.id.rvStudentList)
    RecyclerView rvStudentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        DaggerMainActivityComponent.create().inject(this);
        presenter.attachView(this);
        //updateDB();
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void loadStudents(StudentAdapter adapter) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        rvStudentList.setLayoutManager(layoutManager);
        rvStudentList.setItemAnimator(itemAnimator);
        rvStudentList.addItemDecoration(dividerItemDecoration);
        rvStudentList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void updateDB() {
        Student student = new Student(0, "John", "Snow", "May 2018", 2.89);
        presenter.saveStudent(student);
        Student student1 = new Student(0, "Arya", "Stark", "Dec 2022", 3.89);
        presenter.saveStudent(student1);
        Student student2 = new Student(0, "Max", "Kellerman", "Dec 2018", 1.69);
        presenter.saveStudent(student2);
        Student student3 = new Student(0, "Nancy", "Grace", "May 2020", 4.00);
        presenter.saveStudent(student3);
        Student student4 = new Student(0, "Michael", "Jordan", "May 2018", 2.45);
        presenter.saveStudent(student4);
        Student student5 = new Student(0, "Pickle", "Rick", "Dec 2017", 10.0);
        presenter.saveStudent(student5);
        Student student6 = new Student(0, "Ashad", "Khaled", "May 2018", 3.34);
        presenter.saveStudent(student6);
        Student student7 = new Student(0, "Rhianna", "Fenty", "May 2018", 3.47);
        presenter.saveStudent(student7);
        Student student8 = new Student(0, "Kyle", "Brown", "Dec 2017", 3.62);
        presenter.saveStudent(student8);
        Student student9 = new Student(0, "Estaban", "Corrierra", "May 2021", 2.6);
        presenter.saveStudent(student9);
    }

    public void StudentButtons(View view) {

        switch (view.getId()){

            case R.id.btnAddStudent:

                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.new_student_item);
                dialog.setTitle("Add a new Student");

                Button btnDialog = (Button) dialog.findViewById(R.id.btnSaveStudent);
                btnDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText etFirstName = (EditText) dialog.findViewById(R.id.etFirstName);
                        EditText etLastName = (EditText) dialog.findViewById(R.id.etLastName);
                        EditText etGradDate = (EditText) dialog.findViewById(R.id.etGradDate);
                        EditText etGpa = (EditText) dialog.findViewById(R.id.etGpa);
                        final Student student = new Student(
                                0,
                                etFirstName.getText().toString(),
                                etLastName.getText().toString(),
                                etGradDate.getText().toString(),
                                Double.valueOf(etGpa.getText().toString())
                        );
                        presenter.saveStudent(student);
                        dialog.dismiss();
                    }
                });
                dialog.show();

                break;

            case R.id.btnLoadStudents:

                presenter.loadStudents();

                break;
        }
    }
}
