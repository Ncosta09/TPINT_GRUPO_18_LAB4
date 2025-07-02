package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/bdGestionBanco";
    private static final String USER = "root";
    private static final String PASS = "root";

    private static Conexion instancia;

    private Conexion() {
    }

    public static Conexion getConexion() {
        if (instancia == null) {
            synchronized (Conexion.class) {
                if (instancia == null) {
                    instancia = new Conexion();
                }
            }
        }
        return instancia;
    }


    public Connection getSQLConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, USER, PASS);
            connection.setAutoCommit(true);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void cerrarConexion(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void resetearInstancia() {
        instancia = null;
    }
}
