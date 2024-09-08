package com.bbb.imageprocessor.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.bbb.imageprocessor.view.ImageProcessingScreen

sealed class ImageProcessingRoute(val route: String) {
    data object ImageProcessing : ImageProcessingRoute(IMAGE_PROCESSING_ROUTE) {
        data object ImageProcessingDetail : ImageProcessingRoute(IMAGE_PROCESSING_DETAIL)
    }

    companion object {
        const val IMAGE_PROCESSING_ROUTE = "image_processing_route"
        const val IMAGE_PROCESSING_DETAIL: String = "image_processing_detail"
    }
}

fun NavController.navigateToImageProcessing(navOptions: NavOptions? = null) = navigate(
    ImageProcessingRoute.ImageProcessing.route, navOptions
)

fun NavGraphBuilder.imageProcessing(
    modifier: Modifier
) {
    navigation(
        route = ImageProcessingRoute.ImageProcessing.route,
        startDestination = ImageProcessingRoute.ImageProcessing.ImageProcessingDetail.route
    ) {
        composable(route = ImageProcessingRoute.ImageProcessing.ImageProcessingDetail.route) {
            ImageProcessingScreen(modifier = modifier)
        }
    }
}
