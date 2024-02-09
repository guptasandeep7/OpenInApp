package com.sandeepgupta.openinapp.presentation.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sandeepgupta.openinapp.R

val figTreeFontFamily = FontFamily(
    Font(R.font.figtree_regular),
    Font(R.font.figtree_black,FontWeight.Black),
    Font(R.font.figtree_bold,FontWeight.Bold),
    Font(R.font.figtree_extra_bold,FontWeight.ExtraBold),
    Font(R.font.figtree_medium,FontWeight.Medium),
    Font(R.font.figtree_regular,FontWeight.Normal),
    Font(R.font.figtree_semibold,FontWeight.SemiBold),
)

val greyTextStyle = TextStyle(
    fontFamily = figTreeFontFamily,
    fontSize = 14.sp,
    fontWeight = FontWeight(400),
    color = Color(0xFF999CA0)
)
