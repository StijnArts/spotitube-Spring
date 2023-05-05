package han.stijn.spotitube.resource;


import han.stijn.spotitube.datasource.dao.*;
import han.stijn.spotitube.dto.*;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;


public class LoginResourceTest {
    private LoginResource sut;
    private LoginDAO mockedLoginDAO;
    @BeforeEach
    public void setup(){
        sut = new LoginResource();
        this.mockedLoginDAO = Mockito.mock(LoginDAO.class);
        this.sut.setLoginDAO(mockedLoginDAO);
    }

    @Test
    public void executeBadLogin(){
        //Arrange
        ResponseEntity testResponse = ResponseEntity.status(403).build();
        LoginDTO testLoginDTO = new LoginDTO("melon", "MySuperSecretPassword12341");
        Mockito.when(mockedLoginDAO.checkLogin(testLoginDTO)).thenReturn(false);
        //Act

        //Assemble
        assertEquals(testResponse.getStatusCode(), sut.login(testLoginDTO).getStatusCode());
    }

    @Test
    public void executeGoodLogin(){
        //Arrange
        ResponseEntity testResponse = ResponseEntity.status(200).build();
        LoginDTO testLoginDTO = new LoginDTO("meron", "MySuperSecretPassword12341");
        UserDTO mockedUserDTO = new UserDTO("full name","1234-1234-1234");
        Mockito.when(mockedLoginDAO.getUser(testLoginDTO)).thenReturn(mockedUserDTO);
        Mockito.when(mockedLoginDAO.checkLogin(testLoginDTO)).thenReturn(true);
        //Act
        var sutStatus = sut.login(testLoginDTO).getStatusCode();
        var testStatus = testResponse.getStatusCode();
        //Assemble
        assertEquals(testStatus, sutStatus);
    }
}
