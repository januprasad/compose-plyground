package com.cakap.compose_plyground.api


import com.google.gson.annotations.SerializedName

data class PostOffice(
    @SerializedName("Block")
    val block: String? = null,
    @SerializedName("BranchType")
    val branchType: String? = null,
    @SerializedName("Circle")
    val circle: String? = null,
    @SerializedName("Country")
    val country: String? = null,
    @SerializedName("DeliveryStatus")
    val deliveryStatus: String? = null,
    @SerializedName("Description")
    val description: Any? = null,
    @SerializedName("District")
    val district: String? = null,
    @SerializedName("Division")
    val division: String? = null,
    @SerializedName("Name")
    val name: String? = null,
    @SerializedName("Pincode")
    val pincode: String? = null,
    @SerializedName("Region")
    val region: String? = null,
    @SerializedName("State")
    val state: String? = null
)