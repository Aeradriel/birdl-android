package configBirdl;

/**
 * Created by Christophe on 15/09/2015.
 */
public class UserInformationStatic {

    public static int id;
    public static String first_name;
    public static String last_name;
    public static String birthdate;
    public static int gender;
    public static String email;
    public static String password;

    public static String getAccess_token() {
        return access_token;
    }

    public static void setAccess_token(String access_token) {
        UserInformationStatic.access_token = access_token;
    }

    public static String access_token;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        UserInformationStatic.id = id;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        UserInformationStatic.email = email;
    }

    public static String getLast_name() {
        return last_name;
    }

    public static void setLast_name(String last_name) {
        UserInformationStatic.last_name = last_name;
    }

    public static String getBirthdate() {
        return birthdate;
    }

    public static void setBirthdate(String birthdate) {
        UserInformationStatic.birthdate = birthdate;
    }

    public static int getGender() {
        return gender;
    }

    public static void setGender(int gender) {
        UserInformationStatic.gender = gender;
    }

    public static String getFirst_name() {
        return first_name;

    }

    public static void setFirst_name(String first_name) {
        UserInformationStatic.first_name = first_name;
    }


    public UserInformationStatic(int id, String email, String first_name, String last_name, String birthdate, int gender, String access, String pass)
    {
        UserInformationStatic.id = id;
        UserInformationStatic.email = email;
        UserInformationStatic.first_name = first_name;
        UserInformationStatic.last_name = last_name;
        UserInformationStatic.birthdate = birthdate;
        UserInformationStatic.gender = gender;
        UserInformationStatic.access_token = access;
        UserInformationStatic.password = pass;
    }
}
