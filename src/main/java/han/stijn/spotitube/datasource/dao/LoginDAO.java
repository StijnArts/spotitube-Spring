package han.stijn.spotitube.datasource.dao;

import han.stijn.spotitube.datasource.*;
import han.stijn.spotitube.datasource.preparedstatement.*;
import han.stijn.spotitube.datasource.preparedstatement.parameter.*;
import han.stijn.spotitube.dto.*;
import han.stijn.spotitube.exception.*;
import org.apache.commons.codec.digest.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.sql.*;
import java.util.*;

@Component
public class LoginDAO extends DatabaseLogger {

    //Password for all test accounts is password123!
    private SpringPreparedStatementHelper springPreparedStatementHelper;
    private ResultSetReader resultSetReader;
    private TokenGenerator tokenGenerator;

    public boolean checkLogin(LoginDTO loginDTO){
        //var query = "SELECT [PASSWORD] AS [password] FROM [USER] WHERE [USERNAME] = ?";
        var query = "SELECT * FROM [USER]";
        var parameters = new ArrayList<ISQLParameter>();
        //parameters.add(SQLParameterFactory.createParameter(1,loginDTO.getUser()));
        List<LoginDTO> login = springPreparedStatementHelper.executeQueryWithResultAndTransaction(query,parameters,
            (rs,rowNum)-> {return new LoginDTO("",rs.getString("password"));});
        if (DigestUtils.sha256Hex(loginDTO.getPassword()).equals(login.get(0).getPassword())) {
            return true;
        }
        return false;
    }

    public UserDTO getUser(LoginDTO loginDTO) {
        putToken(loginDTO.getUser());
        var query = "SELECT FIRSTNAMELASTNAME AS [fullName], [TOKEN] AS [token] FROM [USER] WHERE [USERNAME] = ?";
        var parameters = new ArrayList<ISQLParameter>();
        parameters.add(SQLParameterFactory.createParameter(1,loginDTO.getUser()));
        return (UserDTO) springPreparedStatementHelper.executeQueryWithResultAndTransaction(query,parameters, (rs, rowNum)->{
            return new UserDTO(resultSetReader.readString(rs,"fullName"), resultSetReader.readString(rs,"token"));
        }).get(0);
    }

    public void putToken(String username){
        var query = "UPDATE [USER] SET [TOKEN]=? WHERE [USERNAME] = ?";
        var parameters = new ArrayList<ISQLParameter>();
        parameters.add(SQLParameterFactory.createParameter(1,tokenGenerator.generateToken()));
        parameters.add(SQLParameterFactory.createParameter(2,username));
        springPreparedStatementHelper.executeQueryWithoutResultWithTransaction(query, parameters);
    }

    public boolean checkToken(String token){
            var query = "SELECT TOKEN FROM [USER] WHERE TOKEN = ?";
            var parameters = new ArrayList<ISQLParameter>();
            parameters.add(SQLParameterFactory.createParameter(1,token));
            return (boolean) springPreparedStatementHelper.executeQueryWithResultAndTransaction(query,parameters, (rs, rowNum)->{
                return true;
            }).get(0);
    }

    @Autowired
    public void setTokenGenerator(TokenGenerator tokenGenerator) {
        this.tokenGenerator = tokenGenerator;
    }

    @Autowired
    public void setResultSetReader(ResultSetReader resultSetReader) {
        this.resultSetReader = resultSetReader;
    }

    @Autowired
    public void setPreparedStatementHelper(SpringPreparedStatementHelper jakartaPreparedStatementHelper) {
        this.springPreparedStatementHelper = jakartaPreparedStatementHelper;
    }
}
