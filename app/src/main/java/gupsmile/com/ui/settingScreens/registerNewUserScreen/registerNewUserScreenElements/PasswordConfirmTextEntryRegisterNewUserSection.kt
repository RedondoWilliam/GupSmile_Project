package gupsmile.com.ui.settingScreens.registerNewUserScreen.registerNewUserScreenElements

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ensayo.example.ui.commonElements.textCommonHomePage
import gupsmile.com.R
import gupsmile.com.ui.theme.GupsMileTheme

@Composable
fun PasswordConfirmTextEntryRegisterNewUser(
    modifier: Modifier = Modifier,
    textPassword: String,
    ontextchage: (String) -> Unit,
    onCloseClicked : () -> Unit,
    onSearchClicked: (String) -> Unit,
    focusRequester: FocusRequester
){
    var visualTransformation: VisualTransformation by remember{ mutableStateOf(PasswordVisualTransformation()) }

    Box(
        modifier = modifier
            .wrapContentSize()
            .padding(start = 27.dp, end = 27.dp)
    ) {
        Row(
            modifier = modifier
                .clip(RoundedCornerShape(15.dp))
                .background(colorResource(id = R.color.text_entry_login_color))
                .height(36.dp)
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BasicTextField(
                value = textPassword,
                onValueChange = {it:String ->
                    ontextchage(it)
                }  ,
                textStyle = TextStyle(
                    MaterialTheme.colorScheme.onSecondaryContainer,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.raleway_extra_light)),
                    textAlign = TextAlign.Start
                ),
                modifier = modifier
                    .fillMaxWidth(0.92f)
                    .padding(start = 4.dp)
                    .background(colorResource(id = R.color.text_entry_login_color))
                    .focusRequester(focusRequester),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearchClicked(textPassword)
                    }
                ),
                singleLine = true,
                enabled = true,
                visualTransformation = visualTransformation
            )
            if(textPassword != "" ){
                if(visualTransformation == PasswordVisualTransformation() ){
                    Icon(
                        imageVector =  ImageVector.vectorResource(
                            R.drawable.close_eye_icon
                        ),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier
                            .clickable {
                                visualTransformation = VisualTransformation.None
                            }
                            .width(25.dp)
                    )
                }
                if(visualTransformation == VisualTransformation.None ){
                    Icon(
                        imageVector =  ImageVector.vectorResource(
                            R.drawable.open_eye_icon
                        ),
                        contentDescription = "",
                        tint = Color.Black,
                        modifier = Modifier
                            .clickable {
                                visualTransformation = PasswordVisualTransformation()
                            }
                            .width(25.dp)
                    )
                }
            }else{
                Spacer(modifier = Modifier.width(25.dp))
            }




        }
        if(textPassword == ""){
            textCommonHomePage(
                stringResTextEntry = R.string.password_textfield_confirm_register_new_user,
                maxLinesResParameter = 1,
                lineHeightParameter = 15.sp,
                fontSizeStyleParameter = 15.sp ,
                fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_extra_light)),
                colorStyleParameter = Color.Black,
                modifier = modifier
                    .offset(x = 22.dp, y = 10.dp )
            )

        }
    }
}
@Composable
@Preview(showBackground = true, device = "spec:width=392.7dp,height=850.9dp,dpi=440")
fun PasswordConfirmTextEntryRegisterNewUserPreview(
) {

    GupsMileTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            PasswordConfirmTextEntryRegisterNewUser(
                textPassword = "",
                ontextchage = {} ,
                onCloseClicked = { /*TODO*/ },
                onSearchClicked = {} ,
                focusRequester = FocusRequester()
            )
        }
    }
}