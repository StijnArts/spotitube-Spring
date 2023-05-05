package han.stijn.spotitube.datasource.preparedstatement.parameter;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class SQLParameterFactoryTest {

    SQLParameterFactory sut = new SQLParameterFactory();

    @Test
    public void createStringParameter(){
        //arrange
        StringParameter testParameter = new StringParameter(1,"string");
        //act

        //assert
        assertEquals(testParameter.toString(),sut.createParameter(1,"string").toString());
    }

    @Test
    public void createIntegerParameter(){
        //arrange
        IntegerParameter testParameter = new IntegerParameter(1,1);
        //act

        //assert
        assertEquals(testParameter.toString(),sut.createParameter(1,1).toString());
    }
}
