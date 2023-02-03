package com.example.application.screens


import com.example.application.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


////////////////////////////Стартовый экран

@Composable
fun setBackground() {

    Image (
        painter = painterResource(id = R.drawable.picture_background_splash) ,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
        contentDescription = "pc1"
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(top = 40.dp),
            text = "Wallpaper",
            fontSize = 35.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        CircularProgressIndicator(
            modifier = Modifier
                .size(70.dp)
                .padding(top = 50.dp),
            color = Color.White,
            strokeWidth = 6.dp
        )
    }
}