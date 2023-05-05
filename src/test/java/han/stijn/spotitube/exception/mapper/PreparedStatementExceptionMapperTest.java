package han.stijn.spotitube.exception.mapper;

import han.stijn.spotitube.exception.*;
import han.stijn.spotitube.resource.mapper.*;
import jakarta.ws.rs.core.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PreparedStatementExceptionMapperTest {

    PreparedStatementExceptionMapper sut;

    @Test
    public void responseOnExceptionThrown(){
        //Arrange
        PreparedStatementException exception = new PreparedStatementException();
        sut = new PreparedStatementExceptionMapper();
        Response testResponse = Response.status(500).build();
        Response sutResponse;
        //Act
        sutResponse = sut.toResponse(exception);
        //Assert
        assertEquals(testResponse.getStatus(),sutResponse.getStatus());
    }
}
