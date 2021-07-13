package com.example.lab_management.objects;

public class Term extends Subject {
    private String TermID;
    private String TermName;

    public Term(int index, String subjectID, String subjectName, String termID, String termName) {
        super(index, subjectID, subjectName);
        TermID = termID;
        TermName = termName;
    }

    public String getTermID() {
        return TermID;
    }

    public void setTermID(String termID) {
        TermID = termID;
    }

    public String getTermName() {
        return TermName;
    }

    public void setTermName(String termName) {
        TermName = termName;
    }
}
