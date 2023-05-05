package han.stijn.spotitube.datasource.preparedstatement;

import org.junit.jupiter.api.*;
import org.mockito.*;

import java.sql.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class ResultSetReaderTest {

    private ResultSetReader sut = new ResultSetReader();
    private static ResultSet resultSet = Mockito.mock(ResultSet.class);

    @Test
    public void readString() throws SQLException {
        //Arrange
        Mockito.doReturn("string").when(resultSet).getString("column");
        //Act

        //Assert
        assertEquals("string", sut.readString(resultSet,"column"));
    }

    @Test
    public void readInteger() throws SQLException {
        //Arrange
        Mockito.doReturn(0).when(resultSet).getInt("column");
        //Act

        //Assert
        assertEquals(0, sut.readInteger(resultSet,"column"));
    }

    @Test
    public void readBoolean() throws SQLException {
        //Arrange
        Mockito.doReturn(false).when(resultSet).getBoolean("column");
        //Act

        //Assert
        assertEquals(false, sut.readBoolean(resultSet,"column"));
    }

    @Test
    public void determineNextInResult() throws SQLException {
        //Arrange
        Mockito.doReturn(true).when(resultSet).next();
        //Act

        //Assert
        assertEquals(true, sut.determineNextInResult(resultSet));
    }
}
