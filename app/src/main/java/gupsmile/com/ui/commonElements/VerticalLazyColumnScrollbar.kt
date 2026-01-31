package gupsmile.com.ui.commonElements

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun Modifier.VerticalLazyColumnScrollBar(
    lazyListState: LazyListState,
    width: Dp = 4.dp,
    showScrollBarTrack: Boolean = true,
    scrollBarTrackColor: Color = MaterialTheme.colorScheme.surface.copy(0.5f),
    scrollBarColor: Color = MaterialTheme.colorScheme.outline,
    scrollBarCornerRadius: Float = 4f,
    endPadding: Float= 6f,
    visibleState: Boolean = false
): Modifier{
    val alphaState = rememberScrollbarVisibility(lazyListState)
    return drawWithContent {

        drawContent()


        val layoutInfo = lazyListState.layoutInfo
        val viewportHeight = size.height

        // Total height of the content
        val totalItems = layoutInfo.totalItemsCount
        val firstVisibleIndex = lazyListState.firstVisibleItemIndex
        val firstItemOffset =lazyListState.firstVisibleItemScrollOffset.toFloat()

        if(totalItems == 0) return@drawWithContent

        // estimated height of each items(it could change if you have items with different heights)
        val averageItemHeight =
            if(layoutInfo.visibleItemsInfo.isNotEmpty()){
                layoutInfo.visibleItemsInfo.sumOf { it.size }/ layoutInfo.visibleItemsInfo.size.toFloat()
            }else{
                0f
            }

        val totalContentHeight = totalItems* averageItemHeight
        val scrollHeight = firstVisibleIndex*averageItemHeight + firstItemOffset


        // scrollbar height in proportion to the viewport
        val scrollBarHeight =(viewportHeight / totalContentHeight) * viewportHeight

        //bar's vertical Offset
        val scrollBarStartOfffset = (scrollHeight / totalContentHeight)*viewportHeight

        if(showScrollBarTrack){
            drawRoundRect(
                cornerRadius = CornerRadius(scrollBarCornerRadius),
                color = scrollBarTrackColor,
                topLeft = Offset(this.size.width - endPadding, 8f),
                size = Size(width.toPx(), viewportHeight)
            )
        }

        if(alphaState.value > 0f){
            drawRoundRect(
                cornerRadius = CornerRadius(scrollBarCornerRadius),
                color = scrollBarColor.copy(alpha = alphaState.value),
                topLeft = Offset(this.size.width - endPadding, viewportHeight - scrollBarStartOfffset - scrollBarHeight),
                size = Size(width.toPx(), scrollBarHeight)
            )
        }




    }


}



@Composable
fun rememberScrollbarVisibility(
    lazyListState: LazyListState,
    hideDelayMills: Long = 450,
    fadeDurationMills: Int =  300
): State<Float>{
    var targetAlpha by remember { mutableFloatStateOf(0f) }
    LaunchedEffect(lazyListState.isScrollInProgress) {
        if(lazyListState.isScrollInProgress){
           targetAlpha = 1f
        }else{
            delay(hideDelayMills)
            targetAlpha = 0f
        }
    }

    val animatedAlpha by animateFloatAsState(
        targetValue = targetAlpha,
        animationSpec = tween(durationMillis = fadeDurationMills ),
        label = "scrollbar alpha"
    )

    return rememberUpdatedState(animatedAlpha)
}

