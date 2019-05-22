package com.example.nazi.githubretrofit;

import java.util.List;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by Nazi on 5/22/2019.
 */

public interface GithubAPI {

    @Headers("Accept : application/json")
    @FormUrlEncoded
    @POST("login/oauth/access_token")
    Call<OAuthToken> getAccessToken(
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("code") String code);
    @GET("/user{user}/repos")
    Call<List<GithubRepo>> getRepo(@Path("user") String user);

}
