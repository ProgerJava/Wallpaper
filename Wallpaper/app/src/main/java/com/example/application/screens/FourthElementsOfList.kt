package com.example.application.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.example.application.view_model.MainActivityViewModel

////////////////////////////Отрисовка четвертой пары элементов, полученной из запроса

@Composable
fun fourthElements(
    mainActivityViewModel: MainActivityViewModel,
    listWithPicture: MutableState<ArrayList<String>>
) {

    val colorCard = Color (0xFF320A1E)

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(top = 20.dp)
    ) {
        if (listWithPicture.value.size != 0) {
            for (i in 6 until 8) {
                Card(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(200.dp)
                        .weight(1f)
                ) {
                    SubcomposeAsyncImage (
                        model = listWithPicture.value[i],
                        loading = { CircularProgressIndicator(
                            modifier = Modifier
                                .size(30.dp)
                                .padding(20.dp),
                            color = colorCard,
                            strokeWidth = 6.dp
                        )},
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(2.dp)
                            .clickable {
                                mainActivityViewModel.buttonSelectPhoto.value =
                                    listWithPicture.value[i]
                            },
                        contentScale = ContentScale.Crop,
                        contentDescription = "pc1"
                    )
                }
            }
        }
    }
}