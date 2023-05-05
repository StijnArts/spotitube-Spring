package han.stijn.spotitube.datasource.dao;


import han.stijn.spotitube.datasource.*;
import han.stijn.spotitube.datasource.preparedstatement.*;
import han.stijn.spotitube.datasource.preparedstatement.parameter.*;
import han.stijn.spotitube.dto.*;
import org.apache.commons.codec.digest.*;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.sql.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class LoginDAOTest {

    private static SpringPreparedStatementHelper mockedStatementHelper = Mockito.mock(SpringPreparedStatementHelper.class, Mockito.RETURNS_MOCKS);
    private static ResultSetReader mockedResultSetReader = Mockito.mock(ResultSetReader.class);
    private static LoginDAO sut = new LoginDAO();

    @BeforeAll
    public static void setup(){
        TokenGenerator tokenGenerator = new TokenGenerator();
        sut.setPreparedStatementHelper(mockedStatementHelper);
        sut.setResultSetReader(mockedResultSetReader);
        sut.setTokenGenerator(tokenGenerator);
    }
    @Test
    public void checkLoginWithIncorrectPassword() throws SQLException {
        //Arrange
        LoginDTO loginDTO = new LoginDTO("user","wrongPassword!");
        String query = "SELECT [PASSWORD] AS [password] FROM [USER] WHERE [USERNAME] = ?";
        var parameters = new ArrayList<ISQLParameter>();
        parameters.add(SQLParameterFactory.createParameter(1,loginDTO.getUser()));
        Mockito.doReturn(DigestUtils.sha256Hex("password123!")).when(mockedResultSetReader)
                .readString(ArgumentMatchers.isA(ResultSet.class),ArgumentMatchers.matches("password"));
        Mockito.doReturn(true, false).when(mockedResultSetReader).determineNextInResult(ArgumentMatchers.any(ResultSet.class));
        //Act

        //Assert
        assertEquals(false, sut.checkLogin(loginDTO));
    }

    @Test
    public void checkLoginWithCorrectPassword() throws SQLException {
        //Arrange
        LoginDTO loginDTO = new LoginDTO("user","password123!");
        String query = "SELECT [PASSWORD] AS [password] FROM [USER] WHERE [USERNAME] = ?";
        var parameters = new ArrayList<ISQLParameter>();
        parameters.add(SQLParameterFactory.createParameter(1,loginDTO.getUser()));
        Mockito.doReturn(DigestUtils.sha256Hex("password123!")).when(mockedResultSetReader)
                .readString(ArgumentMatchers.isA(ResultSet.class),ArgumentMatchers.matches("password"));
        Mockito.doReturn(true, false).when(mockedResultSetReader).determineNextInResult(ArgumentMatchers.any(ResultSet.class));
        //Act

        //Assert
        assertEquals(true, sut.checkLogin(loginDTO));
    }

    @Test
    public void checkIfTokenIsInserted(){
        //Arrange

        //Act
        sut.putToken("user");
        //Assert
        Mockito.verify(mockedStatementHelper).executeQueryWithoutResultWithTransaction(ArgumentMatchers.anyString(),ArgumentMatchers.isA(ArrayList.class));
    }

    @Test
    public void checkTokenWithIncorrectToken() throws SQLException {
        //Arrange
        Mockito.doReturn(true).when(mockedResultSetReader).determineNextInResult(ArgumentMatchers.isNull());
        Mockito.doReturn(false).when(mockedResultSetReader).determineNextInResult(ArgumentMatchers.isNotNull());
        //Act

        //Assert
        assertEquals(false, sut.checkToken("badtoken"));
    }

    @Test
    public void getUser() throws SQLException {
        //Arrange
        Mockito.doReturn("Full Name").when(mockedResultSetReader).readString(ArgumentMatchers.any(ResultSet.class),ArgumentMatchers.matches("fullName"));
        Mockito.doReturn("a token").when(mockedResultSetReader).readString(ArgumentMatchers.any(ResultSet.class),ArgumentMatchers.matches("token"));
        Mockito.doReturn(true).when(mockedResultSetReader).determineNextInResult(ArgumentMatchers.any(ResultSet.class));
        UserDTO userDTO = new UserDTO("Full Name", "a token");
        LoginDTO loginDTO = new LoginDTO("username", "password");
        //Act

        //Assert
        assertEquals(sut.getUser(loginDTO).toString(),userDTO.toString());
    }
}
