package kodz.org.core_ui.ui

import android.app.Activity
import android.content.ContextWrapper
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = ColorPalate.Dark.Primary,
    onPrimary = ColorPalate.Dark.Primary,

    secondary = ColorPalate.Dark.Secondary,
    onSecondary = ColorPalate.Dark.Secondary,

    tertiary = ColorPalate.Dark.Tertiary,
    onTertiary = ColorPalate.Dark.Tertiary,

    background = ColorPalate.Dark.Background,
    onBackground = ColorPalate.Dark.Background,

    surface = ColorPalate.Dark.Surface,
    onSurface = ColorPalate.Dark.Surface,
)

private val LightColorScheme = lightColorScheme(
    primary = ColorPalate.Light.Primary,
    onPrimary = ColorPalate.Light.Primary,

    secondary = ColorPalate.Light.Secondary,
    onSecondary = ColorPalate.Light.Secondary,

    tertiary = ColorPalate.Light.Tertiary,
    onTertiary = ColorPalate.Light.Tertiary,

    background = ColorPalate.Light.Background,
    onBackground = ColorPalate.Light.Background,

    surface = ColorPalate.Light.Surface,
    onSurface = ColorPalate.Light.Surface,
)

@Composable
fun TemplateAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = when (view.context) {
                is ContextWrapper -> ((view.context as ContextWrapper).baseContext as Activity).window
                else -> (view.context as Activity).window
            }

            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = if (darkTheme) DarkTypography else LightTypography,
        content = content
    )
}