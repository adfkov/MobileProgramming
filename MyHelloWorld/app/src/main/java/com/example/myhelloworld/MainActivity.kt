package com.example.myhelloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myhelloworld.ui.theme.MyHelloWorldTheme
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyHelloWorldTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreen() {
    Column(modifier = Modifier.padding(horizontal = 40.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = stringResource(id = R.string.calculate_tip),
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        TextField(value = "",
            onValueChange = {},
            modifier = Modifier.padding(bottom = 32.dp),
            label = { Text(text = stringResource(id = R.string.bill_amount)) },
            leadingIcon = { Icon(imageVector = Icons.Default.Money, contentDescription = null) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )
        val keyboardController = LocalSoftwareKeyboardController.current
        TextField(value = "",
            onValueChange = {},
            modifier = Modifier.padding(bottom = 32.dp),
            label = { Text(text = stringResource(id = R.string.how_was_the_service)) },
            leadingIcon = { Icon(imageVector = Icons.Default.Percent, contentDescription = null) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {keyboardController?.hide()})
        )
        Row(
            modifier = Modifier.padding(bottom = 32.dp, start = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = R.string.round_up_tip))
            Spacer(modifier = Modifier.width(50.dp))    //세로 height
            Switch(checked = false, onCheckedChange = null)
        }
        Text(text = stringResource(id = R.string.tip_amount, "$0.0"),
            style = MaterialTheme.typography.headlineMedium)
    }
}
