package com.example.covidfight;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;


public interface YelpInterface {

    @GET("businesses/search")
    Call<YelpSearchResult> searchRestaurants(
            @Header("Authorization") String authHeader,
            @Query("term") String searchTerm,
            @Query("location") String location);

    //Call<List<Repo>> listRepos(@Path("user") String user);




}
