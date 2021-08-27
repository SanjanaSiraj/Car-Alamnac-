package Server;

import Utils.LoginData;

import java.util.HashMap;

public class UserList {
    HashMap<String,String> userMap;
    UserList(){
        userMap = new HashMap<>();
        userMap.put("viewer","");
        userMap.put("a","b");
        userMap.put("siraj","1805041");
    }
    public LoginData checkLogin(LoginData ld) {
        LoginData loginData = ld;
        loginData.setStatus(ld.getPassword().equals(userMap.get(ld.getUsername())));
        return loginData;
    }

}
