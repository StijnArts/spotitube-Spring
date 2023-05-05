package han.stijn.spotitube.exception.mapper;

import han.stijn.spotitube.exception.*;
import han.stijn.spotitube.resource.mapper.*;
import jakarta.ws.rs.core.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TokenNotSetExceptionMapperTest {

    TokenNotSetExceptionMapper sut;

    @Test
    public void responseOnExceptionThrown(){
        //Arrange
        TokenNotSetException exception = new TokenNotSetException();
        sut = new TokenNotSetExceptionMapper();
        Response testResponse = Response.status(401).build();
        Response sutResponse;
        //Act
        sutResponse = sut.toResponse(exception);
        //Assert
        assertEquals(testResponse.getStatus(),sutResponse.getStatus());
    }
}
