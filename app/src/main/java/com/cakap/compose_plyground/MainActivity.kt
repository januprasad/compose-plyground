package com.cakap.compose_plyground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.cakap.compose_plyground.component.RegularField
import com.cakap.compose_plyground.component.SearchBar
import com.cakap.compose_plyground.ui.theme.ComposeplygroundTheme

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
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.SpaceEvenly) {
        var phoneNumber by rememberSaveable { mutableStateOf("") }
        RegularField(
            hint = "Phone number",
            keyboardType = KeyboardType.Phone,
        ) { phoneNumber = it }
        SearchBar(hint = "Pin code", modifier = Modifier.fillMaxWidth())
    }
}
