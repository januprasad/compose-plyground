package com.cakap.compose_plyground.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cakap.compose_plyground.semiBorder

object Dimens {
    val sp16 = 16.sp
    val dp40 = 40.dp
    val dp3 = 3.dp
    val dp8 = 8.dp
    val dp24 = 24.dp
    val sp12 = 12.sp
}

@Composable
fun RegularField(
    hint: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onTextChange: (String) -> Unit = {},
) {
    var text by remember { mutableStateOf(TextFieldValue()) }

    Box(
        modifier = Modifier
            .height(Dimens.dp40)
            .fillMaxWidth()
//            .shadow(elevation = Dimens.dp3, shape = RoundedCornerShape(Dimens.dp8))
            .semiBorder(2.dp, Color.Red, 6.dp)
            .background(Color.White, RoundedCornerShape(Dimens.dp8)),
    ) {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterStart)
                .padding(horizontal = Dimens.dp24),
            value = text,
            onValueChange = {
                text = it
                onTextChange(it.text)
            },
            textStyle = TextStyle(
                fontSize = Dimens.sp12,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = keyboardType,
                imeAction = imeAction,
            ),
            decorationBox = { innerTextField ->
                if (text.text.isEmpty()) {
                    Text(
                        text = hint,
                        fontSize = Dimens.sp12,
                        color = Color.DarkGray,
                    )
                }
                innerTextField()
            },
            singleLine = true,
        )
    }
}
