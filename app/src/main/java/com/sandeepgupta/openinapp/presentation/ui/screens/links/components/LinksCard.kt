package com.sandeepgupta.openinapp.presentation.ui.screens.links.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandeepgupta.openinapp.R
import com.sandeepgupta.openinapp.domain.model.Link
import com.sandeepgupta.openinapp.presentation.ui.theme.Background
import com.sandeepgupta.openinapp.presentation.ui.theme.figTreeFontFamily
import com.sandeepgupta.openinapp.presentation.ui.theme.greyTextStyle
import com.sandeepgupta.openinapp.util.formatDate

@Composable
fun LinksCard(item: Link, onLinkClick: () -> Unit, copyText: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Background, shape = RoundedCornerShape(8.dp)),
    ) {

        Row(
            Modifier
                .padding(12.dp)
                .height(48.dp)
        ) {
            Image(
                painterResource(id = R.drawable.thumbnail_image),
                contentDescription = null,
                Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.size(12.dp))

            Column(Modifier.weight(1f)) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = item.title, style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = figTreeFontFamily,
                            fontWeight = FontWeight(400)
                        ),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier
                            .padding(end = 120.dp)
                            .weight(1f)
                    )

                    Text(
                        text = item.total_clicks.toString(), style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = figTreeFontFamily,
                            fontWeight = FontWeight(400)
                        )
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))

                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = formatDate(item.created_at, "dd MMM yyyy"),
                        style = greyTextStyle,
                        fontSize = 12.sp,
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = "Clicks", style = greyTextStyle, fontSize = 12.sp
                    )
                }
            }
        }

        Row(
            Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(Color(0xFFE8F1FF))
                .drawBehind {
                    drawRoundRect(color = Color(0xFFA6C7FF), style = stroke)
                }
                .padding(horizontal = 12.dp, vertical = 8.dp)) {
            Text(
                text = item.web_link, style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = figTreeFontFamily,
                    fontWeight = FontWeight(400),
                    color = MaterialTheme.colors.primary
                ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier
                    .padding(end = 120.dp)
                    .weight(1f)
                    .clickable(enabled = true) { onLinkClick() }
            )

            Image(
                painterResource(id = R.drawable.ic_copy),
                contentDescription = "Copy",
                modifier = Modifier.clickable(enabled = true) {
                    copyText()
                }
            )
        }
    }
}

val stroke = Stroke(
    width = 2f,
    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
)


