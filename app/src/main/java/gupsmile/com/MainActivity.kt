package gupsmile.com

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import gupsmile.com.data.temporalConfig.StateChangesOnListReviews
import gupsmile.com.data.temporalConfig.StateGetReviews
import gupsmile.com.data.temporalConfig.StateUpdateListGups
import gupsmile.com.data.temporalConfig.ViewModelGetReviews
import gupsmile.com.data.temporalConfig.ViewModelUrlsImages
import gupsmile.com.data.testWoMa.viewModelShowMessage.ViewModelShowMessage
import gupsmile.com.ui.navigationApp.NavigationMainScreens
import gupsmile.com.ui.subScreens.ChatScreen.emojiTray.mainImeScreen
import gupsmile.com.ui.theme.GupsMileTheme
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.StateLoginAsVisitor
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.StateLoginWithGoogle
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.StateSignInUser
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.ViewModelAuthentication
import gupsmile.com.ui.viewModelPanelControl.viewModelNetwork.StateNetwork
//import gupsmile.com.ui.viewModelPanelControl.viewModelNetwork.StateNetwork
import gupsmile.com.ui.viewModelPanelControl.viewModelNetwork.ViewModelNetwork
import gupsmile.com.ui.viewModelPanelControl.viewModelNetwork.ViewModelStatusNetwork
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("ResourceAsColor", "CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window,false)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        askNotificationPermission()
        tokenNew()

        setContent {
            GupsMileTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModelAuthentication: ViewModelAuthentication = hiltViewModel()
                    val viewModelUrlsImages: ViewModelUrlsImages by viewModels()
                    val viewModelGetReviews: ViewModelGetReviews by viewModels()
                    val viewModelNetwork: ViewModelNetwork by viewModels()
                    val viewModelGetReviewsUiState = viewModelGetReviews.uiState.collectAsState().value
                    val viewModelAuthenticationUiState = viewModelAuthentication.uiState.collectAsState().value
                    val viewModelStatusNetwork : ViewModelStatusNetwork by viewModels()
                    val viewModelShowMessage: ViewModelShowMessage by viewModels()

                    val stateNetwork = {
                        if(viewModelNetwork.isOnline(this)){
                            viewModelNetwork.updateStateNetwork(StateNetwork.ACTIVE)
                        }else{
                            viewModelNetwork.updateStateNetwork(StateNetwork.INACTIVE)
                        }
                    }

                    lifecycleScope.launch {
                        repeatOnLifecycle(Lifecycle.State.STARTED){
                           if(viewModelAuthentication.getCurrentUser() != null){

                               launch {
                                   stateNetwork()
                               }

                               launch {
                                   viewModelUrlsImages.result.collect {
                                       viewModelUrlsImages.updateUrlsImages(result = it)
                                       Log.d("FlowDectect", "Collector activo UrlsIMages")
                                   }
                               }
                               launch {

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
                                               Log.d("FlowDectect", "colector activo dentro del controlador de estado")
                                               viewModelGetReviews.result()
                                           }
                                           if(viewModelGetReviewsUiState.stateUpdateListGups == StateUpdateListGups.DONE ||
                                               viewModelGetReviewsUiState.stateUpdateListGups == StateUpdateListGups.VERIFYAGAIN){
                                               Log.d("FlowDectect", "validación de escritura de datos en el backend")
                                               viewModelGetReviews.validateWritingInBackend()
                                           }
                                       }
                                   }
                               }
                           }
                        }
                    }


                   NavigationMainScreens(
                       context = this,
                       viewModelAuthentication = viewModelAuthentication,
                       viewModelUrlsImages = viewModelUrlsImages,
                       viewModelGetReviews = viewModelGetReviews,
                       viewModelNetwork = viewModelNetwork,
                       viewModelStatusNetwork = viewModelStatusNetwork,
                       viewModelShowMessage = viewModelShowMessage
                   )

//                    Column(
//                        modifier = Modifier
//                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
//                            .fillMaxWidth()
//                            .fillMaxHeight(),
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.Bottom){
//                        EmojiTray()
//                    }

//                    ImeDetection()
//                    EmojiTray()
//                    proofEmojis()
//                    GridEmojis()

                  //  MyScrollableScreen()

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
