package com.example.intifoodapp.models;

public class UserModel {
    String name;
    String email;
    String profileImg;

    public UserModel(){

    }

    public UserModel(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
