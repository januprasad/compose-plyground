package com.cakap.compose_plyground.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    hint: String,
    modifier: Modifier = Modifier,
    isEnabled: (Boolean) = true,
    searching: (Boolean) = false,
    height: Dp = Dimens.dp40,
    elevation: Dp = Dimens.dp3,
    cornerShape: Shape = RoundedCornerShape(Dimens.dp8),
    backgroundColor: Color = Color.White,
    onSearchClicked: (String) -> Unit = {},
) {
    var text by remember { mutableStateOf(TextFieldValue()) }
    Column {
        Row(
            modifier = Modifier
                .height(height)
                .fillMaxWidth()
                .shadow(elevation = elevation, shape = cornerShape)
                .background(color = backgroundColor, shape = cornerShape)
                .clickable { onSearchClicked(text.text) },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BasicTextField(
                modifier = modifier
                    .weight(5f)
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.dp24),
                value = text,
                onValueChange = {
                    text = it
                },
                enabled = isEnabled,
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = Dimens.sp16,
                    fontWeight = FontWeight.Bold,
                ),
                decorationBox = { innerTextField ->
                    if (text.text.isEmpty()) {
                        Text(
                            text = hint,
                            color = Color.Gray.copy(alpha = 0.5f),
                            fontSize = Dimens.sp16,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    innerTextField()
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search,
                ),
                keyboardActions = KeyboardActions(onSearch = { onSearchClicked(text.text) }),
                singleLine = true,
            )
            Box(
                modifier = modifier
                    .weight(1f)
                    .size(Dimens.dp40)
                    .background(color = Color.Transparent, shape = CircleShape),
            ) {
                Icon(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    imageVector = Icons.Default.Search,
                    contentDescription = "something",
                )
            }
        }
        AnimatedVisibility(searching) {
            LinearProgressIndicator(
                Modifier.fillMaxWidth().height(1.dp)
                    .padding(horizontal = 10.dp)
                    .clip(RoundedCornerShape(16.dp)),
            )
        }
    }
}
