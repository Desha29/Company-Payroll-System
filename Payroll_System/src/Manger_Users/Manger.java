package Manger_Users;

public class Manger {
    private String username;
    private String password;


    public Manger() {
        this.username = "manger";
        this.password = "123";
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password=password;
    }
}
