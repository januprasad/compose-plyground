package com.cakap.compose_plyground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.TextStyle
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
                    Column {
                        SimpleColumnDemo()
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
    CustomColumn {
        alphabets.forEachIndexed { index, alphabet ->
            val frontColor = Color(Random.nextLong(0xAFFFFFFA))
            val backColor = Color(Random.nextLong(0xAFFFFFFA))
            FlipCard(alphabet to index + 1, frontColor, backColor)
        }
    }
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

    Card(
        modifier = Modifier
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 8 * density
            }
            .clickable {
                rotated = !rotated
            }.padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = animateColor,
        ),
    ) {
        Column(
            Modifier.size(60.dp),
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
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                ),
            )
        }
    }
}

@Composable
fun CustomColumn(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Layout(
        modifier = modifier,
        content = content,
        measurePolicy = { measurebles, constraints ->
            val placeables = measurebles.map { measureable ->
                measureable.measure(constraints)
            }
            layout(constraints.maxWidth, constraints.maxHeight) {
                var xPosition = 0
                var yPosition = 0
                placeables.forEach { placeable ->
                    placeable.placeRelative(x = xPosition, y = yPosition)
                    if (xPosition + placeable.width * 2 <= constraints.maxWidth) {
                        xPosition += placeable.width
                    } else {
                        xPosition = 0
                        yPosition += placeable.height
                    }
                }
            }
        },
    )
}

val padding = 10
