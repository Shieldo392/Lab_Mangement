package com.example.lab_management.objects;

public class Term {
    private int TermID;
    private String TermName;
    private int SubjectID;

    public Term(int termID, String termName, int subjectID) {
        TermID = termID;
        TermName = termName;
        SubjectID = subjectID;
    }

    public int getTermID() {
        return TermID;
    }

    public void setTermID(int termID) {
        TermID = termID;
    }

    public String getTermName() {
        return TermName;
    }

    public void setTermName(String termName) {
        TermName = termName;
    }

    public int getSubjectID() {
        return SubjectID;
    }

    public void setSubjectID(int subjectID) {
        SubjectID = subjectID;
    }
}
