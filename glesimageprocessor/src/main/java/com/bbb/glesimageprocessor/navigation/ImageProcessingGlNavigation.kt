package com.bbb.glesimageprocessor.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.bbb.glesimageprocessor.view.ImageProcessingGlScreen

sealed class ImageProcessingGlRoute(val route: String) {
    data object ImageProcessingGl : ImageProcessingGlRoute(IMAGE_PROCESSING_GL_ROUTE) {
        data object ImageProcessingGlDetail : ImageProcessingGlRoute(IMAGE_PROCESSING_GL_DETAIL)
    }

    companion object {
        const val IMAGE_PROCESSING_GL_ROUTE = "image_processing_gl_route"
        const val IMAGE_PROCESSING_GL_DETAIL: String = "image_processing_gl_detail"
    }
}

fun NavController.navigateToImageGlProcessing(navOptions: NavOptions? = null) = navigate(
    ImageProcessingGlRoute.ImageProcessingGl.route, navOptions
)

fun NavGraphBuilder.imageProcessingGl(
    modifier: Modifier
) {
    navigation(
        route = ImageProcessingGlRoute.ImageProcessingGl.route,
        startDestination = ImageProcessingGlRoute.ImageProcessingGl.ImageProcessingGlDetail.route
    ) {
        composable(route = ImageProcessingGlRoute.ImageProcessingGl.ImageProcessingGlDetail.route) {
            ImageProcessingGlScreen(modifier = modifier)
        }
    }
}
