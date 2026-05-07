package com.periz.learnloop.models

data class CloudinaryUpdateResponse(
    val asset_id: String? = null,
    val public_id: String? = null,
    val version: Int? = null,
    val version_id: String? = null,
    val signature: String? = null,
    val width: Int? = null,
    val height: Int? = null,
    val format: String? = null,
    val resource_type: String? = null,
    val created_at: String? = null,
    val tags: List<String>? = null,
    val bytes: Int? = null,
    val type: String? = null,
    val etag: String? = null,
    val placeholder: Boolean? = null,
    val url: String? = null,
    val secure_url: String? = null,
    val folder: String? = null,
    val original_filename: String? = null
)