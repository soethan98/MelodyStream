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
 * Play icon vector graphic created by the original author.
 *
 * Original source: [https://www.composables.com/icons]
 */
@Composable
fun PlayIcon(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "play_arrow",
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
                moveTo(15.542f, 30f)
                quadToRelative(-0.667f, 0.458f, -1.334f, 0.062f)
                quadToRelative(-0.666f, -0.395f, -0.666f, -1.187f)
                verticalLineTo(10.917f)
                quadToRelative(0f, -0.75f, 0.666f, -1.146f)
                quadToRelative(0.667f, -0.396f, 1.334f, 0.062f)
                lineToRelative(14.083f, 9f)
                quadToRelative(0.583f, 0.375f, 0.583f, 1.084f)
                quadToRelative(0f, 0.708f, -0.583f, 1.083f)
                close()
                moveToRelative(0.625f, -10.083f)
                close()
                moveToRelative(0f, 6.541f)
                lineToRelative(10.291f, -6.541f)
                lineToRelative(-10.291f, -6.542f)
                close()
            }
        }.build()
    }
}