package models;

public class LoginBodyModel {

    //        String authData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";
    String email, password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
