package gupsmile.com.ui.commonElements

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gupsmile.com.R

@Composable
fun FloatingBottomDesignPd(
    modifier: Modifier = Modifier,
    coordinateX: Dp = 0.dp,
    coordinateY: Dp = 0.dp,
    sizeIcon: Dp = 44.dp,
    onclickBottomActions: () -> Unit = {},
    elementsToShow: @Composable () -> Unit = {}
){
    FloatingActionButton(
        onClick = onclickBottomActions,
        containerColor = Color.Unspecified,
        contentColor = Color.Unspecified,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            hoveredElevation = 0.dp,
            focusedElevation = 0.dp
        ),
        modifier = modifier
            .offset(coordinateX, coordinateY)
            .size(46.dp),
    ) {
       elementsToShow()
    }
}

@Composable
@Preview(showBackground = true)
fun FloatingBottomDesignPdPreview(){
    Box (
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(60.dp)

    ){
        FloatingBottomDesignPd(
            coordinateX = 0.dp,
            coordinateY = 0.dp
        )
    }
}