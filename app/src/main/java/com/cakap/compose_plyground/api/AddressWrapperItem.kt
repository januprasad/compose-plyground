package com.cakap.compose_plyground.api


import com.google.gson.annotations.SerializedName

data class AddressWrapperItem(
    @SerializedName("Message")
    val message: String? = null,
    @SerializedName("PostOffice")
    val postOffice: List<PostOffice?>? = null,
    @SerializedName("Status")
    val status: String? = null
)