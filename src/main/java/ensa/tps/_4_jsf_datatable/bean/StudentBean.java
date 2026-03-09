package ensa.tps._4_jsf_datatable.bean;

import ensa.tps._4_jsf_datatable.StudentDAO;
import ensa.tps._4_jsf_datatable.entity.Student;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

public class StudentBean {
    private static final Logger logger = Logger.getLogger(StudentBean.class.getName());
    private final StudentDAO dao = new StudentDAO();
    private List<Student> students;
    private Student studentToAdd = new Student();

    public StudentBean() {
        students = dao.getStudents();
        logger.info("StudentBean initialized");
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent() {
        logger.info("Adding student: CNE=" + studentToAdd.getCne() + ", Name=" + studentToAdd.getFname() + " " + studentToAdd.getLname());
        dao.addStudent(studentToAdd);
        studentToAdd = new Student();
        students = dao.getStudents();
        logger.info("Student added successfully");
    }

    public void updateStudent(Student student) {
        if (student.getEditMode()) {
            logger.info("Updating student: CNE=" + student.getCne());
            dao.updateStudent(student);
            student.setEditMode(false);
            students = dao.getStudents();
            logger.info("Student updated: " + student.getCne() + " - " + student.getFname() + " " + student.getLname());
        } else {
            student.setEditMode(true);
            logger.info("Edit mode activated for student: CNE=" + student.getCne());
        }
    }

    public void deleteStudent(Student student) {
        logger.warning("Deleting student: CNE=" + student.getCne() + ", Name=" + student.getFname() + " " + student.getLname());
        dao.deleteStudent(student.getCne());
        students = dao.getStudents();
        logger.info("Student deleted successfully");
    }

    public Student getStudentToAdd() {
        return studentToAdd;
    }

    public void setStudentToAdd(Student studentToAdd) {
        this.studentToAdd = studentToAdd;
    }
}