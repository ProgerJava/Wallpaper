package com.example.application.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.application.domain.nine_elements_into_collections.DataClassPhotosIntoCollection
import com.example.application.domain.random_element_into_collection.DataClassForRandomElement
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel () {

    ////////////////////////////Имя запрашиваемой коллекции
    val currentCollection : MutableLiveData<String> = MutableLiveData <String>("")

    ////////////////////////////Слушатель кнопки "домой"
    val buttonHome : MutableLiveData<Boolean> = MutableLiveData <Boolean>()

    ////////////////////////////Слушатель выбранного фото
    val buttonSelectPhoto : MutableLiveData<String> = MutableLiveData <String>()

    ////////////////////////////Полученные фотографии из выбранной темы
    val listWithCurrentCollectionsPhotos : MutableLiveData<ArrayList<String>> = MutableLiveData <ArrayList<String>>()

    ////////////////////////////Ссылки на рандомные фотографии из каждой коллекции
    val pictureRef1 : MutableLiveData<String> = MutableLiveData <String>("")
    val pictureRef2 : MutableLiveData<String> = MutableLiveData <String>("")
    val pictureRef3 : MutableLiveData<String> = MutableLiveData <String>("")
    val pictureRef4 : MutableLiveData<String> = MutableLiveData <String>("")
    val pictureRef5 : MutableLiveData<String> = MutableLiveData <String>("")
    val pictureRef6 : MutableLiveData<String> = MutableLiveData <String>("")
    val pictureRef7 : MutableLiveData<String> = MutableLiveData <String>("")

    ////////////////////////////Инициализация списка тем
    fun setCollectionsNames (): ArrayList<String> {
        val list = ArrayList <String> ()
        list.add("Церковь")
        list.add("Йога")
        list.add("Рыбы")
        list.add("Пицца")
        list.add("Крылья")
        list.add("Токио")
        list.add("Лего")
        return list
    }
    suspend fun request(apiInterface: Call<DataClassForRandomElement>, i: Int) = coroutineScope{
        launch {
            apiInterface.enqueue(object : Callback<DataClassForRandomElement> {
                override fun onResponse(
                    call: Call<DataClassForRandomElement>,
                    response: Response<DataClassForRandomElement>
                ) {
                    if (response.body() != null) {
                        var random = (0 until response.body()!!.results.size).random()
                        when (i) {
                            0 -> {pictureRef1.value = response.body()!!.results[random].cover_photo.urls.full}
                            1 -> {pictureRef2.value = response.body()!!.results[random].cover_photo.urls.full}
                            2 -> {pictureRef3.value = response.body()!!.results[random].cover_photo.urls.full}
                            3 -> {pictureRef4.value = response.body()!!.results[random].cover_photo.urls.full}
                            4 -> {pictureRef5.value = response.body()!!.results[random].cover_photo.urls.full}
                            5 -> {pictureRef6.value = response.body()!!.results[random].cover_photo.urls.full}
                            6 -> {pictureRef7.value = response.body()!!.results[random].cover_photo.urls.full}
                        }
                    }
                }
                override fun onFailure(call: Call<DataClassForRandomElement>, t: Throwable) {
                    println(t.message.toString())
                }
            })
        }
    }
    suspend fun requestListElements (apiInterface: Call<DataClassPhotosIntoCollection>) = coroutineScope{
        launch {
            apiInterface.enqueue(object : Callback<DataClassPhotosIntoCollection> {
                override fun onResponse(
                    call: Call<DataClassPhotosIntoCollection>,
                    response: Response<DataClassPhotosIntoCollection>
                ) {
                    if (response.body() != null) {
                        val list = ArrayList <String> ()
                        for (i in 0 until response.body()!!.size) {
                            list.add(response.body()!![i].urls.full)
                        }
                        listWithCurrentCollectionsPhotos.value = list
                    }
                }
                override fun onFailure(call: Call<DataClassPhotosIntoCollection>, t: Throwable) {
                    println(t.message.toString())
                }
            })
        }
    }

}