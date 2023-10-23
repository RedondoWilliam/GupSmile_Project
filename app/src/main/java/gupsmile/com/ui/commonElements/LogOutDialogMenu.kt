package gupsmile.com.ui.commonElements

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.ColumnScopeInstance.align
//import androidx.compose.foundation.layout.ColumnScopeInstance.align
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.ensayo.example.ui.commonElements.textCommonHomePage
import gupsmile.com.R
import gupsmile.com.ui.theme.GupsMileTheme


@Composable
fun LogOutDialogMenu(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    @StringRes titleAlertDialogMenu: Int,
    @StringRes confirmBottomText: Int,
    @StringRes dimmissBottomText: Int,
    onConfirmLogOut: () -> Unit,

){
    AlertDialog(
        onDismissRequest =onDismiss,
        title = {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, bottom = 30.dp, )
            ) {
                textCommonHomePage(
                    stringResTextEntry = titleAlertDialogMenu,
                    maxLinesResParameter = 1,
                    lineHeightParameter = 18.sp,
                    fontSizeStyleParameter = 18.sp,
                    fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_regular)),
                    colorStyleParameter = Color.Black
                )
            }
        },
        confirmButton = {
           BottomActionsAlertDialogMenu(
               actionsBottomText = confirmBottomText,
               onConfirmActions = onConfirmLogOut,
           )
        },
        dismissButton = {
            BottomActionsAlertDialogMenu(
                actionsBottomText = dimmissBottomText,
                onConfirmActions = onDismiss
            )
        },
        shape = RoundedCornerShape(15.dp),
        modifier = modifier
            .height(168.dp),
        containerColor = colorResource(id = R.color.color_alert_dialog_menu)
    )
}

@Composable
fun BottomActionsAlertDialogMenu(
    modifier: Modifier = Modifier,
    actionsBottomText: Int,
    onConfirmActions: ()-> Unit
){
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(15.dp))
            .background(Color.Black)
            .width(127.dp)
            .height(32.dp)
            .padding(start = 25.dp, end = 25.dp, top = 5.dp, bottom = 8.dp)
            .clickable { onConfirmActions() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        textCommonHomePage(
            stringResTextEntry = actionsBottomText,
            maxLinesResParameter = 1,
            lineHeightParameter = 14.sp,
            fontSizeStyleParameter = 14.sp,
            fontFamilyStyleParameter =FontFamily(Font(R.font.raleway_medium)),
            colorStyleParameter = Color.White,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun LogOutDialogMenuPreview(){
    GupsMileTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            LogOutDialogMenu(
                onDismiss = {},
                onConfirmLogOut = {},
                titleAlertDialogMenu = R.string.titleAlertDialogMenu,
                confirmBottomText = R.string.confirmBottomText,
                dimmissBottomText = R.string.dimmissBottomText
            )
        }
    }
}