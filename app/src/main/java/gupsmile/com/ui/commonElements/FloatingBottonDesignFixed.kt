package gupsmile.com.ui.commonElements

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import gupsmile.com.R

@Composable
fun FloatingBottonDesignFixed(
    modifier: Modifier = Modifier,
    @DrawableRes imageIcon: Int,
    coordinateX: Dp = 0.dp,
    coordinateY: Dp = 0.dp,
    sizeIcon: Dp = 44.dp,
    onclickBottomActions: () -> Unit = {},
    colorIcon: Color = MaterialTheme.colorScheme.onPrimary,
    containerColor: Color = MaterialTheme.colorScheme.primary

){
    FloatingActionButton(
        onClick = { onclickBottomActions() },
        shape = CircleShape,
        containerColor = containerColor,
        modifier = modifier
            .offset(coordinateX, coordinateY)
            .size(46.dp)
        ,
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(
                imageIcon
            ),
            contentDescription = null,
            tint = colorIcon,
            modifier = modifier.size(sizeIcon)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun FloatingBottonDesignFixedPreview(){
   Box (
       contentAlignment = Alignment.Center,
       modifier = Modifier
           .size(60.dp)

   ){
       FloatingBottonDesignFixed(
           imageIcon = R.drawable.menu,
           coordinateX = 0.dp,
           coordinateY = 0.dp
       )
   }
}