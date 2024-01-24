package com.cakap.compose_plyground


import androidx.compose.foundation.shape.CornerSize
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

/**
 *
 *  Base class for creating a Squircle based [Shape] defined by four corners and smoothing factor.
 *
 *  @param topStart The top start corner radius defined as [CornerSize].
 *  @param topEnd The top end corner radius defined as [CornerSize].
 *  @param bottomStart The bottom start corner radius defined as [CornerSize].
 *  @param bottomEnd The bottom end corner radius defined as [CornerSize].
 *  @param cornerSmoothing (0.55f - rounded corner shape, 1f - fully pronounced squircle).
 *
 **/
abstract class SquircleBasedShape(
    val topStart: CornerSize,
    val topEnd: CornerSize,
    val bottomStart: CornerSize,
    val bottomEnd: CornerSize,
    val cornerSmoothing: Float
) : Shape {

    final override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {

        var topStart = topStart.toPx(size, density)
        var topEnd = topEnd.toPx(size, density)
        var bottomEnd = bottomEnd.toPx(size, density)
        var bottomStart = bottomStart.toPx(size, density)
        val minDimension = size.minDimension

        if (topStart + bottomStart > minDimension) {
            val scale = minDimension / (topStart + bottomStart)
            topStart *= scale
            bottomStart *= scale
        }

        if (topEnd + bottomEnd > minDimension) {
            val scale = minDimension / (topEnd + bottomEnd)
            topEnd *= scale
            bottomEnd *= scale
        }

        require(topStart >= 0.0f && topEnd >= 0.0f && bottomEnd >= 0.0f && bottomStart >= 0.0f) {
            "Corner size in Px can't be negative(topStart = $topStart, topEnd = $topEnd, " +
                    "bottomEnd = $bottomEnd, bottomStart = $bottomStart)!"
        }

        return createOutline(
            size = size,
            topStart = topStart,
            topEnd = topEnd,
            bottomEnd = bottomEnd,
            bottomStart = bottomStart,
            cornerSmoothing = cornerSmoothing,
            layoutDirection = layoutDirection
        )

    }

    /**
     *
     * Creates [Outline] of this shape for the given [size].
     *
     * @param size the size of the shape boundary.
     * @param topStart the resolved size of the top start corner
     * @param topEnd the resolved size for the top end corner
     * @param bottomEnd the resolved size for the bottom end corner
     * @param bottomStart the resolved size for the bottom start corner
     * @param cornerSmoothing the resolved smoothing factor for all corners
     * @param layoutDirection the current layout direction.
     *
     */
    abstract fun createOutline(
        size: Size,
        topStart: Float,
        topEnd: Float,
        bottomEnd: Float,
        bottomStart: Float,
        cornerSmoothing: Float,
        layoutDirection: LayoutDirection
    ): Outline

    /**
     *
     * Creates a copy of this Shape with a new corner sizes.
     *
     * @param topStart a size of the top start corner
     * @param topEnd a size of the top end corner
     * @param bottomEnd a size of the bottom end corner
     * @param bottomStart a size of the bottom start corner
     * @param cornerSmoothing a factor for smoothing all corners
     *
     */
    abstract fun copy(
        topStart: CornerSize = this.topStart,
        topEnd: CornerSize = this.topEnd,
        bottomEnd: CornerSize = this.bottomEnd,
        bottomStart: CornerSize = this.bottomStart,
        cornerSmoothing: Float = this.cornerSmoothing
    ): SquircleBasedShape

    /**
     *
     * Creates a copy of this Shape with a new corner size.
     *
     * @param all a size to apply for all four corners
     * @param cornerSmoothing a factor for smoothing all corners
     *
     */
    fun copy(
        all: CornerSize,
        cornerSmoothing: Float = this.cornerSmoothing
    ): SquircleBasedShape = copy(all, all, all, all, cornerSmoothing)

}