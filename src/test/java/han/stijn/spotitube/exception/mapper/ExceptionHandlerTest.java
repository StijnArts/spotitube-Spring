//package han.stijn.spotitube.exception.mapper;
//
//import han.stijn.spotitube.exception.*;
//import han.stijn.spotitube.resource.mapper.*;
//import jakarta.ws.rs.core.*;
//import org.junit.jupiter.api.*;
//import org.springframework.http.*;
//
//public class ExceptionHandlerTest {
//
//    ExceptionHandler sut;
//
//    @Test
//    public void responseOnExceptionThrown(){
//        //Arrange
//        DatabaseConnectionException exception = new DatabaseConnectionException();
//        sut = new ExceptionHandler();
//        ResponseEntity testResponse = ResponseEntity.status(500).build();
//        ResponseEntity sutResponse;
//        //Act
//        sutResponse = sut.toResponse(exception);
//        //Assert
//        assertEquals(testResponse.getStatus(),sutResponse.getStatus());
//    }
//}
