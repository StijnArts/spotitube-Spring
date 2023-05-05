package han.stijn.spotitube.exception.mapper;

import han.stijn.spotitube.exception.*;
import han.stijn.spotitube.resource.mapper.*;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Provider
public class UserNotFoundExceptionMapperTest {

    UserNotFoundExceptionMapper sut;

    @Test
    public void responseOnExceptionThrown(){
        //Arrange
        UserNotFoundException exception = new UserNotFoundException();
        sut = new UserNotFoundExceptionMapper();
        Response testResponse = Response.status(403).build();
        Response sutResponse;
        //Act
        sutResponse = sut.toResponse(exception);
        //Assert
        assertEquals(testResponse.getStatus(),sutResponse.getStatus());
    }
}
