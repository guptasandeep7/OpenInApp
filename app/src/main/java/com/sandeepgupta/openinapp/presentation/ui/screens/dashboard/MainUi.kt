package com.sandeepgupta.openinapp.presentation.ui.screens.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sandeepgupta.openinapp.presentation.ui.screens.dashboard.components.BottomBar
import com.sandeepgupta.openinapp.presentation.ui.screens.dashboard.components.NavigationGraph
import com.sandeepgupta.openinapp.presentation.viewmodel.DashboardViewModel

@Composable
fun MainUi(
    navController: NavHostController,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            BottomBar(navController = navController, currentRoute = currentRoute)
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            NavigationGraph(navController = navController, viewModel)
        }
    }

}


