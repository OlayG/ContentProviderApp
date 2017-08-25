package com.example.admin.contentproviderapp.model;

/**
 * Created by Admin on 8/23/2017.
 */

public class Student {

    int schoolId;
    String studentFirstName, studentLastName, expectedGradDate;
    double gpa;

    public Student() {
    }

    public Student(int schoolId, String studentFirstName, String studentLastName, String expectedGradDate, double gpa) {
        this.schoolId = schoolId;
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.expectedGradDate = expectedGradDate;
        this.gpa = gpa;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public String getExpectedGradDate() {
        return expectedGradDate;
    }

    public void setExpectedGradDate(String expectedGradDate) {
        this.expectedGradDate = expectedGradDate;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "schoolId=" + schoolId +
                ", studentFirstName='" + studentFirstName + '\'' +
                ", studentLastName='" + studentLastName + '\'' +
                ", expectedGradDate='" + expectedGradDate + '\'' +
                ", gpa=" + gpa +
                '}';
    }
}
