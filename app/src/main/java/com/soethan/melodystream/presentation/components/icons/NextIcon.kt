package com.soethan.melodystream.presentation.components.icons


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp


/**
 * Next icon vector graphic created by the original author.
 *
 * Original source: [https://www.composables.com/icons]
 */
@Composable
fun NextIcon(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "skip_next",
            defaultWidth = 40.0.dp,
            defaultHeight = 40.0.dp,
            viewportWidth = 40.0f,
            viewportHeight = 40.0f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(29.25f, 29.75f)
                quadToRelative(-0.542f, 0f, -0.917f, -0.375f)
                reflectiveQuadToRelative(-0.375f, -0.958f)
                verticalLineTo(11.542f)
                quadToRelative(0f, -0.542f, 0.375f, -0.917f)
                reflectiveQuadToRelative(0.959f, -0.375f)
                quadToRelative(0.541f, 0f, 0.916f, 0.375f)
                reflectiveQuadToRelative(0.375f, 0.917f)
                verticalLineToRelative(16.875f)
                quadToRelative(0f, 0.583f, -0.395f, 0.958f)
                quadToRelative(-0.396f, 0.375f, -0.938f, 0.375f)
                close()
                moveToRelative(-17.792f, -1.417f)
                quadToRelative(-0.666f, 0.459f, -1.354f, 0.084f)
                quadToRelative(-0.687f, -0.375f, -0.687f, -1.125f)
                verticalLineTo(12.708f)
                quadToRelative(0f, -0.75f, 0.687f, -1.125f)
                quadToRelative(0.688f, -0.375f, 1.354f, 0.084f)
                lineToRelative(10.584f, 7.25f)
                quadToRelative(0.583f, 0.416f, 0.583f, 1.083f)
                reflectiveQuadToRelative(-0.583f, 1.083f)
                close()
                moveTo(12.042f, 20f)
                close()
                moveToRelative(0f, 4.75f)
                lineTo(18.958f, 20f)
                lineToRelative(-6.916f, -4.75f)
                close()
            }
        }.build()
    }
}