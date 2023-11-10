package gupsmile.com.ui.commonElements

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ensayo.example.ui.commonElements.textCommonHomePage
import gupsmile.com.R
import gupsmile.com.ui.theme.GupsMileTheme

@Composable
fun EmergentMenuPd(
    modifier: Modifier = Modifier,
    principalIconActions: @Composable () -> Unit,
    actionsListEmergentMenu: @Composable () -> Unit,
    idReview: String = ""
){

    val columnState = rememberScrollState()

    Box(
        modifier = modifier
            .clip(
                RoundedCornerShape(
                    topStart = 10.dp,
                    topEnd = 10.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                )
            )
            .height(426.dp)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(10.dp)

    ){
        Column(
            modifier = modifier
                .fillMaxWidth()
                .verticalScroll(columnState)
                .background(MaterialTheme.colorScheme.primaryContainer),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.barr_menu),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = modifier
                    .width(108.dp)
                    .height(8.dp)
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
        }
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
            .padding(10.dp)
            .clickable { actionsItemListBottom() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            painter = painterResource(id = drawableIcon),
            contentDescription = "Search Icon",
            tint = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier
                .size(17.5.dp)
        )
        Spacer(modifier = modifier.width(15.dp))
        textCommonHomePage(
            stringResTextEntry = titileTextAction,
            maxLinesResParameter = 2,
            lineHeightParameter = 14.sp,
            fontSizeStyleParameter = 14.sp,
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
                      PrincipalIconEmergentMenuItem(drawableIcon = R.drawable.no_coments_icon)
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
                  modifier = Modifier

              )
          }
      }
    }
}