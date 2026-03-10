package ensa.tps._4_jsf_datatable.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "students")
public class Student {
    @Id
    private String cne;
    private String fname;
    private String lname;
    private String email;
    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    private Double scholarship;

    @Transient
    private boolean editMode = false;

    public Student() {
    }

    public Student(String cne, String fname, String lname, String email, Date birthDate, Double scholarship) {
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

    public Double getScholarship() {
        return scholarship;
    }

    public void setScholarship(Double scholarship) {
        this.scholarship = scholarship;
    }
}
