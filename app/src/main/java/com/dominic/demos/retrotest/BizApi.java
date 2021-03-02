package com.dominic.demos.retrotest;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BizApi {

    @GET("/usercenter/accountInfo")
    Observable<UserAccount> getAccountInfo(@Query("user_phone") String phone,
                                           @Query("role") int role);
}
