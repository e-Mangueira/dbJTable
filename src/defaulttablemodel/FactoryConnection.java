package defaulttablemodel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FactoryConnection {

    //Dados para a conexão com o banco
    private static final String USER = "root";
    private static final String PASSWORD = "N1lt0n#Mysql";
    private static final String DATABASE = "dbJtable";
    private static final String DRIVER_CONNECTION = "com.mysql.cj.jdbc.Driver";
    private static final String STR_CONNECTION = "jdbc:mysql://localhost:3306/";

    public static Connection getConexao() throws SQLException, ClassNotFoundException {

        Connection conn = null;
        try {
            Class.forName(DRIVER_CONNECTION);
            conn = DriverManager.getConnection(STR_CONNECTION + DATABASE, USER, PASSWORD);

            return conn;

        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException(
                    "MySql driver not found" + e.getMessage());

        } catch (SQLException e) {
            throw new SQLException("Conenction error" + e.getMessage());
        }
    }

    public static void closeConnection(Connection conn) {

        try {
            if (conn != null) {
                conn.close();
                System.out.println("Database connection closed");
            }

        } catch (Exception e) {
            System.out.println("Could not close database connection" + e.getMessage());
        }
    }

    public static void closeConnection(Connection conn, PreparedStatement stmt) {

        try {
            if (conn != null) {
                closeConnection(conn);
            }
            if (stmt != null) {
                stmt.close();
                System.out.println("Statement closed successfully");
            }


        } catch (Exception e) {
            System.out.println("Could not close statement" + e.getMessage());
        }
    }

    public static void closeConnection(Connection conn, PreparedStatement stmt, ResultSet rs) {

        try {
            if (conn != null || stmt != null) {
                closeConnection(conn, stmt);
            }
            if (rs != null) {
                rs.close();
                System.out.println("ResultSet closed successfully");
            }


        } catch (Exception e) {
            System.out.println("Could not close ResultSet" + e.getMessage());
        }
    }
}

