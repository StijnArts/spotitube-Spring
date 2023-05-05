package han.stijn.spotitube.datasource.preparedstatement.parameter;

public class SQLParameterFactory {
    public static ISQLParameter createParameter(int index, String value){
        return new StringParameter(index, value);
    }

    public static ISQLParameter createParameter(int index, int value){
        return new IntegerParameter(index, value);
    }
}
