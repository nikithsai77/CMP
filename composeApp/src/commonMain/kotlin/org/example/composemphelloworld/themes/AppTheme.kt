package org.example.composemphelloworld.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

val LightColorTheme = lightColorScheme(
    primary = Primary,
    surface = Surface,
    onSurface = OnSurface,
    background = Background,
    onSurfaceVariant = OnSurfaceVariant,
    surfaceContainerLowest = SurfaceLowest
)

val DarkTheme = darkColorScheme(
    primary = DarkPrimary,
    surface = DarkSurface,
    onSurface = DarkOnSurface,
    background = DarkBackgroundDark,
    onSurfaceVariant = DarkOnSurfaceVariant,
    surfaceContainerLowest = DarkSurfaceLowest,
)

val Shape = Shapes(
    extraSmall = RoundedCornerShape(size = 5.dp),
    medium = RoundedCornerShape(size = 15.dp)
)

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) DarkTheme else LightColorTheme,
        typography = Typography,
        shapes = Shape,
        content = content
    )
}