package gupsmile.com.ui.commonElements

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import gupsmile.com.ui.theme.GupsMileTheme


@Composable
fun DialogOptionsPersonalized(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onConfirmActions: () -> Unit,
    @StringRes textCancelBottom: Int,
    @StringRes textConfirmBottom: Int,
    @StringRes textInfoDialogMenu: Int,
    @DrawableRes iconDialogMenu : Int
){
    Dialog(
        onDismissRequest = { onDismiss }
    ) {
        Card(
            modifier = modifier,
            shape = RoundedCornerShape(15.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )

        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp, bottom = 40.dp, start = 8.dp, end = 8.dp)
                    .background(MaterialTheme.colorScheme.secondaryContainer),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = iconDialogMenu),
                    contentDescription = "Search Icon",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier
                        .size(50.dp)
                )
                Spacer(modifier = modifier.height(22.dp))

                textCommonHomePage(
                    stringResTextEntry = textInfoDialogMenu,
                    maxLinesResParameter = 5,
                    lineHeightParameter = 15.sp,
                    fontSizeStyleParameter = 15.sp,
                    fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_regular)),
                    colorStyleParameter = MaterialTheme.colorScheme.onSecondaryContainer,
                    textAlignPersonalized = TextAlign.Center
                )
                Spacer(modifier = modifier.height(55.dp))
                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    BottomActionDialogMenu(
                        actionsBottomText = textCancelBottom,
                        onConfirmActions = onDismiss,
                    )
                    Spacer(modifier = modifier.width(15.dp))
                    BottomActionDialogMenu(
                        actionsBottomText = textConfirmBottom,
                        onConfirmActions = onConfirmActions,
                    )
                }
            }
        }
    }
}

@Composable
fun BottomActionDialogMenu(
    modifier: Modifier = Modifier,
    actionsBottomText: Int,
    onConfirmActions: ()-> Unit
){
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.primary)
            .width(135.dp)
            .height(33.dp)
            .padding(start = 25.dp, end = 25.dp, top = 5.dp, bottom = 8.dp)
            .clickable { onConfirmActions() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        textCommonHomePage(
            stringResTextEntry = actionsBottomText,
            maxLinesResParameter = 1,
            lineHeightParameter = 15.sp,
            fontSizeStyleParameter = 15.sp,
            fontFamilyStyleParameter =FontFamily(Font(R.font.raleway_regular)),
            colorStyleParameter = MaterialTheme.colorScheme.onPrimary,
        )
    }
}


@Composable
@Preview(showBackground = true)
fun DialogOptionsPersonalizedPreview() {
    GupsMileTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
           DialogOptionsPersonalized(
               onDismiss = { /*TODO*/ },
               onConfirmActions = { /*TODO*/ },
               textCancelBottom = R.string.text_action_bottom_cancel,
               textConfirmBottom = R.string.text_action_bottom_aceptar,
               textInfoDialogMenu = R.string.text_content_dialog_delete_contact,
               iconDialogMenu = R.drawable.warning_icon
           )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DialogOptionsPersonalizedDarkModePreview() {
    GupsMileTheme(
        darkTheme = true
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            DialogOptionsPersonalized(
                onDismiss = { /*TODO*/ },
                onConfirmActions = { /*TODO*/ },
                textCancelBottom = R.string.text_action_bottom_cancel,
                textConfirmBottom = R.string.text_action_bottom_aceptar,
                textInfoDialogMenu = R.string.text_content_dialog_delete_contact,
                iconDialogMenu = R.drawable.warning_icon
            )
        }
    }
}