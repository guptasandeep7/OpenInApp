package com.sandeepgupta.openinapp.presentation.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sandeepgupta.openinapp.presentation.ui.screens.dashboard.MainUi
import com.sandeepgupta.openinapp.presentation.ui.theme.OpenInAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OpenInAppTheme {
                val navController: NavHostController = rememberNavController()
                MainUi(navController)
            }
        }
    }
}


