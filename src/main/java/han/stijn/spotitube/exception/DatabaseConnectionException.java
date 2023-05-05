package han.stijn.spotitube.exception;

import java.sql.*;

public class DatabaseConnectionException extends RuntimeException{

    public DatabaseConnectionException(){

    }
    public DatabaseConnectionException(String response, SQLException e){
        super(response,e);
    }
}
