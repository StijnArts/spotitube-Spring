package han.stijn.spotitube.datasource;

import org.springframework.stereotype.*;

import java.security.*;
import java.util.*;

@Component
public class TokenGenerator {
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    public static String generateToken(){
        var randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
}
