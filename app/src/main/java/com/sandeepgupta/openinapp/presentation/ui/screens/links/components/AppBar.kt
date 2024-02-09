package com.sandeepgupta.openinapp.presentation.ui.screens.links.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandeepgupta.openinapp.R
import com.sandeepgupta.openinapp.presentation.ui.theme.Primary
import com.sandeepgupta.openinapp.presentation.ui.theme.figTreeFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(scrollBehavior: TopAppBarScrollBehavior) {
    androidx.compose.material3.TopAppBar(title = {
        Text(
            text = "Dashboard", style = TextStyle(
                fontFamily = figTreeFontFamily,
                fontWeight = FontWeight(600),
                fontSize = 24.sp,
                color = White,
            )
        )
    }, actions = {
        IconButton(
            onClick = { /*TODO*/ },
            Modifier
                .size(40.dp)
                .background(Color.White.copy(alpha = 0.12f), shape = RoundedCornerShape(12.dp))
        ) {
            Icon(
                painterResource(id = R.drawable.ic_setting),
                contentDescription = "settings icon",
                tint = White
            )

        }
        Spacer(modifier = Modifier.width(16.dp))
    }, colors = TopAppBarDefaults.topAppBarColors(
        containerColor = Primary, titleContentColor = White
    ), scrollBehavior = scrollBehavior
    )
}