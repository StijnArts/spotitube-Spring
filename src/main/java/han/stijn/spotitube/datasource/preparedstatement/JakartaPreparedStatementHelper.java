//package han.stijn.spotitube.datasource.preparedstatement;
//
//import han.stijn.spotitube.datasource.*;
//import han.stijn.spotitube.datasource.preparedstatement.parameter.*;
//import han.stijn.spotitube.exception.*;
//import org.springframework.beans.factory.annotation.*;
//
//import java.sql.*;
//import java.util.*;
//
//public class JakartaPreparedStatementHelper implements PreparedStatementHelper {
//
//    private DatabaseConnection databaseConnection;
//
//    @Override
//    public void executeQueryWithoutResultWithTransaction(String query, ArrayList<ISQLParameter> parameters){
//        try {
//            databaseConnection.getConnection().setAutoCommit(false);
//            executeQueryWithoutResult(query,parameters);
//            databaseConnection.getConnection().commit();
//            databaseConnection.getConnection().setAutoCommit(true);
//        } catch (SQLException e) {
//            throw new DatabaseConnectionException("Something went wrong connecting to the database.",e);
//        }
//    }
//
//    public ResultSet executeQueryWithResult(String query, ArrayList<ISQLParameter> parameters){
//        PreparedStatement preparedStatement;
//        ResultSet resultSet;
//        try {
//            preparedStatement = createPreparedStatement(query,parameters);
//            resultSet = preparedStatement.executeQuery();
//        } catch (SQLException e) {
//            throw new PreparedStatementException("Something went wrong executing the prepared statement.",e);
//        }
//        return resultSet;
//    }
//
//    public ResultSet executeQueryWithResultAndTransaction(String query, ArrayList<ISQLParameter> parameters){
//        ResultSet resultSet = null;
//        try {
//            databaseConnection.getConnection().setAutoCommit(false);
//            resultSet = executeQueryWithResult(query,parameters);
//            databaseConnection.getConnection().commit();
//            databaseConnection.getConnection().setAutoCommit(true);
//        } catch (SQLException e) {
//            throw new DatabaseConnectionException("Something went wrong connecting to the database.",e);
//        }
//        return resultSet;
//    }
//
//    public void executeQueryWithoutResult(String query, ArrayList<ISQLParameter> parameters){
//        PreparedStatement preparedStatement;
//        try {
//            preparedStatement = createPreparedStatement(query,parameters);
//            preparedStatement.execute();
//        } catch (SQLException e) {
//            throw new PreparedStatementException("Something went wrong executing the prepared statement.",e);
//        }
//    }
//
//    public PreparedStatement createPreparedStatement(String query, ArrayList<ISQLParameter> parameters) throws SQLException {
//        var preparedStatement = databaseConnection.getConnection().prepareStatement(query);
//        prepareParameters(parameters, preparedStatement);
//        return preparedStatement;
//    }
//
//    private void prepareParameters(ArrayList<ISQLParameter> parameters, PreparedStatement preparedStatement) throws SQLException {
//        for (ISQLParameter parameter : parameters) {
//            parameter.putParameter(preparedStatement);
//        }
//    }
//
//    @Autowired
//    public void setDatabaseConnection(DatabaseConnection databaseConnection) {
//        this.databaseConnection = databaseConnection;
//    }
//}
