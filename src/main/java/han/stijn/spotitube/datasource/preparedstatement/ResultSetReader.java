package han.stijn.spotitube.datasource.preparedstatement;

import org.springframework.context.annotation.*;

import java.sql.*;

@Configuration
public class ResultSetReader {
    public String readString(ResultSet result, String columnName) throws SQLException {
        return result.getString(columnName);
    }

    public int readInteger(ResultSet result, String columnName) throws SQLException {
        return result.getInt(columnName);
    }

    public boolean readBoolean(ResultSet result, String columnName) throws SQLException {
        return result.getBoolean(columnName);
    }

    public boolean determineNextInResult(ResultSet result) throws SQLException {
        return result.next();
    }
}
