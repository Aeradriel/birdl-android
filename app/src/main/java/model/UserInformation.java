package model;

import activity.MainActivity;

/**
 * Created by Christophe on 15/09/2015.
 */
public class UserInformation{

    int id;
    String email;
    String first_name;
    String last_name;
    String birthdate;
    int gender;
    String access_token;
    //String country;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAccess_token() {return access_token;}

    public void setAccess_token(String access_token){this.access_token = access_token;}
}
