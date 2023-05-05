package han.stijn.spotitube.exception;

import java.sql.*;

public class IncompleteQueryResultException extends RuntimeException{
    public IncompleteQueryResultException(){

    }
    public IncompleteQueryResultException(String response, SQLException e){
        super(response,e);
    }
}
