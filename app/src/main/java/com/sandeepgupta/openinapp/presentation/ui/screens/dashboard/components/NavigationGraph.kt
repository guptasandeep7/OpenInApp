package com.sandeepgupta.openinapp.presentation.ui.screens.dashboard.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sandeepgupta.openinapp.presentation.ui.screens.campaigns.CampaignsScreen
import com.sandeepgupta.openinapp.presentation.ui.screens.courses.CoursesScreen
import com.sandeepgupta.openinapp.presentation.ui.screens.create.CreateScreen
import com.sandeepgupta.openinapp.presentation.ui.screens.Screens
import com.sandeepgupta.openinapp.presentation.ui.screens.links.LinksScreen
import com.sandeepgupta.openinapp.presentation.ui.screens.profile.ProfileScreen
import com.sandeepgupta.openinapp.presentation.viewmodel.DashboardViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController,
    viewModel: DashboardViewModel,
) {
    NavHost(navController, Screens.Links.route) {
        composable(Screens.Links.route) {
            LinksScreen(viewModel)
        }
        composable(Screens.Courses.route) {
            CoursesScreen()
        }
        composable(Screens.Create.route) {
            CreateScreen()
        }
        composable(Screens.Campaigns.route) {
            CampaignsScreen()
        }
        composable(Screens.Profile.route) {
            ProfileScreen()
        }
    }

}