package com.sandeepgupta.openinapp.presentation.ui.screens.links.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.sandeepgupta.openinapp.R
import com.sandeepgupta.openinapp.domain.model.DashboardModel

@Composable
fun HighlightCardsRow(data: DashboardModel) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            DetailCard(
                imageId = R.drawable.ic_click,
                title = "Today’s clicks",
                value = data.today_clicks.toString()
            )
        }
        item {
            DetailCard(
                imageId = R.drawable.ic_location,
                title = "Top Location",
                value = data.top_location
            )
        }
        item {
            DetailCard(
                imageId = R.drawable.ic_social,
                title = "Top source",
                value = data.top_source
            )
        }
        item {
            DetailCard(
                imageId = R.drawable.ic_time,
                title = "Best Time",
                value = data.startTime
            )
        }
    }
}