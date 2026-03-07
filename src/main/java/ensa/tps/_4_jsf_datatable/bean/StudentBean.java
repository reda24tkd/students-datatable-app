package ensa.tps._4_jsf_datatable.bean;

import ensa.tps._4_jsf_datatable.StudentDAO;

import java.util.List;

public class StudentBean {
    private final StudentDAO dao = new StudentDAO();
    private List<Student> students;
    private Student studentToAdd = new Student();

    public StudentBean() {
        students = dao.getStudents();
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent() {
        dao.addStudent(studentToAdd);
        studentToAdd = new Student();
        students = dao.getStudents();
    }

    public void updateStudent(Student student) {
        if (student.getEditMode()) {
            dao.updateStudent(student);
            student.setEditMode(false);
            students = dao.getStudents();
            System.out.println("Student updated: " + student.getCne() + " - " + student.getFname() + " " + student.getLname() + " - " + student.getEmail() + " - " + student.getBirthDate());
        } else {
            student.setEditMode(true);
            System.out.println("Edit mode activated: " + student.getCne() + " - " + student.getFname() + " " + student.getLname() + " - " + student.getEmail() + " - " + student.getBirthDate());

        }
    }

    public void deleteStudent(Student student) {
        dao.deleteStudent(student.getCne());
        students = dao.getStudents();
    }

    public Student getStudentToAdd() {
        return studentToAdd;
    }

    public void setStudentToAdd(Student studentToAdd) {
        this.studentToAdd = studentToAdd;
    }
}