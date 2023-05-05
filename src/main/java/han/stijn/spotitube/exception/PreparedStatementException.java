package han.stijn.spotitube.exception;

import java.sql.*;

public class PreparedStatementException extends RuntimeException{
    public PreparedStatementException(){

    }
    public PreparedStatementException(String response, SQLException e){
        super(response,e);
    }
}
