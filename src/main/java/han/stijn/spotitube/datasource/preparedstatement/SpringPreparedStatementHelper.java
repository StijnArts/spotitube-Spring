package han.stijn.spotitube.datasource.preparedstatement;

import han.stijn.spotitube.datasource.preparedstatement.parameter.*;
import han.stijn.spotitube.datasource.properties.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.datasource.*;
import org.springframework.lang.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.*;
import org.springframework.transaction.annotation.*;
import org.springframework.transaction.support.*;

import java.sql.*;
import java.util.*;

@Repository
public class SpringPreparedStatementHelper implements PreparedStatementHelper{
    JdbcTemplate jdbcTemplate;
    @Override
    @Transactional
    public void executeQueryWithoutResultWithTransaction(String query, ArrayList<ISQLParameter> parameters) {
        executePreparedStatement(query, parameters);
    }

    @Override
    public List executeQueryWithResult(String query, ArrayList<ISQLParameter> parameters, RowMapper rowMapper) {
        return executePreparedStatement(query, parameters, rowMapper);
    }

    @Override
    @Transactional
    public List executeQueryWithResultAndTransaction(String query, ArrayList<ISQLParameter> parameters, RowMapper rowMapper) {
        return executePreparedStatement(query, parameters, rowMapper);
    }

    @Override
    public void executeQueryWithoutResult(String query, ArrayList<ISQLParameter> parameters) {
        executePreparedStatement(query, parameters);
    }

    public List executePreparedStatement(String query, ArrayList<ISQLParameter> parameters, RowMapper rowMapper){
        return jdbcTemplate.query(query, ps -> {
            for (ISQLParameter parameter: parameters) {
                parameter.putParameter(ps);
            }
        }, rowMapper);
    }

    public void executePreparedStatement(String query, ArrayList<ISQLParameter> parameters){
        jdbcTemplate.update(query, ps -> {
            for (ISQLParameter parameter: parameters) {
                parameter.putParameter(ps);
            }
        });
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
