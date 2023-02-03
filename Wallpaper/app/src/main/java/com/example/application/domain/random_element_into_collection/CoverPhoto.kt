package com.example.application.domain.random_element_into_collection

data class CoverPhoto(
    val alt_description: Any,
    val blur_hash: String,
    val color: String,
    val created_at: String,
    val current_user_collections: List<Any>,
    val description: Any,
    val height: Int,
    val id: String,
    val liked_by_user: Boolean,
    val likes: Int,
    val promoted_at: Any,
    val sponsorship: Any,
    val updated_at: String,
    val urls: Urls,
    val width: Int
)