package com.bbb.hades

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bbb.hades.core.designsystem.HadesTheme
import com.bbb.hades.navigation.HadesNavHost

@Composable
fun HadesComposeApp() {
    HadesTheme {
        HadesNavHost(
            modifier = Modifier
        )
    }
}
