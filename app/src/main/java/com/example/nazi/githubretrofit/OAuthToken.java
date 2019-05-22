package com.example.nazi.githubretrofit;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nazi on 5/22/2019.
 */

public class OAuthToken {
    @SerializedName("access_token")
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }



    public String getTokentype() {
        return tokentype;
    }

    public void setTokentype(String tokentype) {
        this.tokentype = tokentype;
    }

    @SerializedName("token_type")
    private String tokentype;

}
