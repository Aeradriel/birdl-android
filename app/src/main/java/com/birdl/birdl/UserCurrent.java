package com.birdl.birdl;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Serkan on 12/07/2015.
 */

public class UserCurrent {

    private String id;
    private String password;
    private String username;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String toJSON(){

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", username);
            jsonObject.put("password", password);
            return jsonObject.toString();
        } catch (JSONException e){
            e.printStackTrace();
            return "";
        }
    }
}
