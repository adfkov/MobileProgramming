package com.chobo.act_0327

import android.graphics.Color
import android.os.Parcelable
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import kotlinx.android.parcel.Parcelize

@Composable // 0401
fun ImageWithSlot(img: String, slotBtn: @Composable () -> Unit) { // slot이 하나 들어올거다
    AsyncImage(model = img, contentDescription = "유해진님",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(200.dp)
            .clip(CircleShape) // 사이즈 200 동그랗게 모양
    )
    slotBtn()
}

@Composable
fun ImageWithSlot(img: Int, slotBtn: @Composable () -> Unit) { // slot이 하나 들어올거다
    Image(
        painter = painterResource(id = img),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(200.dp)
            .clip(CircleShape) // 사이즈 200 동그랗게 모양
    )
    slotBtn()
}

@Composable
fun ButtonWithIcon(counter: Int, onClick: () -> Unit) { // onclick 함수를 인자로
    Button(onClick = { onClick() }) {
        Icon(
            Icons.Default.Favorite,
            contentDescription = null,
            tint = Color.RED
        )
        if (counter > 0)
            Text("$counter")
        else
            Text("Like")
    }
}

private fun RowScope.Icon(favorite: ImageVector, contentDescription: Nothing?, tint: Int) {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IconWithBadge(counter: Int, onClick: () -> Unit) { // 0401 Unit으로 수정해보기
    Column(modifier = Modifier.padding(16.dp)) {
        BadgedBox(badge = {
            Badge {
                Text(text = "$counter")
            }
        }) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = null,
                tint = androidx.compose.ui.graphics.Color.Red,
                modifier = Modifier.clickable { onClick()}
            )
        }
    }
}
@Parcelize
data class ImgData(var img :Int,var counter: Int) : Parcelable
data class ImgData2(var img: Int, var counter: Int) {
    companion object { // 객체를 선언하지 않고도 가능한 static
        var imgID = "ImgID"
        var imgCounter = "Counter"
        var imgMapSaver = mapSaver(
            save = {mapOf(imgID to it.img, imgCounter to it.counter)},
            restore = {ImgData2(it[imgID] as Int, it[imgCounter] as Int)} // ImgDATA2 객체 생성

        )

        val imgListSaver = listSaver<ImgData2, Any>(
            save = { listOf(it.img, it.counter) },
            restore = {ImgData2(it[0] as Int, it[1] as Int)}
        )
    }
}

class ImgViewModel: ViewModel() {
    var imgList = mutableStateListOf<ImgData2>()
    init {
       imgList.add(ImgData2(R.drawable.grass, 10))
        imgList.add(ImgData2(R.drawable.grass, 11))
       imgList.add(ImgData2(R.drawable.grass, 22))

    }
    // 클래스이므로 함수도 자유롭게 넣는다.
    fun incrCount(index: Int) {
        imgList[index] = imgList[index].copy(counter = imgList[index].counter + 1)
    }
}
@Preview
@Composable
fun MainScreen(imgViewModel: ImgViewModel = viewModel()) {
//    var scrollState by rememberSaveable {
//        mutableStateOf("")
//    }
    val context = LocalContext.current



    var scrollState = rememberScrollState()

//    var img1 by rememberSaveable(stateSaver = ImgData2.imgMapSaver){
//        mutableStateOf(ImgData2(R.drawable.ftft, 10))
//    }
//    var img2 by rememberSaveable(stateSaver = ImgData2.imgMapSaver) {
//        mutableStateOf(ImgData2(R.drawable.android, 20))
//    }
//    var img3 by rememberSaveable(stateSaver = ImgData2.imgMapSaver) {
//        mutableStateOf(ImgData2(R.drawable.grass, 30))
//    }
//    var img4 by rememberSaveable(stateSaver = ImgData2.imgMapSaver) {
//        mutableStateOf(ImgData2(R.drawable.grass, 30))
//    }

    var img6 by remember {
        mutableStateOf(10)
    }

    var counter5 by remember {
        mutableStateOf(100)
    }

    var img5 by rememberSaveable(stateSaver = ImgData2.imgListSaver) {
        mutableStateOf(ImgData2(R.drawable.grass, 70))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState) ,
        horizontalAlignment = Alignment.CenterHorizontally // 가운데 정렬
    ) {
        ImageWithSlot(img = imgViewModel.imgList[0].img) { // 객체를 통으로 넘기는 것은 좋은 것이 아니다.
            ButtonWithIcon(counter = imgViewModel.imgList[0].counter) { imgViewModel.incrCount(0)} // copy 함수 내에서 ++ 연산 안된다.
        }
        ImageWithSlot(img = imgViewModel.imgList[1].img) {
            IconWithBadge(counter = imgViewModel.imgList[1].counter , { imgViewModel.incrCount(2)})
        }
//        ImageWithSlot(img = R.drawable.grass) {
//            ButtonWithIcon(counter = img3.counter , { img3 = img3.copy(counter = img3.counter + 1)}) // copy 함수 내에서 ++ 연산 안된다.
//        }
//        ImageWithSlot(img = "https://img.etoday.co.kr/pto_db/2015/06/20150619043028_658367_600_406.jpg") {
////            ButtonWithIcon(counter = img4 , {img4++} ) // counter++
//        }
//
        ImageWithSlot(img = imgViewModel.imgList[2].img) {
            IconWithBadge(counter = imgViewModel.imgList[2].counter , { imgViewModel.incrCount(2)})
        }

        AsyncImage(model = "https://img.etoday.co.kr/pto_db/2015/06/20150619043028_658367_600_406.jpg", contentDescription ="유해진")
    }
}
