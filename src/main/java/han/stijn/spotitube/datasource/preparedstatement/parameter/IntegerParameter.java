package han.stijn.spotitube.datasource.preparedstatement.parameter;

import java.sql.*;

public class IntegerParameter implements ISQLParameter{

    private int index;
    private int value;
    public IntegerParameter(int index, int value){
        this.index = index;
        this.value = value;
    }

    @Override
    public void putParameter(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(index, value);
    }

    @Override
    public String toString() {
        return "Index: "+index+". Value: "+value;
    }
}
