package com.periz.learnloop.models

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import com.google.gson.Gson

data class CloudinaryResponse(
    val secure_url: String? = null,
    val public_id: String? = null
)

object Cloudinary {

    private val gson = Gson()

    // Convert JSON string to object
    fun parseResponse(json: String): CloudinaryResponse {
        return try {
            gson.fromJson(json, CloudinaryResponse::class.java)
        } catch (e: Exception) {
            CloudinaryResponse()
        }
    }

    // Convert File to Multipart for upload
    fun createImagePart(
        file: File,
        partName: String = "file"
    ): MultipartBody.Part {

        val requestFile: RequestBody =
            file.asRequestBody("image/*".toMediaTypeOrNull())

        return MultipartBody.Part.createFormData(
            partName,
            file.name,
            requestFile
        )
    }
}