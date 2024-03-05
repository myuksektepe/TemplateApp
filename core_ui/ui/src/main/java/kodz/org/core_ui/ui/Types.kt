package kodz.org.core_ui.ui

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import kodz.org.core_ui.extension.textSdp

val DarkTypography = Typography(
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
        color = Color.White
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.sp,
        color = Color.LightGray
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.sp,
        color = Color.LightGray
    ),
)

val LightTypography = Typography(
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
        color = Color.Black
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.sp,
        color = Color.DarkGray
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.sp,
        color = Color.DarkGray
    ),
)


class ExpandableBox() {
    @Composable
    fun TitleTextStyle() = TextStyle(
        fontFamily = avertaFamily,
        fontSize = 14.textSdp,
        letterSpacing = 0.sp,
        fontWeight = FontWeight.Bold,
    )

    @Composable
    fun BodyTextStyle() = TextStyle(
        fontFamily = avertaFamily,
        letterSpacing = 0.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 1.5.em
    )
}

class HtmlView() {
    @Composable
    fun TextStyle() = TextStyle(
        fontFamily = avertaFamily,
        fontSize = 14.textSdp,
        lineHeight = 1.5.em,
        letterSpacing = 0.sp
    )
}
