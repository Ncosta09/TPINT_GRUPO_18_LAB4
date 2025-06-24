package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/bdGestionBanco";
    private static final String USER = "root";
    private static final String PASS = "1234";

    private static Connection connection = null;

    public static Connection getConexion() {
        if (connection == null) {
            try {
            	Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASS);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void cerrarConexion() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}