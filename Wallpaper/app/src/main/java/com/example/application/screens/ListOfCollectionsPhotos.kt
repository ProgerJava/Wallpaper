package com.example.application.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.R
import com.example.application.domain.QuotesApi
import com.example.application.view.MainActivity
import com.example.application.view_model.MainActivityViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

////////////////////////////Отрисовка элементов, полученных из запроса определенной коллеции

@SuppressLint("CoroutineCreationDuringComposition", "MutableCollectionMutableState")
@Composable
fun getCollectionsPhotos(
    mainActivity: MainActivity,
    mainActivityViewModel: MainActivityViewModel,
    collectionsName: String
) {
    val colorCard = Color (0xFF320A1E)

    CoroutineScope(Dispatchers.Main).launch {
        val apiInterface = QuotesApi.RetrofitHelper.getInstance()
        when (collectionsName) {
            "Церковь" -> {mainActivityViewModel.requestListElements(apiInterface.getElementsIntoCollectionChurch())}
            "Йога" -> {mainActivityViewModel.requestListElements(apiInterface.getElementsIntoCollectionYoga())}
            "Рыбы" -> {mainActivityViewModel.requestListElements(apiInterface.getElementsIntoCollectionPisces())}
            "Пицца" -> {mainActivityViewModel.requestListElements(apiInterface.getElementsIntoCollectionPizza())}
            "Крылья" -> {mainActivityViewModel.requestListElements(apiInterface.getElementsIntoCollectionWings())}
            "Токио" -> {mainActivityViewModel.requestListElements(apiInterface.getElementsIntoCollectionTokyo())}
            "Лего" -> {mainActivityViewModel.requestListElements(apiInterface.getElementsIntoCollectionLego())}
        }
    }
    val listWithPicture = remember { mutableStateOf(ArrayList<String>())}
    mainActivityViewModel.listWithCurrentCollectionsPhotos.observe(mainActivity) {
        listWithPicture.value = it
    }
    Image(
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.9f),
        contentScale = ContentScale.Crop,
        painter = painterResource(id = R.drawable.picture_background_splash),
        contentDescription = "pc1"
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                FloatingActionButton(
                    modifier = Modifier
                        .size(50.dp)
                        .padding(top = 5.dp),
                    shape = CircleShape,
                    backgroundColor = colorCard,
                    onClick = {
                        mainActivityViewModel.buttonHome.value = true
                    }) {
                    Icon(Icons.Filled.Home, contentDescription = "Назад в меню", tint = Color.White)
                }
                Text(
                    modifier = Modifier.padding(start = 15.dp, top = 5.dp),
                    text = collectionsName,
                    fontSize = 35.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        if (listWithPicture.value.size >= 2) {
            firstElements(mainActivityViewModel, listWithPicture)
        }
        if (listWithPicture.value.size >= 4) {
            secondElements(mainActivityViewModel, listWithPicture)
        }
        if (listWithPicture.value.size >= 6) {
            thirdElements(mainActivityViewModel, listWithPicture)
        }
        if (listWithPicture.value.size >= 8) {
            fourthElements(mainActivityViewModel, listWithPicture)
        }
        if (listWithPicture.value.size >= 10) {
            fifthElements(mainActivityViewModel, listWithPicture)
        }
    }
}

