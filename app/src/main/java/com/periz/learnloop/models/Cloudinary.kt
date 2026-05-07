package com.periz.learnloop.models


import com.google.gson.annotations.SerializedName

data class CloudinaryResponse(
    @SerializedName("url")
    val url: String? = null,

    @SerializedName("secure_url")
    val secureUrl: String? = null,

    @SerializedName("public_id")
    val publicId: String? = null,

    @SerializedName("asset_id")
    val assetId: String? = null,

    @SerializedName("format")
    val format: String? = null
)