package org.springapp.api.request.model;


public class OrderListRequestModel {
    public UserRequestModel getUser() {
        return user;
    }

    public void setUser(UserRequestModel user) {
        this.user = user;
    }

    private UserRequestModel user;
}
