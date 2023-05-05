package han.stijn.spotitube.datasource;

import han.stijn.spotitube.datasource.properties.*;
import org.springframework.stereotype.*;

import java.sql.*;


public class DatabaseConnection extends DatabaseLogger {
    public static Connection getConnection(){
        var databaseProperties = new DatabaseProperties();
        Connection connection = null;
        try {
            Class.forName(databaseProperties.driverString());
            connection = DriverManager.getConnection(databaseProperties.connectionString());
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

}
