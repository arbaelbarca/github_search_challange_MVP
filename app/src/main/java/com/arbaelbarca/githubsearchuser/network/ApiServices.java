package com.arbaelbarca.githubsearchuser.network;

import com.arbaelbarca.githubsearchuser.model.ModelListUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {

    @GET("search/users")
    Call<ModelListUser> getListUser(
            @Query("q") String textSearch,
            @Query("sort") String sort,
            @Query("orderpage") String orderPage,
            @Query("per_page") String perPage

    );
}
