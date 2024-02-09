package com.sandeepgupta.openinapp.presentation.ui.screens

import com.sandeepgupta.openinapp.R

sealed class Screens(
    val route: String,
    val title: String? = null,
    val id: Int? = null
) {
    data object Links : Screens(
        route = "links_screen",
        title = "Links",
        id = R.drawable.ic_link
    )

    data object Courses : Screens(
        route = "courses_screen",
        title = "Courses",
        id = R.drawable.ic_course
    )

    data object Create : Screens(
        route = "create_screen",
        title = null,
        id = null
    )

    data object Campaigns : Screens(
        route = "campaigns_screen",
        title = "Campaigns",
        id = R.drawable.ic_campaign
    )

    data object Profile : Screens(
        route = "profile_screen",
        title = "Profile",
        id = R.drawable.ic_profile
    )

}

val screens = listOf(
    Screens.Links,
    Screens.Courses,
    Screens.Create,
    Screens.Campaigns,
    Screens.Profile
)