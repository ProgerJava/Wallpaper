package com.example.application.domain

import com.example.application.domain.nine_elements_into_collections.DataClassPhotosIntoCollection
import com.example.application.domain.random_element_into_collection.DataClassForRandomElement
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface QuotesApi {

    ////////////////////////////Функции для запроса одной фотографии из определенной коллекции
    @GET("search/collections?query=church&client_id=AL6_fe8NKpKRLqoaUG1ig4MoRTM3Fpwjpf9qHcTtGMY")
    fun getRandomElement1Collections() : Call<DataClassForRandomElement>
    @GET("search/collections?query=yoga&client_id=AL6_fe8NKpKRLqoaUG1ig4MoRTM3Fpwjpf9qHcTtGMY")
    fun getRandomElement2Collections() : Call<DataClassForRandomElement>
    @GET("search/collections?query=pisces&client_id=AL6_fe8NKpKRLqoaUG1ig4MoRTM3Fpwjpf9qHcTtGMY")
    fun getRandomElement3Collections() : Call<DataClassForRandomElement>
    @GET("search/collections?query=pizza&client_id=AL6_fe8NKpKRLqoaUG1ig4MoRTM3Fpwjpf9qHcTtGMY")
    fun getRandomElement4Collections() : Call<DataClassForRandomElement>
    @GET("search/collections?query=wings&client_id=AL6_fe8NKpKRLqoaUG1ig4MoRTM3Fpwjpf9qHcTtGMY")
    fun getRandomElement5Collections() : Call<DataClassForRandomElement>
    @GET("search/collections?query=tokyo&client_id=AL6_fe8NKpKRLqoaUG1ig4MoRTM3Fpwjpf9qHcTtGMY")
    fun getRandomElement6Collections() : Call<DataClassForRandomElement>
    @GET("search/collections?query=lego&client_id=AL6_fe8NKpKRLqoaUG1ig4MoRTM3Fpwjpf9qHcTtGMY")
    fun getRandomElement7Collections() : Call<DataClassForRandomElement>

    ////////////////////////////Функции для запроса списка фотографий из определенной коллекции
    @GET("collections/1991725/photos?client_id=AL6_fe8NKpKRLqoaUG1ig4MoRTM3Fpwjpf9qHcTtGMY")
    fun getElementsIntoCollectionChurch() : Call<DataClassPhotosIntoCollection>
    @GET("collections/99144643/photos?client_id=AL6_fe8NKpKRLqoaUG1ig4MoRTM3Fpwjpf9qHcTtGMY")
    fun getElementsIntoCollectionYoga() : Call<DataClassPhotosIntoCollection>
    @GET("collections/256524/photos?client_id=AL6_fe8NKpKRLqoaUG1ig4MoRTM3Fpwjpf9qHcTtGMY")
    fun getElementsIntoCollectionPisces() : Call<DataClassPhotosIntoCollection>
    @GET("collections/ZBVwwGlWlh0/photos?client_id=AL6_fe8NKpKRLqoaUG1ig4MoRTM3Fpwjpf9qHcTtGMY")
    fun getElementsIntoCollectionPizza() : Call<DataClassPhotosIntoCollection>
    @GET("collections/KizanWcExgU/photos?client_id=AL6_fe8NKpKRLqoaUG1ig4MoRTM3Fpwjpf9qHcTtGMY")
    fun getElementsIntoCollectionWings() : Call<DataClassPhotosIntoCollection>
    @GET("collections/9389477/photos?client_id=AL6_fe8NKpKRLqoaUG1ig4MoRTM3Fpwjpf9qHcTtGMY")
    fun getElementsIntoCollectionTokyo() : Call<DataClassPhotosIntoCollection>
    @GET("collections/3678981/photos?client_id=AL6_fe8NKpKRLqoaUG1ig4MoRTM3Fpwjpf9qHcTtGMY")
    fun getElementsIntoCollectionLego() : Call<DataClassPhotosIntoCollection>

    ////////////////////////////Инициализация объекта retrofit
    object RetrofitHelper {

        val baseUrl = "https://api.unsplash.com/"

        fun getInstance(): QuotesApi {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build()
            return retrofit.create(QuotesApi::class.java)
        }
    }
}