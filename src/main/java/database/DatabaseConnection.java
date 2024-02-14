package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private Connection conn;

    public static Connection getConnection() {
        if (instance.conn == null) {
            return instance.startConnection();
        }
        return instance.conn;
    }

    private Connection startConnection() {
        try {
            var connectionUrl = "jdbc:mysql://localhost:3306/administracion";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de MySQL: " + ex);
            }

            this.conn = DriverManager.getConnection(connectionUrl, "z0s3r77", "supersecret");

            boolean valid = conn.isValid(50000);
            System.out.println(valid ? "TEST OK" : "TEST FAIL");

            return this.conn;

        } catch (java.sql.SQLException sqle) {
            System.out.println("Error: " + sqle);
        }

        // En caso de que no se realice la conexión estamos perdidos
        return this.conn;
    }

    // Singleton pattern
    private static final DatabaseConnection instance = new DatabaseConnection();

    private DatabaseConnection() {
    }
}