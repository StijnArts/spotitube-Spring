package han.stijn.spotitube.datasource.preparedstatement.parameter;

import java.sql.*;

public class StringParameter  implements ISQLParameter{

    private int index;
    private String value;

    public StringParameter(int index, String value){
        this.index=index;
        this.value= value;
    }
    @Override
    public void putParameter(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(index, value);
    }

    @Override
    public String toString() {
        return "Index: "+index+". Value: "+value;
    }
}
