package han.stijn.spotitube.datasource.preparedstatement;

import han.stijn.spotitube.datasource.*;
import han.stijn.spotitube.datasource.preparedstatement.parameter.*;
import han.stijn.spotitube.exception.*;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.sql.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JakartaPreparedStatementHelperTest {

    private static JakartaPreparedStatementHelper sut = new JakartaPreparedStatementHelper();
    private static DatabaseConnection mockedConnection = Mockito.mock(DatabaseConnection.class);

    @BeforeAll
    public static void setup(){
        sut.setDatabaseConnection(mockedConnection);
    }

    @Test
    public void executeQueryWithoutResultWithTransaction() throws SQLException {
        //Arrange
        ArrayList<ISQLParameter> parameters = new ArrayList<>();
        parameters.add(new StringParameter(1,"string"));
        //Act
        //Assert
        assertThrows(PreparedStatementException.class,() ->sut.executeQueryWithoutResultWithTransaction("?",parameters));
    }
}
