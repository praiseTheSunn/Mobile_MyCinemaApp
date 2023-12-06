package com.example.mycinemaapp.models;

import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class UserEntity {
    @PrimaryKey @NonNull
//    private long id;

    private String email;

    private String password;

    private String profileImagePath;

    private String name;

    public UserEntity(String email, String password, String profileImagePath, String name) {
//        this.id = id;
        this.email = email;
        this.password = password;
        this.profileImagePath = profileImagePath;
        this.name = name;
    }

    public UserEntity(String etYourName, String etEmail, String etPassword) {
        this.email = etEmail;
        this.password = etPassword;
        this.profileImagePath = "user_profile_image/default-user.svg";
        this.name = etYourName;
    }

//    public long getId() {
//        return id;
//    }

//    public void setId(long _id) {
//        id = _id;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String _email) {
        email = _email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String _password) {
        password = _password;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String _profileImagePath) {
        profileImagePath = _profileImagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }
}
