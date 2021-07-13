package com.example.lab_management.objects;

public class Subject {
    private int index;
    private String SubjectID;
    private String SubjectName;

    public Subject(int index, String subjectID, String subjectName) {
        this.index = index;
        SubjectID = subjectID;
        SubjectName = subjectName;
    }

    public String getSubjectID() {
        return SubjectID;
    }

    public void setSubjectID(String subjectID) {
        SubjectID = subjectID;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String subjectName) {
        SubjectName = subjectName;
    }
}
