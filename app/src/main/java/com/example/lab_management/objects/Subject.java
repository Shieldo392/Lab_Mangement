package com.example.lab_management.objects;

public class Subject {
    private int SubjectID;
    private String SubjectName;

    public Subject(int subjectID, String subjectName) {
        SubjectID = subjectID;
        SubjectName = subjectName;
    }

    public int getSubjectID() {
        return SubjectID;
    }

    public void setSubjectID(int subjectID) {
        SubjectID = subjectID;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String subjectName) {
        SubjectName = subjectName;
    }
}
