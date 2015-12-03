package com.birdl.activity;

/**
 * Created by Christophe on 14/09/2015.
 */

public class SessionInformation {
    static public String AccessToken;
    static public int id;
    static public String email;
    static public String first_name;
    static public String last_name;

    public SessionInformation(int id, String email, String first_name, String last_name)
    {
        SessionInformation.id = id;
        SessionInformation.email = email;
        SessionInformation.first_name = first_name;
        SessionInformation.last_name = last_name;
    }

    public static void ChangeAccessToken (String accessTokenRaw)
    {
        String[] tab = accessTokenRaw.split(" ");
        String AccessTokenModif = tab[1];
        SessionInformation.AccessToken = AccessTokenModif;
    }
}
