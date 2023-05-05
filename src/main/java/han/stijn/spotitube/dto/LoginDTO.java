package han.stijn.spotitube.dto;

public class LoginDTO {

    public LoginDTO(){

    }

    public LoginDTO(String user, String password){
        this.user = user;
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    private String user;
    private String password;

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
