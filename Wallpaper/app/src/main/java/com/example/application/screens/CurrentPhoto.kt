package com.example.application.screens

import android.annotation.SuppressLint
import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.application.view.MainActivity
import com.example.application.view_model.MainActivityViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.O_MR1)
@SuppressLint("ServiceCast", "ResourceType")
@Composable
fun showCurrentPhoto(
    mainActivity: MainActivity,
    mainActivityViewModel: MainActivityViewModel,
    currentPhoto: String
) {
    val flag = remember { mutableStateOf(false)}
    val colorCard = Color (0xFF320A1E)
    var visibility = remember { mutableStateOf(0.0f)}

    AsyncImage(
        model = currentPhoto,
        modifier = Modifier
            .fillMaxSize(),
        contentScale = ContentScale.None,
        contentDescription = "pc1"
    )
    Column (modifier = Modifier.fillMaxSize()) {
        FloatingActionButton(
            modifier = Modifier
                .size(50.dp)
                .padding(top = 10.dp, start = 5.dp),
            shape = CircleShape,
            backgroundColor = colorCard,
            onClick = {
                mainActivityViewModel.buttonHome.value = true
            }) {
            Icon(Icons.Filled.Home, contentDescription = "Назад в меню", tint = Color.White)
        }
        Row(horizontalArrangement = Arrangement.End) {
            FloatingActionButton(
                modifier = Modifier
                    .size(50.dp)
                    .padding(top = 10.dp, start = 5.dp),
                shape = CircleShape,
                backgroundColor = colorCard,
                onClick = {
                    val manager = WallpaperManager.getInstance(mainActivity.applicationContext)
                    val thread = Thread (Runnable {
                        run () {
                            visibility.value = 1.0f
                            var result = Picasso.with(mainActivity.applicationContext).load(currentPhoto).get()
                            manager.setBitmap(result)
                            visibility.value = 0.0f
                        }
                    })
                    thread.start()


                }) {
                Icon(Icons.Filled.Done, contentDescription = "Выбрать", tint = Color.White)
            }
        }
    }
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .alpha(visibility.value)
                .size(70.dp)
                .padding(top = 50.dp),
            color = Color.White,
            strokeWidth = 6.dp
        )
    }
}
