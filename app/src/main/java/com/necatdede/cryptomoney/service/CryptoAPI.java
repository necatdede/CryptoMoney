package com.necatdede.cryptomoney.service;

import com.necatdede.cryptomoney.model.CryptoModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface CryptoAPI {

    @GET("currencies/ticker?key=05f3474a1b2141489f71f0609616003f")
    Observable<List<CryptoModel>> getData();
   // Call<List<CryptoModel>> getData();


}
