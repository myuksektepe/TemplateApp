package kodz.org.core_ui.ui

import androidx.compose.ui.graphics.Color

sealed class ColorPalate {
    object Light {
        val Primary = Color(0xFFB4D544)
        val Secondary = Color(0xFFFF9800)
        val Tertiary = Color(0xFF009688)
        val Background = Color(0xFFF0F0F0)
        val Background90 = Color(0xE6F0F0F0)
        val Background80 = Color(0xCCF0F0F0)
        val CorrectAnswerBg = Color(0xFF8BC34A)
        val WrongAnswerBg = Color(0xFFF44336)
        val Surface = Color(0xFFECECEC)
    }

    object Dark {
        val Primary = Color(0xFF7F9928)
        val Secondary = Color(0xFFBE770E)
        val Tertiary = Color(0xFF056D63)
        val Background = Color(0xFF2B292B)
        val Background90 = Color(0xFF212121)
        val Background80 = Color(0xFF424242)
        val CorrectAnswerBg = Color(0xFF608831)
        val WrongAnswerBg = Color(0xFFBB382F)
        val Surface = Color(0xFF5C5C5C)
    }
}

val ShimmerHighLight = Color(0xA3C2C2C2)