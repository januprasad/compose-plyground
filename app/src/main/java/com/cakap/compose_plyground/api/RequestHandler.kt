package com.cakap.compose_plyground.api

import android.util.Log
import androidx.compose.runtime.MutableState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun sendRequest(
    pincode: String,
    postOffice: MutableState<PostOffice>,
) {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.postalpincode.in/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(UserApi::class.java)

    val call: Call<AddressWrapper?>? = api.getAddressByPincode(pincode)

    call!!.enqueue(object : Callback<AddressWrapper?> {
        override fun onResponse(call: Call<AddressWrapper?>, response: Response<AddressWrapper?>) {
            if (response.isSuccessful) {
                Log.d("Main", "success!" + response.body().toString())
                response.let { addressWrapper ->
                    addressWrapper.body()?.let { nonNullAddressWrapper ->
                        postOffice.value = nonNullAddressWrapper[0].postOffice?.get(0)!!
                    }
                }
            }
        }

        override fun onFailure(call: Call<AddressWrapper?>, t: Throwable) {
            Log.e("Main", "Failed mate " + t.message.toString())
        }
    })
}
