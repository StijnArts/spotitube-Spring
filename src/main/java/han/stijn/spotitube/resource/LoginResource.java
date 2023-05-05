package han.stijn.spotitube.resource;

import han.stijn.spotitube.datasource.dao.*;
import han.stijn.spotitube.dto.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginResource {

    private LoginDAO loginDAO;

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<UserDTO> login(@RequestBody LoginDTO loginDTO){
        if(loginDAO.checkLogin(loginDTO)){
            return ResponseEntity.ok(loginDAO.getUser(loginDTO));
        }
        return ResponseEntity.status(403).build();
    }

    @Autowired
    public void setLoginDAO(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }
}
