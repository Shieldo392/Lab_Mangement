package com.example.lab_management.objects;

public class RegisterLab {
    private String registerID;
    private Lab lab;
    private User user;
    private String session; // ca thực hành
    private String time; // thứ mấy trong tuần
    private int count; // số buổi thực hành
    private Term term;

    public RegisterLab(String registerID, Lab lab, User user, String session, String time, int count, Term term) {
        this.registerID = registerID;
        this.lab = lab;
        this.user = user;
        this.session = session;
        this.time = time;
        this.count = count;
        this.term = term;
    }

    public String getRegisterID() {
        return registerID;
    }

    public void setRegisterID(String registerID) {
        this.registerID = registerID;
    }

    public Lab getLab() {
        return lab;
    }

    public void setLab(Lab lab) {
        this.lab = lab;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }
}
