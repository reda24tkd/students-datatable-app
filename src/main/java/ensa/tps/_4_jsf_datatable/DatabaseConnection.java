package ensa.tps._4_jsf_datatable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;
//    private static final String URL = "jdbc:mysql://localhost:3306/student_db";
//    private static final String USER = "root";
//    private static final String PASSWORD = "";
    String host = System.getenv("MYSQL_HOST");
    String port = System.getenv("MYSQL_PORT");
    String db   = System.getenv("MYSQL_DATABASE");
    String user = System.getenv("MYSQL_USER");
    String pass = System.getenv("MYSQL_PASSWORD");

    String url = "jdbc:mysql://" + host + ":" + port + "/" + db;
    private DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
//            connection = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
