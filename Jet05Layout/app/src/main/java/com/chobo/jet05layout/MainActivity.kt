
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BmiCalculatorScreen()
        }
    }
}

@Preview
@Composable
fun BmiCalculatorScreen() {
    // 상태 관리를 위한 변수들
    var heightInput by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var isMetric by re member { mutableStateOf(false) } // 기본값을 미터로 설정
    var bmi by remember { mutableStateOf<String?>(null) }
    var height by remember { mutableStateOf(0f) } // 실제 계산에 사용될 높이 값

    // 키 입력 변환 함수
    fun convertAndSetHeight(input: String) {
        heightInput = input
        height = input.toFloatOrNull()?.let {
            if (isMetric) it else it / 100 // cm 단위이면 m 단위로 변환
        } ?: 0f // 올바른 숫자가 아닌 경우 0으로 리셋
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 단위 선택 스위치
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("m")
            Switch(
                checked = isMetric,
                onCheckedChange = {
                    isMetric = it
                    convertAndSetHeight(heightInput) // 스위치 토글 시 높이 다시 설정
                }
            )
            Text("cm")
        }

        // 키 입력 필드
        TextField(
            value = heightInput,
            onValueChange = { convertAndSetHeight(it) },
            label = { Text(if (isMetric) "키 (m)" else "키 (cm)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))

        // 체중 입력 필드
        TextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("체중 (kg)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(32.dp))

        // BMI 계산 버튼
        Button(onClick = {
            val w = weight.toFloatOrNull()
            if (height > 0f && w != null) {
                bmi = "%.2f".format(w / (height * height))
            } else {
                bmi = "Invalid input"
            }
        }) {
            Text("BMI 계산")
        }
        Spacer(modifier = Modifier.height(16.dp))

        // 계산된 BMI 출력
        bmi?.let {
            Text("당신의 BMI는 $it 입니다", fontSize = 18.sp)
        }
    }
}

