package han.stijn.spotitube.datasource.spring;

import han.stijn.spotitube.datasource.properties.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.datasource.*;

@Configuration
@ComponentScan
public class SpotitubeDataSource {
    @Autowired
    DatabaseProperties databaseProperties;

    @Bean
    JdbcTemplate jdbcTemplate(){
        DriverManagerDataSource driverManagerDataSource= new DriverManagerDataSource();
        driverManagerDataSource.setUrl(databaseProperties.connectionString());
        driverManagerDataSource.setDriverClassName(databaseProperties.driverString());
        return new JdbcTemplate(driverManagerDataSource);
    }

}
