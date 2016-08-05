package com.example.exam01;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 최예찬 on 2016-08-05.
 */
public interface UserService {
    @FormUrlEncoded
    @POST("/login")
    Call<ResultData> login(@Field("id") String id, @Field("pw") String pw);

    @FormUrlEncoded
    @POST("/register")
    Call<ResultData> register(@Field("id") String id, @Field("pw") String pw, @Field("name") String name);
}
