package com.jpark.memowithnodejs;

import com.jpark.memowithnodejs.domain.Data;

import retrofit2.Call;
import retrofit2.http.GET;


public interface LocalhostInterface {
    @GET("bbs")
    Call<Data> getData();

}
