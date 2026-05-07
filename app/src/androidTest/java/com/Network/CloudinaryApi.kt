package com.Network

import com.periz.learnloop.models.CloudinaryUpdateResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface CloudinaryApi {

    @Multipart
    @POST("v1_1/YOUR_CLOUD_NAME/image/upload")
    suspend fun uploadImage(
        @Part file: MultipartBody.Part,
        @Part("upload_preset") uploadPreset: RequestBody
    ): Response<CloudinaryUpdateResponse>
}