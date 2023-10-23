package gupsmile.com.ui.settingScreens.registerNewUserScreen.registerNewUserScreenElements

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.ensayo.example.ui.commonElements.textCommonHomePage
import com.ensayo.example.ui.commonElements.textCommonHomePageString
import gupsmile.com.R
import gupsmile.com.ui.commonElements.BottomActionsAlertDialogMenu
import gupsmile.com.ui.theme.GupsMileTheme

@Composable
fun AlertDialogConfirmCreateAccount(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    titleAlertDialogMenu: String,
    @StringRes confirmBottomText: Int,
    @DrawableRes iconDialogMenu: Int,
    onConfirmLogOut: () -> Unit,
){
    Dialog(
        onDismissRequest = { onDismiss }
    ) {
        Card(
            modifier = modifier,
            shape = RoundedCornerShape(15.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.color_alert_dialog_menu)
            )

        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 26.dp, bottom = 40.dp, start = 25.dp, end = 25.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = iconDialogMenu),
                    contentDescription = "Search Icon",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(50.dp)
                )
                Spacer(modifier = modifier.height(22.dp))

                SelectionContainer {
                    textCommonHomePageString(
                        stringResTextEntry = titleAlertDialogMenu,
                        maxLinesResParameter = 5,
                        lineHeightParameter = 15.sp,
                        fontSizeStyleParameter = 15.sp,
                        fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_regular)),
                        colorStyleParameter = Color.Black,
                        textAlignPersonalized = TextAlign.Center
                    )
                }

                Spacer(modifier = modifier.height(55.dp))
                BottomActionsAlertDialogMenu(
                    actionsBottomText = confirmBottomText,
                    onConfirmActions =  onConfirmLogOut ,
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AlertDialogConfirmCreateAccountPreview() {
    GupsMileTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            AlertDialogConfirmCreateAccount(
                onDismiss = {},
                onConfirmLogOut = {},
                titleAlertDialogMenu = "Cuenta creada exitosamente",
                confirmBottomText = R.string.name_bottom_alert_dialog_retrieve_password_alert_dialog,
                iconDialogMenu = R.drawable.succes_create_account_icon
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AlertDialogWarningCreateAccountPreview() {
    GupsMileTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            AlertDialogConfirmCreateAccount(
                onDismiss = {},
                onConfirmLogOut = {},
                titleAlertDialogMenu = "Ups! Algo salió mal, vuelve a intentar",
                confirmBottomText = R.string.name_action_bottom_warning_error_crate_new_account,
                iconDialogMenu = R.drawable.warning_icon
            )
        }
    }
}