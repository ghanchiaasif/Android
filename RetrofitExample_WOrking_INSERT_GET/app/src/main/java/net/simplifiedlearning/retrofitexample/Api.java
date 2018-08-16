package net.simplifiedlearning.retrofitexample;

import android.content.Intent;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Belal on 10/2/2017.
 */

public interface Api {

    String BASE_URL = "https://91c15853.ngrok.io/api/";

    @GET("st")
    Call<List<Hero>> getHeroes();

    @FormUrlEncoded
    @POST("st")
    Call<Message> createUser(
            @Field("id") String id,
            @Field("name") String name,
            @Field("updated_at") String updated_at,
            @Field("created_at") String created_a);

    @FormUrlEncoded
    @POST("st")
    Call<Message> userLogin(
            @Field("id") String id,
            @Field("name") String name
    );

    @FormUrlEncoded
    @POST("update/{id}")
    Call<Message> updateUser(
            @Path("id") String  id,
            @Field("name") String name,
            @Field("updated_at") String updated_at,
            @Field("created_at") String created_at
    );
}
