package com.sandeepgupta.openinapp.presentation.ui.screens.dashboard.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.sandeepgupta.openinapp.presentation.ui.screens.screens
import com.sandeepgupta.openinapp.presentation.ui.theme.Background
import com.sandeepgupta.openinapp.presentation.ui.theme.Primary

@Composable
fun BottomBar(
    navController: NavHostController,
    currentRoute: String?
) {

    BottomNavigation(
        backgroundColor = Background,
        modifier = Modifier.wrapContentHeight().fillMaxWidth(),
        elevation = 0.dp
    ) {

        screens.forEach { screen ->

            BottomNavigationItem(
                label = {
                    Text(
                        text = screen.title ?: "",
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight(600),
                            textAlign = TextAlign.Center,
                        )
                    )
                },
                icon = {
                    if (screen.title == null) {
                        FloatingActionButton(
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            modifier = Modifier
                                .clip(shape = CircleShape)
                                .size(60.dp),
                            backgroundColor = Primary,
                            contentColor = White
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Add Icon",
                                tint = White
                            )
                        }

                    } else {
                        Icon(
                            painterResource(id = screen.id!!),
                            contentDescription = screen.title
                        )
                    }

                },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Gray,

                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}