package han.stijn.spotitube.datasource.preparedstatement.parameter;

import java.sql.*;

public interface ISQLParameter {
    public void putParameter(PreparedStatement preparedStatement) throws SQLException;
}
