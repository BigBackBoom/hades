package com.bbb.hades

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.DisposableEffect
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity  : ComponentActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()

        super.onCreate(savedInstanceState)

        setContent {
            val darkTheme = isSystemInDarkTheme()
            DisposableEffect(darkTheme) {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.auto(
                        Color.TRANSPARENT,
                        Color.TRANSPARENT,
                    ) { darkTheme },
                    navigationBarStyle = if (darkTheme) {
                        SystemBarStyle.dark(darkScrim)
                    } else {
                        SystemBarStyle.light(
                            lightScrim,
                            darkScrim,
                        )
                    }
                )
                onDispose {}
            }
            HadesComposeApp()
        }
    }

    companion object {
        // Used to load the 'hades' library on application startup.
        init {
            System.loadLibrary("image-processing")
        }
    }
}

private val lightScrim = Color.argb(0x90, 0xFF, 0xFF, 0xFF)
private val darkScrim = Color.argb(0x80, 0x1b, 0x1b, 0x1b)
