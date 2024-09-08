package com.bbb.hades.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.bbb.hades.navigation.ImageProcessingRouter.IMAGE_PROCESSOR
import com.bbb.imageprocessor.view.ImageProcessingScreen

const val IMAGE_PROCESSING_ROUTE = "image_processing_route"

object ImageProcessingRouter {
    const val IMAGE_PROCESSOR: String = "image_processor"
}

fun NavController.navigateToImageProcessing(navOptions: NavOptions? = null) = navigate(
    IMAGE_PROCESSING_ROUTE, navOptions
)

fun NavGraphBuilder.imageProcessing(
    modifier: Modifier
) {
    navigation(
        route = IMAGE_PROCESSING_ROUTE,
        startDestination = IMAGE_PROCESSOR
    ) {
        composable(route = IMAGE_PROCESSOR) {
            ImageProcessingScreen(modifier = modifier)
        }
    }
}
