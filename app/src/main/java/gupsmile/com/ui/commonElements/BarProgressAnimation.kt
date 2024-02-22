package gupsmile.com.ui.commonElements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import gupsmile.com.R
import gupsmile.com.ui.theme.GupsMileTheme


//@Composable
//fun BarProgressAnimation(
//    modifier: Modifier = Modifier
//){
//    val composition by rememberLottieComposition(
//        LottieCompositionSpec.RawRes(R.raw.bar_progress_animation_lottie_five)
//    )
//    var isPlaying by remember { mutableStateOf(true) }
//    val progress by animateLottieCompositionAsState(
//        composition = composition,
//        isPlaying = isPlaying
//    )
//    LaunchedEffect(key1 = progress){
//        if(progress == 0f){
//            isPlaying = true
//        }
//        if(progress == 1f){
//            isPlaying = false
//        }
//    }
//    if(!isPlaying){
//        isPlaying = !isPlaying
//    }
//    Box(
//        contentAlignment = Alignment.Center,
//        modifier = modifier
//            .fillMaxWidth()
//            .background(color = Color.Transparent)
//    ) {
//        LottieAnimation(
//            modifier = modifier
//                .size(60.dp)
//                .fillMaxWidth(),
//            composition = composition,
//            progress = {progress}
//        )
//    }
//}
//
//@Composable
//@Preview(showBackground = true)
//fun BarProgressAnimationPreview() {
//    GupsMileTheme {
//        Box(
//            modifier = Modifier.fillMaxSize(),
//            contentAlignment = Alignment.Center
//        ) {
//            BarProgressAnimation()
//        }
//    }
//}
//
