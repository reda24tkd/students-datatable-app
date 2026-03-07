package ensa.tps._4_jsf_datatable.bean;

import java.util.Date;

public class Student {
    private String cne;
    private String fname;
    private String lname;
    private String email;
    private Date birthDate;
    private boolean editMode = false;

    public Student() {
    }

    public Student(String cne, String fname, String lname, String email, Date birthDate) {
        this.cne = cne;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.birthDate = birthDate;
    }

    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean getEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }
}