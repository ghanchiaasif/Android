package net.simplifiedlearning.retrofitexample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Belal on 10/2/2017.
 */

public interface Api {

    String BASE_URL = "http://0ab22201.ngrok.io/api/";

    @GET("st")
    Call<List<Hero>> getHeroes();

    @FormUrlEncoded
    @POST("st")
    Call<Message> createUser(
            //@Field("id") int id,
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
    @PUT("st/{id}")
    Call<Message> updateUser(
            @Path("id") int id,
            @Field("name") String name,
            @Field("updated_at") String updated_at,
            @Field("created_at") String created_at
    );
    @DELETE("st/{id}")
    Call<Message> deletestate(@Path("id") int id);
}
