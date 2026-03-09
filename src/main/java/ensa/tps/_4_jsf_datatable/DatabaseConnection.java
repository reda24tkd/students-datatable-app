package ensa.tps._4_jsf_datatable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;

public class DatabaseConnection {
    private static final Logger logger = Logger.getLogger(DatabaseConnection.class.getName());
    private static DatabaseConnection instance;
    private Connection connection;
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
            logger.info("Database connection established: " + host + ":" + port + "/" + db);
        } catch (ClassNotFoundException | SQLException e) {
            logger.log(Level.SEVERE, "Failed to establish database connection", e);
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
