
package org.springapp.api.request.model;


public class AuthRequestModel {
    public String username;
    public String password;
    public boolean keepMeLogin;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isKeepMeLogin() {
        return keepMeLogin;
    }

    public void setKeepMeLogin(boolean keepMeLogin) {
        this.keepMeLogin = keepMeLogin;
    }
}
