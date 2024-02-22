package gupsmile.com.ui.commonElements

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ensayo.example.ui.commonElements.textCommonHomePage
import gupsmile.com.R
import gupsmile.com.ui.theme.GupsMileTheme
import kotlinx.coroutines.delay

@Composable
fun EmergentMenuPd(
    modifier: Modifier = Modifier,
    principalIconActions: @Composable () -> Unit,
    actionsListEmergentMenu: @Composable () -> Unit,
    idReview: String = "",

){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(start = 10.dp, bottom = 5.dp, top = 10.dp, end = 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.barr_menu),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = modifier
                .width(60.dp)
                .height(5.dp)
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            principalIconActions()
        }
        actionsListEmergentMenu()
        Spacer(modifier = modifier.height(15.dp))
    }
}

@Composable
fun PrincipalIconEmergentMenuItem(
    modifier: Modifier = Modifier,
    @DrawableRes drawableIcon: Int
){
    Box(
        modifier = modifier
            .padding(10.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)
            .size(42.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = drawableIcon),
            contentDescription = "Search Icon",
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .size(17.5.dp)
        )
    }
}

@Composable
fun ActionsListEmergentMenuItem(
    modifier: Modifier = Modifier,
    @DrawableRes drawableIcon: Int,
    @StringRes titileTextAction: Int,
    actionsItemListBottom: ()  -> Unit,
    idReview: String = ""
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 35.dp, top = 8.dp, bottom = 8.dp, end = 35.dp)
            .clip(RoundedCornerShape(50.dp))
            .clickable { actionsItemListBottom() }
            .height(40.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Spacer(modifier = modifier.width(10.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .size(30.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.tertiaryContainer)
        ) {
            Icon(
                painter = painterResource(id = drawableIcon),
                contentDescription = "Search Icon",
                tint = MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = Modifier
                    .size(17.5.dp)
            )
        }
        Spacer(modifier = modifier.width(10.dp))
        textCommonHomePage(
            stringResTextEntry = titileTextAction,
            maxLinesResParameter = 2,
            lineHeightParameter = 16.sp,
            fontSizeStyleParameter = 16.sp,
            fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_regular)),
            colorStyleParameter = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
@Preview(showBackground = true)
fun EmergentMenuPdPreview(){
    GupsMileTheme {
      Surface {
          Box(
              modifier = Modifier.fillMaxSize(),
              contentAlignment = Alignment.BottomStart
          ) {
              EmergentMenuPd(
                  principalIconActions = {
                      PrincipalIconEmergentMenuItem(drawableIcon = R.drawable.hide_icon)
                      Spacer(modifier = Modifier.width(28.dp))
                      PrincipalIconEmergentMenuItem(drawableIcon = R.drawable.no_coments_icon)
                      Spacer(modifier = Modifier.width(28.dp))
                      PrincipalIconEmergentMenuItem(drawableIcon = R.drawable.block_icon)

                  },
                  actionsListEmergentMenu = {
                      ActionsListEmergentMenuItem(
                          drawableIcon = R.drawable.edit_icon,
                          titileTextAction =R.string.name_list_action_edit_review,
                          actionsItemListBottom = {}
                      )
                      ActionsListEmergentMenuItem(
                          drawableIcon = R.drawable.delete_icon ,
                          titileTextAction = R.string.name_list_action_delete_review,
                          actionsItemListBottom = {}
                      )
                  },
                  modifier = Modifier,

              )
          }
      }
    }
}