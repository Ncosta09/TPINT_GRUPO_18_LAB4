package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Utils {

    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void closePreparedStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeStatement(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void closeResources(ResultSet rs, PreparedStatement ps) {
        closeResultSet(rs);
        closePreparedStatement(ps);
    }

    public static void closeResources(ResultSet rs, Statement stmt) {
        closeResultSet(rs);
        closeStatement(stmt);
    }

    public static void closeResources(PreparedStatement ps) {
        closePreparedStatement(ps);
    }

    public static void closeResources(PreparedStatement ps, Connection conn) {
        closePreparedStatement(ps);
    }

    public static void closeResources(Statement stmt) {
        closeStatement(stmt);
    }

    public static void closeResources(ResultSet rs, PreparedStatement ps, Connection conn) {
        closeResultSet(rs);
        closePreparedStatement(ps);
    }

    public static void closeResources(ResultSet rs, Statement stmt, Connection conn) {
        closeResultSet(rs);
        closeStatement(stmt);
    }
} 