package com.birdl.birdl;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Serkan on 12/07/2015.
 */

public class Register {

    private String id;
    private String password;
    private String email;
    private String last_name;
    private String first_name;
    private String gender;
    private String birthdate;


    Register(String email, String password, String birthdate, String last_name, String first_name, String gender){
        this.birthdate = birthdate;
        this.last_name = last_name;
        this.first_name = first_name;
        this.gender = gender;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getPassword(){
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

    public String getLast_name(){
        return last_name;
    }

    public String getFirst_name(){
        return first_name;
    }

    public String getGender(){
        return gender;
    }

    public String getBirthdate(){
        return birthdate;
    }

    public void setLast_name(String last_name){
        this.last_name = last_name;
    }

    public void setFirst_name(String first_name){
        this.first_name = first_name;
    }

    public void setGender(String gender){
        this.gender = "1";
    }

    public void setBirthdate(String birthdate){
        this.birthdate = birthdate;
    }

    public String toJSON(){

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", email);
            jsonObject.put("password", password);
            jsonObject.put("last_name",last_name);
            jsonObject.put("first_name", first_name);
            jsonObject.put("gender", gender);
            jsonObject.put("birthdate", birthdate);
            return jsonObject.toString();
        } catch (JSONException e){
            e.printStackTrace();
            return "";
        }
    }
}
