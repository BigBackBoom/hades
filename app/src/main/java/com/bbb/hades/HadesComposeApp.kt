package com.bbb.hades

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.bbb.hades.core.common.LocalNavController
import com.bbb.hades.core.designsystem.HadesTheme
import com.bbb.hades.navigation.HadesNavHost

@Composable
fun HadesComposeApp() {
    HadesTheme {
        val navController = rememberNavController()

        CompositionLocalProvider(
            LocalNavController provides navController
        ) {
            HadesNavHost(
                modifier = Modifier,
                navController = navController
            )
        }
    }
}
