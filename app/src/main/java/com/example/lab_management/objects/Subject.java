package com.example.lab_management.objects;

public class Subject {
    private int index;
    private int SubjectID;
    private String SubjectName;

    public Subject(int index, int subjectID, String subjectName) {
        this.index = index;
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
