package com.periz.learnloop.models

import android.content.Context
import android.net.Uri
import com.cloudinary.android.MediaManager
import com.cloudinary.android.callback.ErrorInfo
import com.cloudinary.android.callback.UploadCallback

object CloudinaryManager {

    private var initialized = false

    fun init(context: Context) {
        if (initialized) return

        val config = HashMap<String, String>()
        config["cloud_name"] = "your_cloud_name"
        config["api_key"] = "your_api_key"
        config["api_secret"] = "your_api_secret"

        MediaManager.init(context, config)
        initialized = true
    }

    fun uploadImage(
        context: Context,
        uri: Uri,
        onSuccess: (String) -> Unit,
        onError: () -> Unit
    ) {
        init(context)

        MediaManager.get().upload(uri)
            .callback(object : UploadCallback {
                override fun onStart(requestId: String?) {}

                override fun onProgress(requestId: String?, bytes: Long, totalBytes: Long) {}

                override fun onSuccess(requestId: String?, resultData: Map<*, *>) {
                    val url = resultData["secure_url"].toString()
                    onSuccess(url)
                }

                override fun onError(requestId: String?, error: ErrorInfo?) {
                    onError()
                }

                override fun onReschedule(requestId: String?, error: ErrorInfo?) {}
            })
            .dispatch()
    }
}