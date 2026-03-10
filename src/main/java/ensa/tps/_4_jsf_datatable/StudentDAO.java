package ensa.tps._4_jsf_datatable;

import ensa.tps._4_jsf_datatable.entity.Student;
import jakarta.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

public class StudentDAO {
    private static final Logger logger = Logger.getLogger(StudentDAO.class.getName());
    private EntityManagerFactory emf;

    public StudentDAO() {
        Map<String, String> properties = new HashMap<>();
        String host = System.getenv("MYSQL_HOST");
        if (host != null) {
            properties.put("jakarta.persistence.jdbc.url", 
                String.format("jdbc:mysql://%s:%s/%s",
                    System.getenv("MYSQL_HOST"),
                    System.getenv("MYSQL_PORT"),
                    System.getenv("MYSQL_DATABASE")));
            properties.put("jakarta.persistence.jdbc.user", System.getenv("MYSQL_USER"));
            properties.put("jakarta.persistence.jdbc.password", System.getenv("MYSQL_PASSWORD"));
        }
        emf = Persistence.createEntityManagerFactory("StudentPU", properties);
    }

    public List<Student> getStudents() {
        EntityManager em = emf.createEntityManager();
        try {
            List<Student> students = em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
            logger.info("Retrieved " + students.size() + " students from database");
            return students;
        } finally {
            em.close();
        }
    }

    public void addStudent(Student student) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
            logger.info("Student inserted into database: CNE=" + student.getCne());
        } catch (Exception e) {
            em.getTransaction().rollback();
            logger.log(Level.SEVERE, "Error adding student: CNE=" + student.getCne(), e);
        } finally {
            em.close();
        }
    }

    public void updateStudent(Student student) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(student);
            em.getTransaction().commit();
            logger.info("Student updated in database: CNE=" + student.getCne());
        } catch (Exception e) {
            em.getTransaction().rollback();
            logger.log(Level.SEVERE, "Error updating student: CNE=" + student.getCne(), e);
        } finally {
            em.close();
        }
    }

    public void deleteStudent(String cne) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Student student = em.find(Student.class, cne);
            if (student != null) {
                em.remove(student);
            }
            em.getTransaction().commit();
            logger.info("Student deleted from database: CNE=" + cne);
        } catch (Exception e) {
            em.getTransaction().rollback();
            logger.log(Level.SEVERE, "Error deleting student: CNE=" + cne, e);
        } finally {
            em.close();
        }
    }
}
