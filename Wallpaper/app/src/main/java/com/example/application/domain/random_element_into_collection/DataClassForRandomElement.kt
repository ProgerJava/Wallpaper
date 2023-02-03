package com.example.application.domain.random_element_into_collection

data class DataClassForRandomElement(
    val results: List<Result>,
    val total: Int,
    val total_pages: Int
)