package gupsmile.com

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Density
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import gupsmile.com.R.color.color_top_bar_with_filter
import gupsmile.com.data.temporalConfig.StateChangesOnListReviews
import gupsmile.com.data.temporalConfig.StateGetReviews
import gupsmile.com.data.temporalConfig.ViewModelGetReviews
import gupsmile.com.data.temporalConfig.ViewModelUrlsImages
import gupsmile.com.ui.navigationApp.NavigationMainScreens
import gupsmile.com.ui.theme.GupsMileTheme
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.StateLoginAsVisitor
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.StateLoginWithGoogle
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.StateSignInUser
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.ViewModelAuthentication
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("ResourceAsColor", "CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        askNotificationPermission()
        tokenNew()

        setContent {
            GupsMileTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModelAuthentication: ViewModelAuthentication = hiltViewModel()
                    val viewModelUrlsImages: ViewModelUrlsImages by viewModels()
                    val viewModelGetReviews: ViewModelGetReviews by viewModels()
                    val viewModelGetReviewsUiState = viewModelGetReviews.uiState.collectAsState().value
                    val viewModelAuthenticationUiState = viewModelAuthentication.uiState.collectAsState().value
                    lifecycleScope.launch {
                        repeatOnLifecycle(Lifecycle.State.STARTED){
                           if(viewModelAuthentication.getCurrentUser() != null){
                               launch {
                                   viewModelUrlsImages.result.collect {
                                       viewModelUrlsImages.updateUrlsImages(result = it)
                                       Log.d("FlowDectect", "Collector activo UrlsIMages")
                                   }
                               }
                               launch {

//                                   viewModelGetReviews.result()

                                   viewModelGetReviews.result()
                                   Log.d("FlowDectect", "colector activo en repeatOnLifecycle.State.STARTED ")
                                   if(viewModelAuthentication.getCurrentUser() != null){
                                       launch {
                                           if(
                                               viewModelGetReviewsUiState.statechanges == StateChangesOnListReviews.NEWCHANGES ||
                                               viewModelAuthenticationUiState.stateLoginAsVisitor == StateLoginAsVisitor.SUCCESS ||
                                               viewModelAuthenticationUiState.stateLoginWithGoogle == StateLoginWithGoogle.SUCCESS ||
                                               viewModelAuthenticationUiState.stateSignInUser == StateSignInUser.SUCCESS ||
                                               viewModelGetReviewsUiState.statechanges == StateChangesOnListReviews.START ||
                                               viewModelGetReviewsUiState.state == StateGetReviews.RELOADINGLIST
                                           ){
                                               viewModelGetReviews.result()
                                               Log.d("FlowDectect", "colector activo en repeatOnLifecycle.State.STARTED")
                                           }
                                       }
                                   }
                               }
                           }
                        }
//                        repeatOnLifecycle(Lifecycle.State.RESUMED){
//                            if(viewModelAuthentication.getCurrentUser() != null){
//                                launch {
//                                   if(
//                                       viewModelGetReviewsUiState.statechanges == StateChangesOnListReviews.NEWCHANGES ||
//                                       viewModelAuthenticationUiState.stateLoginAsVisitor == StateLoginAsVisitor.SUCCESS ||
//                                       viewModelAuthenticationUiState.stateLoginWithGoogle == StateLoginWithGoogle.SUCCESS ||
//                                       viewModelAuthenticationUiState.stateSignInUser == StateSignInUser.SUCCESS ||
//                                       viewModelGetReviewsUiState.statechanges == StateChangesOnListReviews.START
//                                       ){
//                                       viewModelGetReviews.result()
//                                       Log.d("FlowDectect", "colector activo en repeatOnLifecycle.State.RESUMED ")
//                                   }
//                                }
//                            }
//                        }
                    }
                   NavigationMainScreens(
                       context = this,
                       viewModelAuthentication = viewModelAuthentication,
                       viewModelUrlsImages = viewModelUrlsImages,
                       viewModelGetReviews = viewModelGetReviews
                   )
                }
            }
        }
    }
    private fun askNotificationPermission() {
        /**
         * verificamos si la versión de android es mayor o igual a la versión 13*/
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if(
                ContextCompat.checkSelfPermission(
                    this, Manifest.permission.POST_NOTIFICATIONS)
                == PackageManager.PERMISSION_GRANTED
                ){

            }else{
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    /**
     * En caso de que el permiso no esté autorizado necesitamos hacer la solicitud de autorización,
     * lo que va hacer es mostrar un diólogo en pantalla preguntando si permitimos o no las
     * notificaciones
     * */
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ){isCranted: Boolean ->
        if(isCranted){

        }else {

        }
    }


    /**
     * Para implementar notificaciones para usarios individuales utilizamos los toquens,
     * necesitamos un listener que se mantenga atendo a la vinculación de nuevos usuarios
     * */
    private fun tokenNew(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if(!task.isSuccessful){
                Log.w("FCM", "Fetching FCM registration token failed", )
                return@OnCompleteListener
            }
            val token= task.result
            Log.d("FCM TOKEN", token.toString())
        })
    }
}
