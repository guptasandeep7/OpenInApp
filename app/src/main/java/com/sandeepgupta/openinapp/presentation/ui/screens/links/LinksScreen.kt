package com.sandeepgupta.openinapp.presentation.ui.screens.links

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandeepgupta.openinapp.R
import com.sandeepgupta.openinapp.data.ApiState
import com.sandeepgupta.openinapp.domain.model.Link
import com.sandeepgupta.openinapp.presentation.ui.common.ErrorDialog
import com.sandeepgupta.openinapp.presentation.ui.screens.links.components.AppBar
import com.sandeepgupta.openinapp.presentation.ui.screens.links.components.FAQCard
import com.sandeepgupta.openinapp.presentation.ui.screens.links.components.HighlightCardsRow
import com.sandeepgupta.openinapp.presentation.ui.screens.links.components.LinksCard
import com.sandeepgupta.openinapp.presentation.ui.screens.links.components.QuadLineChart
import com.sandeepgupta.openinapp.presentation.ui.screens.links.components.WhatsappCard
import com.sandeepgupta.openinapp.presentation.ui.theme.Outline
import com.sandeepgupta.openinapp.presentation.ui.theme.Primary
import com.sandeepgupta.openinapp.presentation.ui.theme.figTreeFontFamily
import com.sandeepgupta.openinapp.presentation.ui.theme.greyTextStyle
import com.sandeepgupta.openinapp.presentation.viewmodel.DashboardViewModel
import com.sandeepgupta.openinapp.util.copyText
import com.sandeepgupta.openinapp.util.formatDate
import com.sandeepgupta.openinapp.util.getWelcomeText
import com.sandeepgupta.openinapp.util.goToWeb

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LinksScreen(viewModel: DashboardViewModel) {

    val dashboardData = viewModel.dashboardData.collectAsState(ApiState.Loading())
    val linkButtons = listOf("Top Links", "Recent Links")
    var selectedLinkOption by remember {
        mutableStateOf(linkButtons[0])
    }
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = {
            AppBar(scrollBehavior)
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(it)
                .background(Primary)
                .background(
                    Color(0xFFF5F5F5), shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                ), contentAlignment = Alignment.Center
        ) {
            Column(
                Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(24.dp))

                when (dashboardData.value) {

                    // when data is loading show progress indicator
                    is ApiState.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    // error occurred show dialog and retry
                    is ApiState.Error -> {
                        ErrorDialog(
                            errorMsg = dashboardData.value.errorMsg ?: "Something went wrong!!!"
                        ) {
                            viewModel.fetchDashboardData()
                        }
                    }

                    // data loaded successfully show ui
                    is ApiState.Success -> {

                        val data = dashboardData.value.data!!

                        // show greeting according to time
                        Text(
                            text = getWelcomeText(), style = greyTextStyle, fontSize = 16.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row {
                            Text(
                                text = "Sandeep Gupta", style = TextStyle(
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight(600),
                                )
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Image(
                                painterResource(id = R.drawable.emoji_wave_hands),
                                contentDescription = null,
                                Modifier.size(28.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))


                        // Graph Card
                        Column(
                            modifier = Modifier
                                .background(color = White, shape = RoundedCornerShape(12.dp))
                                .padding(12.dp)
                        ) {

                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {

                                Text(text = "Overview", style = greyTextStyle)

                                val startingDate =
                                    formatDate(data.data.overall_url_chart.iterator().next().key)
                                val endDate = formatDate(
                                    data.data.overall_url_chart.toList().reversed().first().first
                                )

                                OutlinedButton(
                                    onClick = { /*TODO*/ },
                                    shape = RoundedCornerShape(6.dp)
                                ) {
                                    Text(
                                        text = "$startingDate-$endDate",
                                        color = MaterialTheme.colors.onBackground
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Icon(
                                        painterResource(id = R.drawable.ic_time),
                                        contentDescription = null,
                                        tint = Color.Gray,
                                        modifier = Modifier.size(32.dp)
                                    )
                                }
                            }
                            QuadLineChart(
                                data.data.overall_url_chart.toList(),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // Details/highlights Card
                        HighlightCardsRow(data)

                        Spacer(modifier = Modifier.height(20.dp))

                        // View Analytics Button
                        OutlinedButton(
                            onClick = { /*TODO*/ },
                            Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Transparent, contentColor = Black
                            )
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painterResource(id = R.drawable.ic_arrow),
                                    contentDescription = "View Analytics Button"
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = "View Analytics", style = TextStyle(
                                        fontSize = 16.sp,
                                        lineHeight = 24.sp,
                                        fontFamily = figTreeFontFamily,
                                        fontWeight = FontWeight(600)
                                    )
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(40.dp))


                        // Top and Recent links
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            MultiToggleButton(
                                currentSelection = selectedLinkOption, linkButtons
                            ) { newSelectedOption ->
                                selectedLinkOption = newSelectedOption
                            }

                            // Search Button
                            IconButton(
                                onClick = { /*TODO*/ }, Modifier.border(
                                    width = 1.dp, color = Outline, shape = RoundedCornerShape(8.dp)
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = "Search Button",
                                    tint = Color.Gray
                                )
                            }
                        }

                        Spacer(modifier = Modifier.size(28.dp))

                        Column(
                            verticalArrangement = Arrangement.spacedBy(20.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                        ) {

                            val linkList: List<Link> = if (selectedLinkOption == linkButtons[0]) {
                                data.data.top_links
                            } else {
                                data.data.recent_links
                            }

                            linkList.forEach { item ->
                                LinksCard(item,
                                    onLinkClick = {
                                        goToWeb(context, item.web_link)
                                    }, copyText = {
                                        copyText(context, item.web_link)
                                        Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show()
                                    })
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // view all links button
                        OutlinedButton(
                            onClick = { /*TODO*/ },
                            Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Transparent, contentColor = Black
                            )
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painterResource(id = R.drawable.ic_link),
                                    contentDescription = "View all Links"
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = "View all Links", style = TextStyle(
                                        fontSize = 16.sp,
                                        lineHeight = 24.sp,
                                        fontFamily = figTreeFontFamily,
                                        fontWeight = FontWeight(600)
                                    )
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(40.dp))

                        // whatsapp button
                        WhatsappCard() {
                            val url =
                                "https://api.whatsapp.com/send?phone=${data.support_whatsapp_number}"
                            val openwhatsappintent = Intent(Intent.ACTION_VIEW)
                            openwhatsappintent.data = Uri.parse(url)
                            context.startActivity(openwhatsappintent)
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // FAQ Button
                        FAQCard()

                        Spacer(modifier = Modifier.height(20.dp))


                    }
                }

            }

        }
    }


}


@Composable
fun MultiToggleButton(
    currentSelection: String, buttons: List<String>, onToggleChange: (String) -> Unit
) {
    val selectedTint = MaterialTheme.colors.primary
    val unselectedTint = Color.Unspecified

    Row(
        modifier = Modifier.height(IntrinsicSize.Min)
    ) {
        buttons.forEachIndexed { index, toggleState ->
            val isSelected = currentSelection.lowercase() == toggleState.lowercase()
            val backgroundTint = if (isSelected) selectedTint else unselectedTint
            val textColor = if (isSelected) Color.White else Color(0xFF999CA0)


            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .background(backgroundTint)
                    .padding(vertical = 6.dp, horizontal = 16.dp)
                    .toggleable(value = isSelected, enabled = true, onValueChange = { selected ->
                        if (selected) {
                            onToggleChange(toggleState)
                        }
                    }),
            ) {
                Text(
                    toggleState, style = TextStyle(
                        fontFamily = figTreeFontFamily,
                        fontWeight = FontWeight(600),
                        fontSize = 16.sp,
                        color = textColor
                    ), modifier = Modifier.padding(4.dp)
                )
            }

        }
    }
}



