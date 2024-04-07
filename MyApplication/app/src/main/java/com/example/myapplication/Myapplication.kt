package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.icons.Icons
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import java.lang.reflect.Modifier
import java.text.NumberFormat
import kotlin.reflect.KProperty


private val Icons.Filled.Percent: ImageVector
    get() {
        TODO("Not yet implemented")
    }

class MainActivity : AppCompatActivity() {

    private lateinit var etBillAmount: EditText
    private lateinit var etTipPercentage: EditText
    private lateinit var swRoundUp: Switch
    private lateinit var tvTipAmount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etBillAmount = findViewById(R.id.bill_amount)
        etTipPercentage = findViewById(R.id.how_was_the_service)
        swRoundUp = findViewById(R.id.round_up_tip)
        tvTipAmount = findViewById(R.id.tip_amount)

        // Assume there's a button with id btnCalculateTip
        findViewById<Button>(R.id.btnCalculateTip).setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip(): String {
        val billAmount = etBillAmount.text.toString().toDoubleOrNull() ?: return
        val tipPercentage = etTipPercentage.text.toString().toDoubleOrNull() ?: return
        var tip = billAmount * (tipPercentage / 100)

        if (swRoundUp.isChecked) {
            tip = kotlin.math.ceil(tip)
        }

        return NumberFormat.getCurrencyInstance().format(tip)
    }
}
}

        @Composable
        fun EditNumberField(value:String,
                            onValueChange:(String) -> Init
                            ,leadingIcon: ImageVector, label:Int) {
            TextField(value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier.padding(Bottom =32.dp)
                    keyboardOptions = KeyBoardOption
                )

            EditNumberField(amountInput,
                {amountInput = it},
                Icons.Default.Percent,
                R.string.how_was_the_service
                )

        }

    @Composable
    fun RoundTheTipRow(roundUp: Boolean, onCheckedChange :(Boolean) ->Init) {

    }

    RoundTheTipRow(roundUp, {roundUp = it}) // 상태 호이스팅