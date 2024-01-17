package com.cakap.compose_plyground.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

public interface UserApi {
    @Headers(
        "Accept: application/json",
    )
    @GET("pincode/{pincode}")
    abstract fun getAddressByPincode(@Path("pincode") pincode: String): Call<AddressWrapper?>?
}
