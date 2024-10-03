package com.bbb.hades.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.bbb.glesimageprocessor.navigation.imageProcessingGl
import com.bbb.imageprocessor.navigation.imageProcessing

@Composable
fun HadesNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController = navController, startDestination = ScreenChooserRoute.ScreenChooser.route) {
        imageProcessing(modifier)
        imageProcessingGl(modifier)
        screenChooser(modifier)
    }
}
