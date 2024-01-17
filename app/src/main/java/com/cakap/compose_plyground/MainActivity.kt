package com.cakap.compose_plyground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cakap.compose_plyground.api.PostOffice
import com.cakap.compose_plyground.api.sendRequest
import com.cakap.compose_plyground.component.SearchBar
import com.cakap.compose_plyground.ui.theme.ComposeplygroundTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
    Column(modifier = Modifier.padding(10.dp)) {
        var pinCode by rememberSaveable { mutableStateOf("") }
        val address = remember { mutableStateOf(PostOffice()) }
        var searching by rememberSaveable { mutableStateOf(false) }
        val coSpace = rememberCoroutineScope()
        SearchBar(
            hint = "Pin code",
            searching = searching,
            modifier = Modifier.fillMaxWidth(),
            onSearchClicked = {
                pinCode = it
                if (pinCode.isNotEmpty()) {
                    searching = !searching
                    coSpace.launch {
                        sendRequest(pinCode, address)
                        delay(2000L)
                        searching = !searching
                    }
                }
            },
        )
        AddresCard(address.value)
    }
}

@Composable
fun AddresCard(address: PostOffice) {
    address.name?.let {
        Card(modifier = Modifier.fillMaxWidth().padding(top = 10.dp).wrapContentHeight()) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = address.name,
                    style = TextStyle.Default.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                    ),
                )
                Text(
                    text = address.district.orEmpty(),
                    style = TextStyle.Default.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                    ),
                )
                Text(
                    text = address.state.orEmpty(),
                    style = TextStyle.Default.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                    ),
                )
                Text(
                    text = address.country.orEmpty(),
                    style = TextStyle.Default.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                    ),
                )
                Text(
                    text = address.pincode.orEmpty(),
                    style = TextStyle.Default.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                    ),
                )
            }
        }
    }
}
