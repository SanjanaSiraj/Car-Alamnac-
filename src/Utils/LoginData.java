package Utils;

import java.io.Serializable;

public class LoginData implements Serializable {
    private String username;
    private String password;
    private boolean status;

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean getStatus(){
        return status;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
