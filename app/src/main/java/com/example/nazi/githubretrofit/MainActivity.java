package com.example.nazi.githubretrofit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public final String Log_Tag = getClass().getSimpleName();
    private Button LoginButton;
    //registor your app on github ,get client id and client secreat .
    private String clientID="Your client id ";
    private String clientSecreat = "Your Secreat";
    private String redirctUrl ="Learningandroidio://callback";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoginButton = findViewById(R.id.login_button);
        // we will redirect to webpage and login their(if already login then dont need to login again),
        // then redirect to our app then catch  that code and use that code to access token.
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/login/oauth/authorize"
                +"\"?client_id="+clientID
                +"&scope=repo&redirect_url="+redirctUrl));
        startActivity(intent);

    }

     void login() {

        Uri uri = getIntent().getData();
        if(uri!=null&&uri.toString().startsWith(redirctUrl)){
            String code =uri.getQueryParameter("code");
            Gson gson = new GsonBuilder().serializeNulls().create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://github.com/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        GithubAPI githubAPI = retrofit.create(GithubAPI.class);
            Call<OAuthToken> oAuthTokenCall = githubAPI.getAccessToken(clientID,clientSecreat,code);
            oAuthTokenCall.enqueue(new Callback<OAuthToken>() {
                @Override
                public void onResponse(Call<OAuthToken> call, Response<OAuthToken> response) {
                    if(response.isSuccessful())
                    {
                        Toast.makeText(getApplicationContext(),"Succesfully authenticated",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Log.d(Log_Tag,"Code:"+ response.code());

                    }


                }

                @Override
                public void onFailure(Call<OAuthToken> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Something went wrong in the response  ",Toast.LENGTH_SHORT).show();

                }
            });

        }
    }

    @Override
    public void onClick(View v) {
         switch (v.getId())
         {

             case R.id.login_button:
                 login();
         }
    }
}
