package com.bbb.hades.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.bbb.hades.view.ViewChoosingScreen

sealed class ScreenChooserRoute(val route: String) {
    data object ScreenChooser : ScreenChooserRoute(SCREEN_CHOOSER_ROUTE) {
        data object ScreenChooserDetail : ScreenChooserRoute(SCREEN_CHOOSER_DETAIL)
    }

    companion object {
        const val SCREEN_CHOOSER_ROUTE = "screen_chooser_route"
        const val SCREEN_CHOOSER_DETAIL: String = "screen_chooser_detail"
    }
}

fun NavController.navigateToScreenChooser(navOptions: NavOptions? = null) = navigate(
    ScreenChooserRoute.ScreenChooser.route, navOptions
)

fun NavGraphBuilder.screenChooser(
    modifier: Modifier
) {
    navigation(
        route = ScreenChooserRoute.ScreenChooser.route,
        startDestination = ScreenChooserRoute.ScreenChooser.ScreenChooserDetail.route
    ) {
        composable(route = ScreenChooserRoute.ScreenChooser.ScreenChooserDetail.route) {
            ViewChoosingScreen(modifier = modifier)
        }
    }
}
