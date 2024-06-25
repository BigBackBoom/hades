package com.bbb.hades.core.designsystem
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val darkTheme = darkColorScheme(
    primary =  AppColor.Dark.primary,
    onPrimary = AppColor.Dark.onPrimary,
    primaryContainer = AppColor.Dark.primaryContainer,
    onPrimaryContainer = AppColor.Dark.onPrimaryContainer,
    secondary = AppColor.Dark.secondary,
    onSecondary = AppColor.Dark.onSecondary,
    secondaryContainer = AppColor.Dark.secondaryContainer,
    onSecondaryContainer = AppColor.Dark.onSecondaryContainer,
    tertiary = AppColor.Dark.tertiary,
    onTertiary = AppColor.Dark.onTertiary,
    tertiaryContainer = AppColor.Dark.tertiaryContainer,
    onTertiaryContainer = AppColor.Dark.onTertiaryContainer,
    error = AppColor.Dark.error,
    onError = AppColor.Dark.onError,
    errorContainer = AppColor.Dark.errorContainer,
    onErrorContainer = AppColor.Dark.onErrorContainer,
    background = AppColor.Dark.background,
    onBackground = AppColor.Dark.onBackground,
    surface = AppColor.Dark.surface,
    onSurface = AppColor.Dark.onSurface,
    outline = AppColor.Dark.outline,
    surfaceVariant = AppColor.Dark.surfaceVariant,
    onSurfaceVariant = AppColor.Dark.onSurfaceVariant
)

val lightTheme = lightColorScheme(
    primary = AppColor.Light.primary,
    onPrimary = AppColor.Light.onPrimary,
    primaryContainer = AppColor.Light.primaryContainer,
    onPrimaryContainer = AppColor.Light.onPrimaryContainer,
    secondary = AppColor.Light.secondary,
    onSecondary = AppColor.Light.onSecondary,
    secondaryContainer = AppColor.Light.secondaryContainer,
    onSecondaryContainer = AppColor.Light.onSecondaryContainer,
    tertiary = AppColor.Light.tertiary,
    onTertiary = AppColor.Light.onTertiary,
    tertiaryContainer = AppColor.Light.tertiaryContainer,
    onTertiaryContainer = AppColor.Light.onTertiaryContainer,
    error = AppColor.Light.error,
    onError = AppColor.Light.onError,
    errorContainer = AppColor.Light.errorContainer,
    onErrorContainer = AppColor.Light.onErrorContainer,
    background = AppColor.Light.background,
    onBackground = AppColor.Light.onBackground,
    surface = AppColor.Light.surface,
    onSurface = AppColor.Light.onSurface,
    outline = AppColor.Light.outline,
    surfaceVariant = AppColor.Light.surfaceVariant,
    onSurfaceVariant = AppColor.Light.onSurfaceVariant
)

@Composable
fun HadesTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (isDarkTheme) {
        darkTheme
    } else {
        lightTheme
    }
    val typography = Typography(

        bodyMedium = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}