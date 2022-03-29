package org.gluu.idp.api;

public class DummyUserInfo {
    
    private String user;
    private String token;
    private String request_id;

    public String getUser() {

        return this.user;
    }

    public void setUser(String user) {

        this.user = user;
    }

    public String getToken() {

        return this.token;
    }

    public void setToken(String token) {

        this.token = token;
    }

    public void setRequest_id(String request_id){
        this.request_id = request_id;
    }

    public String getRequest_id() {

        return this.request_id;
    }
}
