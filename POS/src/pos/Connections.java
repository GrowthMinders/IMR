package pos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connections {

    public static Connection connect() {
        String url = "jdbc:sqlserver://localhost\\ROG-STRIX:1433;databaseName=supermart;encrypt=true;trustServerCertificate=true";
        String username = "Supun";
        String password = "2002";

        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Establish the connection
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection established!");
            return conn;

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Connection could not be established: " + ex.getMessage());
            return null; 
        }
    }
}
