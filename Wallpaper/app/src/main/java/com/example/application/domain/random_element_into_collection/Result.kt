package com.example.application.domain.random_element_into_collection

data class Result(
    val cover_photo: CoverPhoto,
    val curated: Boolean,
    val description: Any,
    val featured: Boolean,
    val id: String,
    val last_collected_at: String,
    val `private`: Boolean,
    val published_at: String,
    val share_key: String,
    val title: String,
    val total_photos: Int,
    val updated_at: String,
)