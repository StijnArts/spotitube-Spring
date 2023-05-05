package han.stijn.spotitube;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.web.servlet.support.*;
import org.springframework.context.*;
import org.springframework.transaction.annotation.*;

@SpringBootApplication
@EnableTransactionManagement
public class SpotitubeBootApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpotitubeBootApplication.class, args);
    }
}
