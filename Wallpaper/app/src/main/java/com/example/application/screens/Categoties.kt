package com.example.application.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
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
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.example.application.R
import com.example.application.view.MainActivity
import com.example.application.view_model.MainActivityViewModel


////////////////////////////Отрисовка списка доступных коллекций на экране
@Composable
fun showCategories(mainActivityViewModel: MainActivityViewModel, mainActivity: MainActivity) {

    val colorCard = Color (0xFF320A1E)

    val pictureImage1 = remember { mutableStateOf("")}
    val pictureImage2 = remember { mutableStateOf("")}
    val pictureImage3 = remember { mutableStateOf("")}
    val pictureImage4 = remember { mutableStateOf("")}
    val pictureImage5 = remember { mutableStateOf("")}
    val pictureImage6 = remember { mutableStateOf("")}
    val pictureImage7 = remember { mutableStateOf("")}


    mainActivityViewModel.pictureRef1.observe(mainActivity) {
        pictureImage1.value = mainActivityViewModel.pictureRef1.value.toString()
    }
    mainActivityViewModel.pictureRef2.observe(mainActivity) {
        pictureImage2.value = mainActivityViewModel.pictureRef2.value.toString()
    }
    mainActivityViewModel.pictureRef3.observe(mainActivity) {
        pictureImage3.value = mainActivityViewModel.pictureRef3.value.toString()
    }
    mainActivityViewModel.pictureRef4.observe(mainActivity) {
        pictureImage4.value = mainActivityViewModel.pictureRef4.value.toString()
    }
    mainActivityViewModel.pictureRef5.observe(mainActivity) {
        pictureImage5.value = mainActivityViewModel.pictureRef5.value.toString()
    }
    mainActivityViewModel.pictureRef6.observe(mainActivity) {
        pictureImage6.value = mainActivityViewModel.pictureRef6.value.toString()
    }
    mainActivityViewModel.pictureRef7.observe(mainActivity) {
        pictureImage7.value = mainActivityViewModel.pictureRef7.value.toString()
    }

    Image(
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.9f),
        contentScale = ContentScale.Crop,
        painter = painterResource(id = R.drawable.picture_background_splash),
        contentDescription = "pc1"
    )
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Text(
            modifier = Modifier.padding(top = 15.dp, bottom = 25.dp),
            text = "Темы",
            fontSize = 35.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            for (i in 0..6) {
                val pictureImage = remember { mutableStateOf("") }
                when (i) {
                    0 -> pictureImage.value = pictureImage1.value
                    1 -> pictureImage.value = pictureImage2.value
                    2 -> pictureImage.value = pictureImage3.value
                    3 -> pictureImage.value = pictureImage4.value
                    4 -> pictureImage.value = pictureImage5.value
                    5 -> pictureImage.value = pictureImage6.value
                    6 -> pictureImage.value = pictureImage7.value
                }

                Card(
                    backgroundColor = colorCard,
                    elevation = 5.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(top = 5.dp)
                        .clickable {
                            mainActivityViewModel.currentCollection.value =
                                mainActivityViewModel.setCollectionsNames()[i]
                        }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        SubcomposeAsyncImage(
                            model = pictureImage.value,
                            loading = {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .size(70.dp),
                                    color = Color.White,
                                    strokeWidth = 6.dp
                                )
                            },
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(2.dp),
                            contentScale = ContentScale.Crop,
                            contentDescription = "pc1"
                        )
                    }
                    Text(
                        text = mainActivityViewModel.setCollectionsNames()[i],
                        fontSize = 25.sp,
                        color = Color.White,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
        }

    }
}
