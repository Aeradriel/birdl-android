package com.birdl.birdl;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Serkan on 12/07/2015.
 */
public class LoginResponse {

    public String email;
    public String id;
    public String first_name;
    public String last_name;
    public String gender;
    public String password;
    public String error;
    public String birthdate;
    public UserCurrent user;

    public LoginResponse(){}

    public LoginResponse(String error, UserCurrent user){
        this.error = error;
        this.user = user;
    }

    public String getFirsName(){
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName(){
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getGender(){
        return gender;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthdate(){
        return birthdate;
    }

    public void setBirthdate(String birthdate){
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public UserCurrent getUserCurrent() {
        return user;
    }

    public void setUserCurrent(UserCurrent resp) {
        this.user = user;
    }

    public String toJSON() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("error", error);
            jsonObject.put("UserCurrent", user);
            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }
}