package com.cakap.compose_plyground

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun PhoneLayout() {
    val list = listOf("Samsung", "Apple", "Oppo", "OnePlus", "Redmi")
    Column {
        FlowRow(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
        ) {
            list.forEach {
                val colorCode = Random.nextLong(0xAFFFFFFA)
                val color = Color(colorCode)
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(10.dp)
                        .background(color, RoundedCornerShape(6.dp)),
                ) {
                    Text(text = it, modifier = Modifier.padding(10.dp))
                }
            }
        }
    }
}

@Composable
internal fun FlowRow(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Layout(
        content = content,
        measurePolicy = { measurables, constraints ->
            val placeables = measurables.map {
                it.measure(constraints)
            }
            val groupedPlaceables = mutableListOf<List<Placeable>>()
            var currentGroup = mutableListOf<Placeable>()
            var currentGroupWidth = 0

            placeables.forEach { placeable ->
                if (currentGroupWidth + placeable.width <= constraints.maxWidth) {
                    currentGroup.add(placeable)
                    currentGroupWidth += placeable.width
                } else {
                    groupedPlaceables.add(currentGroup)
                    currentGroup = mutableListOf(placeable)
                    currentGroupWidth = placeable.width
                }
            }

            if (currentGroup.isNotEmpty()) {
                groupedPlaceables.add(currentGroup)
            }

            layout(
                width = constraints.maxWidth,
                height = constraints.maxHeight,
            ) {
                var yPosition = 0
                groupedPlaceables.forEach { row ->
                    var xPosition = 0
                    row.forEach { placeable ->
                        placeable.place(
                            x = xPosition,
                            y = yPosition,
                        )
                        xPosition += placeable.width
                    }
                    yPosition += row.maxOfOrNull { it.height } ?: 0
                }
            }
        },
    )
}
