package han.stijn.spotitube.datasource.preparedstatement;

import han.stijn.spotitube.datasource.preparedstatement.parameter.*;
import han.stijn.spotitube.exception.*;
import org.springframework.jdbc.core.*;

import java.sql.*;
import java.util.*;

public interface PreparedStatementHelper {
    public void executeQueryWithoutResultWithTransaction(String query, ArrayList<ISQLParameter> parameters);

    public List executeQueryWithResult(String query, ArrayList<ISQLParameter> parameters, RowMapper rowMapper);

    public List executeQueryWithResultAndTransaction(String query, ArrayList<ISQLParameter> parameters, RowMapper rowMapper);

    public void executeQueryWithoutResult(String query, ArrayList<ISQLParameter> parameters);
    private void prepareParameters(ArrayList<ISQLParameter> parameters, PreparedStatement preparedStatement) throws SQLException {
        for (ISQLParameter parameter : parameters) {
            parameter.putParameter(preparedStatement);
        }
    }
}
