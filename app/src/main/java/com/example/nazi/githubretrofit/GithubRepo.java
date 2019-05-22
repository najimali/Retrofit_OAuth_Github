package com.example.nazi.githubretrofit;

/**
 * Created by Nazi on 5/22/2019.
 */

public class GithubRepo {
    String name;
    String owner;
    String url;

    @Override
    public String toString() {
        return(name + " " +  url);
    }
}
