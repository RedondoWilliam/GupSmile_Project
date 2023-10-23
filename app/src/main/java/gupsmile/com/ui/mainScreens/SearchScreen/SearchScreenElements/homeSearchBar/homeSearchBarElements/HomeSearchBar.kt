package gupsmile.com.ui.mainScreens.SearchScreen.SearchScreenElements.homeSearchBar.homeSearchBarElements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardVoice
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gupsmile.com.R
import gupsmile.com.ui.mainScreens.SearchScreen.SearchScreenElements.homeSearchBar.homeSearchBarPanelControl.SearchWidgetState
import gupsmile.com.ui.theme.GupsMileTheme


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun HomeSearchBar(
    searchWidgetState: SearchWidgetState,
    modifier: Modifier = Modifier,
    scroll: TopAppBarScrollBehavior?,
    text: String,
    ontextchage: (String) -> Unit,
    onCloseClicked : () -> Unit,
    onSearchClickedTwo: (String) -> Unit,
    backArrowClicked: () -> Unit = {},
    focusRequester: FocusRequester,
){
    val keyboardController = LocalSoftwareKeyboardController.current
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(end = 12.dp)
    ) {
        SearchAppBar(
            text = text,
            ontextchage = ontextchage,
            onCloseClicked = onCloseClicked,
            onSearchClicked = onSearchClickedTwo,
            focusRequester = focusRequester,
            searchWidgetState = searchWidgetState,
            modifier = Modifier.fillMaxWidth(0.92f)
        )
        Icon(
            painter = painterResource(id = R.drawable.filter),
            contentDescription = "Search Icon",
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .size(25.dp)
                .padding(end = 4.dp)
        )
        LaunchedEffect(Unit){
            keyboardController?.show()
            focusRequester.requestFocus()

        }
    }
}

@Composable
fun SearchAppBar(
    searchWidgetState: SearchWidgetState,
    modifier: Modifier = Modifier,
    text: String,
    ontextchage: (String) -> Unit,
    onCloseClicked : () -> Unit,
    onSearchClicked: (String) -> Unit,
    focusRequester: FocusRequester
){
    Box(
        modifier = modifier
            .wrapContentSize()
    ) {
        Row(
            modifier = modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .height(36.dp)
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Spacer(modifier = Modifier.width(3.dp))
            BasicTextField(
                value = text,
                onValueChange = {it:String ->
                    ontextchage(it)

                }  ,
                textStyle = TextStyle(
                    MaterialTheme.colorScheme.onSecondaryContainer,
                    fontSize = 16.sp
                ),
                modifier = modifier
                    .fillMaxWidth(0.92f)
                    .padding(4.dp)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .focusRequester(focusRequester),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearchClicked(text)
                    }
                ),
                singleLine = true,
                enabled = true,

                )

            if(text != "" ){
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier
                        .clickable {
                            if (text.isNotEmpty()) {
                                ontextchage("")
                            } else {
                                onCloseClicked()
                            }
                        }
                        .width(25.dp)
                )
            }else{
                Icon(
                    imageVector = Icons.Default.KeyboardVoice,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier
                        .clickable {
                            if (text.isNotEmpty()) {
                                ontextchage("")
                            } else {
                                onCloseClicked()
                            }
                        }
                        .width(25.dp)
                )
            }
        }
        if(text == ""){
            Text(
                text = "Buscar...",
                modifier = modifier
                    .offset(x = 52.dp, y = 8.1.dp ),
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SearchAppBarPreview(
) {

    GupsMileTheme {
        SearchAppBar(
            searchWidgetState = SearchWidgetState.OPENED,
            text = "hola",
            ontextchage = {} ,
            onCloseClicked = { /*TODO*/ },
            onSearchClicked = {} ,
            focusRequester = FocusRequester()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun DefaultAppBarSearchPreview(){
    GupsMileTheme {
        HomeSearchBar(
            searchWidgetState = SearchWidgetState.OPENED ,
            scroll = null,
            text = "" ,
            ontextchage = {},
            onCloseClicked = { /*TODO*/ },
            onSearchClickedTwo = {},
            focusRequester = FocusRequester()
        )
    }
}
