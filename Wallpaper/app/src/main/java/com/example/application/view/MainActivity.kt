package com.example.application.view

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import com.example.application.domain.QuotesApi
import com.example.application.extentions.checkConnection
import com.example.application.extentions.onBackPressed
import com.example.application.screens.getCollectionsPhotos
import com.example.application.screens.setBackground
import com.example.application.screens.showCategories
import com.example.application.screens.showCurrentPhoto
import com.example.application.view_model.MainActivityViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*

class MainActivity : ComponentActivity() {

    ////////////////////////////VM
    private lateinit var mainActivityViewModel : MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityViewModel = ViewModelProvider (this).get(MainActivityViewModel::class.java)

        ////////////////////////////Запуск стартового экрана
        setContent {
            setBackground()
        }
        ////////////////////////////Инициализация листа коллекциями
        CoroutineScope(Dispatchers.Main).launch {
            mainActivityViewModel.setCollectionsNames()
        }
        ////////////////////////////Запуск экрана с фотографиями необходимой темы
        mainActivityViewModel.currentCollection.observe(this) {
            if (it != "") {
                setContent { getCollectionsPhotos(this@MainActivity, mainActivityViewModel, it) }
            }
        }
        ////////////////////////////Слушатель кнопки "Домой", возвращающий экран "Список коллекций"
        mainActivityViewModel.buttonHome.observe(this) {
            if (it) {
                setContent {
                    showCategories(mainActivityViewModel = mainActivityViewModel, mainActivity = this@MainActivity)
                }
            }
        }
        ////////////////////////////Слушатель выбранного фото
        mainActivityViewModel.buttonSelectPhoto.observe(this) {
            if (it != "") {
                setContent { showCurrentPhoto(this@MainActivity, mainActivityViewModel, it) }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        object : CountDownTimer(3000, 3000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                if (checkConnection()) {
                    setContent {
                        showCategories(mainActivityViewModel, this@MainActivity)
                        run()
                    }
                }
                else {
                    Toast.makeText(this@MainActivity, "Проверьте подключение к сети", Toast.LENGTH_LONG).show()
                    start()
                }
            }
        }.start()
    }

    override fun onBackPressed() {
        onBackPressed(this)
    }
    ////////////////////////////Создание ассинхронных запросов к серверу, вытаскиваю по одному рандомному фото каждой коллекции
    private fun run() {
        for (i in 0..6) {
            val instanceRetrofit = QuotesApi.RetrofitHelper.getInstance()
            CoroutineScope(Dispatchers.Main).launch {
                when (i) {
                    0 -> { mainActivityViewModel.request(instanceRetrofit.getRandomElement1Collections(), 0) }
                    1 -> { mainActivityViewModel.request(instanceRetrofit.getRandomElement2Collections(), 1) }
                    2 -> { mainActivityViewModel.request(instanceRetrofit.getRandomElement3Collections(), 2) }
                    3 -> { mainActivityViewModel.request(instanceRetrofit.getRandomElement4Collections(), 3) }
                    4 -> { mainActivityViewModel.request(instanceRetrofit.getRandomElement5Collections(), 4) }
                    5 -> { mainActivityViewModel.request(instanceRetrofit.getRandomElement6Collections(), 5) }
                    6 -> { mainActivityViewModel.request(instanceRetrofit.getRandomElement7Collections(), 6) }
                }
            }
        }
    }
}