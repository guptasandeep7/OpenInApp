package com.sandeepgupta.openinapp.presentation.ui.screens.links.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandeepgupta.openinapp.presentation.ui.theme.Background
import com.sandeepgupta.openinapp.presentation.ui.theme.figTreeFontFamily
import com.sandeepgupta.openinapp.presentation.ui.theme.greyTextStyle

@Composable
fun DetailCard(imageId: Int, title: String, value: String) {
    Column(
        modifier = Modifier
            .size(120.dp)
            .background(Background, shape = RoundedCornerShape(8.dp))
            .padding(12.dp),
    ) {
        Image(
            painterResource(id = imageId),
            contentDescription = null,
            Modifier.size(32.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = value,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                fontFamily = figTreeFontFamily,
            ),
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = title,
            style = greyTextStyle,
            overflow = TextOverflow.Ellipsis
        )

    }
}