package gupsmile.com.ui.commonElements

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ensayo.example.ui.commonElements.textCommonHomePage
import gupsmile.com.R
import gupsmile.com.ui.theme.GupsMileTheme


@Composable
fun TextFieldPdTwo(
    modifier: Modifier = Modifier,
    textUser: String,
    ontextchage: (String) -> Unit,
    onDoneClicked: () -> Unit,
    focusRequester: FocusRequester,
    @StringRes textOnTextfield: Int,
    backgroundTextfield: Color,
){
    Box(
        modifier = modifier
            .wrapContentSize()
            .padding(start = 27.dp, end = 27.dp)
    ) {
        Row(
            modifier = modifier
                .clip(RoundedCornerShape(15.dp))
                .background(backgroundTextfield)
                .height(36.dp)
                .padding(start = 15.dp, end = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BasicTextField(
                value = textUser,
                onValueChange = {it:String ->
                    ontextchage(it)

                }  ,
                textStyle = TextStyle(
                    MaterialTheme.colorScheme.onTertiaryContainer,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.raleway_light))
                ),
                modifier = modifier
                    .fillMaxWidth(0.92f)
                    .padding(start = 4.dp)
                    .background(backgroundTextfield)
                    .focusRequester(focusRequester),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Email
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        onDoneClicked()
                    }
                ),
                singleLine = true,
                enabled = true,

                )



        }
        if(textUser == ""){
            textCommonHomePage(
                stringResTextEntry = textOnTextfield,
                maxLinesResParameter = 1,
                lineHeightParameter = 14.sp,
                fontSizeStyleParameter = 14.sp ,
                fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_light)),
                colorStyleParameter = MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = modifier
                    .offset(x = 22.dp, y = 10.dp )
            )

        }
    }
}
@Composable
@Preview(showBackground = true)
fun TextFieldPdTwoPreview(
) {

    GupsMileTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            TextFieldPdTwo(
                textUser = "",
                ontextchage = {} ,
                onDoneClicked  = {} ,
                focusRequester = FocusRequester(),
                textOnTextfield = R.string.phone_and_email,
                backgroundTextfield = MaterialTheme.colorScheme.tertiaryContainer
            )
        }
    }
}