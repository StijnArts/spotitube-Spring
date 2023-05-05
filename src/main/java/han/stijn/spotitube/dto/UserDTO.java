package han.stijn.spotitube.dto;

public class UserDTO {
    private String token;

    public String getToken() {
        return token;
    }

    public String getUser() {
        return user;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUser(String user) {
        this.user = user;
    }

    private String user;

    @Override
    public String toString() {
        return "UserDTO{" +
                "token='" + token + '\'' +
                ", user='" + user + '\'' +
                '}';
    }

    public UserDTO(){

    }
    public UserDTO(String fullUsername, String token){
        this.token = token;
        this.user = fullUsername;
    }

}
