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
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cakap.compose_plyground.component.RegularField
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
//                    shapeDrawCorner()
//                    ButtonSquircle()
//                    ScrollTextExample()
//                    DrawBorderExample()
                    // ScrollTextExample2()
                    //  ScrollTextExample()
                    // SimpleOutlinedTextFieldSample() Keyboar test
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Yellow)
                            .padding(10.dp),
                    ) {
//                        DrawBorderExample()
//                        TabsScreen()

                        Column {
                            CompositionLocalProvider(
                                LocalRippleTheme provides CustomRippleTheme(
                                    Color.DarkGray,
                                ),
                            ) {
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                                }
                            }
                            Spacer(modifier = Modifier.height(20.dp))
                            CompositionLocalProvider(
                                LocalRippleTheme provides CustomRippleTheme(
                                    Color.DarkGray,
                                ),
                            ) {
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(
                                        imageVector = Icons.Filled.Favorite,
                                        contentDescription = null,
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier.clickable(
                                    enabled = true,
                                    onClickLabel = null,
                                    role = Role.Button,
                                    onClick = {  },
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = rememberRipple(bounded = true),
                                ),
                            ) {
                                Text(text = "Submit", modifier = Modifier.padding(10.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

private class CustomRippleTheme(val color: Color) : RippleTheme {

    @Composable
    override fun defaultColor() = color

    @Composable
    override fun rippleAlpha() = RippleTheme.defaultRippleAlpha(
        color,
        lightTheme = isSystemInDarkTheme(),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleOutlinedTextFieldSample() {
    var username by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .height(200.dp)
            .background(Color.Yellow),
    )
    Spacer(modifier = Modifier.height(300.dp))
    OutlinedTextField(
        value = username,
        onValueChange = { username = it },
        label = { Text("Username") },
    )
    OutlinedTextField(
        value = otp,
        onValueChange = { otp = it },
        label = { Text("Password") },
    )
    Button(onClick = { /*TODO*/ }) {
        Text(text = "Submit")
    }
}

@Composable
private fun DrawBorderExample() {
    UShapeBorderSample()
}

@Composable
private fun UShapeBorderSample() {
    RegularField(hint = "Sample")
}

fun Modifier.semiBorder(strokeWidth: Dp, color: Color, cornerRadiusDp: Dp) = composed(
    factory = {
        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }
        val cornerRadius = density.run { cornerRadiusDp.toPx() }

        Modifier.drawBehind {
            val width = size.width
            val height = size.height

            drawLine(
                color = color,
                start = Offset(x = 0f, y = height - cornerRadius),
                end = Offset(x = 0f, y = cornerRadius),
                strokeWidth = strokeWidthPx,
            )

            // Top left arc
            drawArc(
                color = color,
                startAngle = 180f,
                sweepAngle = 90f,
                useCenter = false,
                topLeft = Offset.Zero,
                size = Size(cornerRadius * 2, cornerRadius * 2),
                style = Stroke(width = strokeWidthPx),
            )

            drawLine(
                color = color,
                start = Offset(x = cornerRadius, y = 0f),
                end = Offset(x = width, y = 0f),
                strokeWidth = strokeWidthPx,
            )

            // Top right arc
            drawArc(
                color = color,
                startAngle = 90f,
                sweepAngle = 90f,
                useCenter = false,
                topLeft = Offset(x = 0f, y = height - (cornerRadius * 2)),
                size = Size(cornerRadius * 2, cornerRadius * 2),
                style = Stroke(width = strokeWidthPx),
            )

            drawLine(
                color = color,
                start = Offset(x = cornerRadius, y = height),
                end = Offset(x = width, y = height),
                strokeWidth = strokeWidthPx,
            )
        }
    },
)

@Composable
private fun ScrollTextExample2() {
    Column(
        Modifier
            .wrapContentHeight()
            .padding(10.dp),
    ) {
        scrollableTest()
        scrollableTest()
        scrollableTest()
        scrollableTest()
        scrollableTest()
    }
}

@Composable
private fun ScrollTextExample() {
    LazyColumn(Modifier.wrapContentHeight()) {
        item {
            scrollableTest()
        }
        item {
            scrollableTest()
        }
        item {
            scrollableTest()
        }
        item {
            scrollableTest()
        }
        item {
            scrollableTest()
        }
    }
}

@Composable
fun scrollableTest() {
    val state = rememberScrollState()
    Text(
        text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum." +
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
        modifier = Modifier
            .height(200.dp)
            .padding(8.dp)
            .drawVerticalScrollbar(state)
            .verticalScroll(state),
        fontSize = 30.sp,
    )
}

@Composable
private fun ButtonSquircle() {
    Button(
        modifier = Modifier
            .width(140.dp)
            .height(40.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xff98c93c),
        ),
        onClick = { /* Action */ },
        shape = SquircleShape(
            percent = 40,
            cornerSmoothing = CornerSmoothing.High,
        ), // Fully rounded squircle shape.
    ) {
        Text(text = "Primary Enabled", fontSize = 12.sp)
    }
    Button(
        modifier = Modifier
            .width(140.dp)
            .height(40.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xff98c93c),
        ),
        onClick = { /* Action */ },
        shape = SquircleShape(
            percent = 40,
            cornerSmoothing = CornerSmoothing.High,
        ), // Fully rounded squircle shape.
    ) {
        Text(text = "Primary Enabled", fontSize = 12.sp)
    }
    Button(
        modifier = Modifier
            .width(140.dp)
            .height(40.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xff98c93c),
        ),
        onClick = { /* Action */ },
        shape = SquircleShape(
            percent = 40,
            cornerSmoothing = CornerSmoothing.High,
        ), // Fully rounded squircle shape.
    ) {
        Text(text = "Primary Enabled", fontSize = 12.sp)
    }
}

@Composable
fun shapeDrawCorner() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp),
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
