package com.cakap.compose_plyground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cakap.compose_plyground.ui.theme.ComposeplygroundTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeplygroundTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
//                    App() //pincode search
//                    PhoneLayout() // flow row example
//                    Column {
//                        SimpleColumnDemo()
//                    }
                    Column(
                        Modifier
                            .fillMaxSize().padding(10.dp),
                    ) {
                        Spacer(
                            modifier = Modifier
                                .fillMaxSize()
                                .drawWithContent {
                                    val cornerRadius = CornerRadius(25f, 25f)
                                    val path = Path().apply {
                                        addRoundRect(
                                            RoundRect(
                                                rect = Rect(
                                                    offset = Offset(0f, 0f),
                                                    size = Size(200f, 200f),
                                                ),
                                                bottomRight = cornerRadius, // just an example
                                                topRight = cornerRadius, // just an example
                                            ),
                                        )
                                    }
                                    drawPath(
                                        path,
                                        color = Color.Red,
                                        style = Stroke(width = 1.dp.toPx()),
                                    )
                                },
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun SimpleColumnDemo() {
    val shape = RoundedCornerShape(6.dp)
    val alphabets = listOf(
        "A",
        "B",
        "C",
        "D",
        "E",
        "F",
        "G",
        "H",
        "I",
        "J",
        "K",
        "L",
        "M",
        "N",
        "O",
        "P",
        "Q",
        "R",
        "S",
        "T",
        "U",
        "V",
        "W",
        "X",
        "Y",
        "Z",
    )
    SnakeFlow {
        alphabets.forEachIndexed { index, alphabet ->
            val frontColor = Color(Random.nextLong(0xAFFFFFFA))
            val backColor = Color(Random.nextLong(0xAFFFFFFA))
            FlipCard(alphabet to index + 1, frontColor, backColor)
        }
    }
}

@Composable
fun SnakeFlow(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Layout(
        modifier = modifier,
        content = content,
        measurePolicy = { measurebles, constraints ->
            val placeables = measurebles.map { measureable ->
                measureable.measure(constraints)
            }
            val maxWidth = constraints.maxWidth
            val step = maxWidth / 4
            var isForward = true
            layout(constraints.maxWidth, constraints.maxHeight) {
                var xPosition = 0
                var yPosition = 0
                placeables.forEach { placeable ->
                    placeable.place(x = xPosition, y = yPosition)
                    if (isForward && xPosition + step + step <= constraints.maxWidth) {
                        xPosition += step
                    } else if (isForward && xPosition != 0) {
                        // new row
                        yPosition += placeable.height
                        isForward = false
                        xPosition = constraints.maxWidth
                    }

                    if (!isForward) {
                        xPosition -= step
                    }

                    if (!isForward && xPosition + step == 0) {
                        isForward = true
                        xPosition = 0
                        yPosition += placeable.height
                    }
                }
            }
        },
    )
}

@Composable
fun FlipCard(pair: Pair<String, Int>, frontColor: Color, backColor: Color) {
    var rotated by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (rotated) 180f else 0f,
        animationSpec = tween(500),
    )

    val animateFront by animateFloatAsState(
        targetValue = if (!rotated) 1f else 0f,
        animationSpec = tween(500),
    )

    val animateBack by animateFloatAsState(
        targetValue = if (rotated) 1f else 0f,
        animationSpec = tween(500),
    )

    val animateColor by animateColorAsState(
        targetValue = if (rotated) frontColor else backColor,
        animationSpec = tween(500),
    )

    Box(
        modifier = Modifier
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 8 * density
            }
            .clickable {
                rotated = !rotated
            }
            .background(animateColor),
    ) {
        val configuration = LocalConfiguration.current
        val widthInDp = configuration.screenWidthDp.dp
        val boxWidth = widthInDp.div(4)
        Column(
            Modifier
                .size(boxWidth)
                .border(1.dp, Color.LightGray),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = if (!rotated) pair.first else pair.second.toString(),
                modifier = Modifier
                    .graphicsLayer {
                        alpha = if (rotated) animateBack else animateFront
                        rotationY = rotation
                    },
                style = TextStyle.Default.copy(
                    fontSize = 40.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.ExtraBold,
                ),
            )
        }
    }
}
