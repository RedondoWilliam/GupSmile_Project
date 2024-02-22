package gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.homeSbSnsEs.homeScreenSaved

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.Coil
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import dagger.hilt.android.lifecycle.HiltViewModel
import gupsmile.com.data.MyModuleAuthentication
import gupsmile.com.data.temporalConfig.ViewModelUrlsImages
import gupsmile.com.ui.commonElements.DialogLoading

//import gupsmile.com.data.temporalConfig.ViewModelUrlsImages

@Composable
fun HomeSnSavedSbSnPlCl(
    modifier: Modifier = Modifier,
    viewModelUrlImages: ViewModelUrlsImages?
){
    val viewModelUrlsState = viewModelUrlImages?.uiState?.collectAsState()?.value
    var gallery by remember{ mutableStateOf<List<String>>(listOf()) }
    if(viewModelUrlImages != null){
        if (viewModelUrlsState != null) {
            gallery = viewModelUrlsState.Urls as List<String>
        }
    }
    HomeSnSavedSbSn(
        galleryImages = {
            if(gallery.isEmpty()){
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    DialogLoading()
                }
            }else{
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = modifier.fillMaxSize()
                ){
                    items(gallery.size){index ->
                        val imageUrl = gallery[index]
                        CoilImage(
                            imageUrl = imageUrl,
                            contentDescription = null,
                            modifier = modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }

    )
}

@Composable
fun CoilImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop
){
    val painter = rememberAsyncImagePainter(
        ImageRequest
            .Builder(LocalContext.current)
            .data(data = imageUrl)
            .apply(block = fun ImageRequest.Builder.(){
                crossfade(true)
                transformations(RoundedCornersTransformation(
                    topLeft = 20f,
                    topRight = 20f,
                    bottomLeft = 20f,
                    bottomRight = 20f
                ))
            })
            .build()
    )

    Image(
        painter = painter ,
        contentDescription = contentDescription,
        modifier = modifier.padding(6.dp),
        contentScale = contentScale
    )
}
