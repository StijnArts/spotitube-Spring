package han.stijn.spotitube.exception;

import java.sql.*;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){

    }
    public UserNotFoundException(String response, SQLException e){
        super(response,e);
    }
}
