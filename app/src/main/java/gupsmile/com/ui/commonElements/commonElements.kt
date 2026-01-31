package com.ensayo.example.ui.commonElements

import androidx.annotation.DimenRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.isSpecified
import androidx.compose.ui.unit.sp

@Composable
fun textCommonHomePage(
    modifier: Modifier = Modifier,
    @StringRes stringResTextEntry: Int,
    maxLinesResParameter: Int  ,
    lineHeightParameter: TextUnit,
    includeFontStylePaddingParameter: Boolean = false,
    lineHeightStyle: LineHeightStyle.Alignment = LineHeightStyle.Alignment.Bottom,
    fontSizeStyleParameter: TextUnit,
    fontFamilyStyleParameter: FontFamily?,
    colorStyleParameter: Color,
    textOverflow: TextOverflow =  TextOverflow.Ellipsis,
    textAlignPersonalized: TextAlign = TextAlign.Start,
    letterSpacing: TextUnit = TextUnit.Unspecified
){
    Text(
        modifier = modifier,
        text = stringResource(id = stringResTextEntry),
        maxLines = maxLinesResParameter,
        style = LocalTextStyle.current.merge(
           TextStyle(
               lineHeight = lineHeightParameter,
               platformStyle = PlatformTextStyle(
                   includeFontPadding = includeFontStylePaddingParameter,
               ),
               fontSize = fontSizeStyleParameter,
               lineHeightStyle = LineHeightStyle(
                   alignment = lineHeightStyle,
                   trim = LineHeightStyle.Trim.None
               ),
               fontFamily = fontFamilyStyleParameter ,
               color = colorStyleParameter,
           ),

        ),
        overflow = textOverflow,
        textAlign = textAlignPersonalized,
        letterSpacing = letterSpacing
    )
}

@Composable
fun textCommonHomePageString(
    modifier: Modifier = Modifier,
    stringResTextEntry: String,
    maxLinesResParameter: Int  ,
    lineHeightParameter: TextUnit,
    includeFontStylePaddingParameter: Boolean = false,
    lineHeightStyle: LineHeightStyle.Alignment = LineHeightStyle.Alignment.Bottom,
    fontSizeStyleParameter: TextUnit,
    fontFamilyStyleParameter: FontFamily?,
    colorStyleParameter: Color,
    textOverflow: TextOverflow =  TextOverflow.Ellipsis,
    textAlignPersonalized: TextAlign = TextAlign.Start,
    letterSpacing: TextUnit = TextUnit.Unspecified
){
    Text(
        modifier = modifier,
        text = stringResTextEntry,
        maxLines = maxLinesResParameter,
        style = LocalTextStyle.current.merge(
            TextStyle(
                lineHeight = lineHeightParameter,
                platformStyle = PlatformTextStyle(
                    includeFontPadding = includeFontStylePaddingParameter,
                ),
                fontSize = fontSizeStyleParameter,
                lineHeightStyle = LineHeightStyle(
                    alignment = lineHeightStyle,
                    trim = LineHeightStyle.Trim.None
                ),
                fontFamily = fontFamilyStyleParameter ,
                color = colorStyleParameter,
            ),

            ),
        overflow = textOverflow,
        textAlign = textAlignPersonalized,
        letterSpacing = letterSpacing
    )
}