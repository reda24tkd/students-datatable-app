package ensa.tps._4_jsf_datatable;


import ensa.tps._4_jsf_datatable.bean.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

public class StudentDAO {
    private static final Logger logger = Logger.getLogger(StudentDAO.class.getName());

    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        try (Statement stmt = DatabaseConnection.getInstance().getConnection().createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM students")) {
            
            while (rs.next()) {
                students.add(new Student(
                    rs.getString("cne"),
                    rs.getString("fname"),
                    rs.getString("lname"),
                    rs.getString("email"),
                    rs.getDate("birth_date")
                ));
            }
            logger.info("Retrieved " + students.size() + " students from database");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving students", e);
        }
        return students;
    }

    public void addStudent(Student student) {
        try (PreparedStatement stmt = DatabaseConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO students VALUES (?, ?, ?, ?, ?)")) {
            stmt.setString(1, student.getCne());
            stmt.setString(2, student.getFname());
            stmt.setString(3, student.getLname());
            stmt.setString(4, student.getEmail());
            stmt.setDate(5, new java.sql.Date(student.getBirthDate().getTime()));
            stmt.executeUpdate();
            logger.info("Student inserted into database: CNE=" + student.getCne());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error adding student: CNE=" + student.getCne(), e);
        }
    }

    public void updateStudent(Student student) {
        try (PreparedStatement stmt = DatabaseConnection.getInstance().getConnection()
                .prepareStatement("UPDATE students SET fname=?, lname=?, email=?, birth_date=? WHERE cne=?")) {
            stmt.setString(1, student.getFname());
            stmt.setString(2, student.getLname());
            stmt.setString(3, student.getEmail());
            stmt.setDate(4, new java.sql.Date(student.getBirthDate().getTime()));
            stmt.setString(5, student.getCne());
            stmt.executeUpdate();
            logger.info("Student updated in database: CNE=" + student.getCne());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating student: CNE=" + student.getCne(), e);
        }
    }

    public void deleteStudent(String cne) {
        try (PreparedStatement stmt = DatabaseConnection.getInstance().getConnection()
                .prepareStatement("DELETE FROM students WHERE cne=?")) {
            stmt.setString(1, cne);
            stmt.executeUpdate();
            logger.info("Student deleted from database: CNE=" + cne);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting student: CNE=" + cne, e);
        }
    }
}