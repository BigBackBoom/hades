package com.bbb.hades.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.bbb.hades.features.ImageProcessingScreen

const val IMAGE_PROCESSING_ROUTE = "image_processing_route"

fun NavController.navigateToImageProcessing(navOptions: NavOptions? = null) = navigate(
    IMAGE_PROCESSING_ROUTE, navOptions
)

fun NavGraphBuilder.imageProcessingScreen(
    modifier: Modifier
) {
    composable(route = IMAGE_PROCESSING_ROUTE) {
        ImageProcessingScreen(modifier = modifier)
    }
}
