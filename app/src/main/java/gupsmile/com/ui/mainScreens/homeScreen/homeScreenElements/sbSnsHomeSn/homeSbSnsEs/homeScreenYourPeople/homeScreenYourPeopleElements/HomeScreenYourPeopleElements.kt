package gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.homeSbSnsEs.homeScreenYourPeople.homeScreenYourPeopleElements

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.crashlytics.ktx.setCustomKeys
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ConfigUpdate
import com.google.firebase.remoteconfig.ConfigUpdateListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.firebase.remoteconfig.ktx.get
import com.google.firebase.remoteconfig.ktx.remoteConfig
import gupsmile.com.R
import gupsmile.com.data.MyModuleAuthentication
import gupsmile.com.ui.commonElements.BottomItemDesignFixed
import gupsmile.com.ui.theme.GupsMileTheme

lateinit var mFirebaseRemoteConfig: FirebaseRemoteConfig
val fireBaseConfig = MyModuleAuthentication.provideFireBaseRemoteConfigInstance()
var welcomeMessage: String by mutableStateOf("esta es la pantalla de tu gente")
var isButtonVisible by mutableStateOf(true)

val WELCOME_MESSAGE_KEY = "welcome_message"
val IS_BUTTON_VISIBLE_KEY = "is_button_visible"

@SuppressLint("UnrememberedMutableState")
@Composable
fun HomeScreenYourPeopleElements(
    modifier: Modifier = Modifier
){    Column(
    modifier = modifier.fillMaxSize(),
    verticalArrangement =  Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {


    InitRemoteConfig()

    Text(text = welcomeMessage)


    val context = LocalContext.current
    val user = MyModuleAuthentication.providesAutheticationManagerInstance(context).getCurrentUser()


    Spacer(modifier = modifier.height(30.dp))
    if(isButtonVisible){
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            BottomItemDesignFixed(
                bottomActions = {

                    val crashlytics = FirebaseCrashlytics.getInstance()
                    crashlytics.setCustomKey("PruebaClave", "valor de la prueba clave")
                    crashlytics.log("Mensaje personalizado desde un log")
                    crashlytics.setUserId(user?.uid ?: "No Id Found")
                    crashlytics.setCustomKeys {
                        key("str", "hello")
                        key("bool", true)
                        key("int", 5)
                        key("long", 5.8)
                    }

                    throw RuntimeException("Error forzado desde HomeScreenYourPeopleElements")
                },
                padddingStart = 5.dp,
                paddingEnd = 5.dp,
                widthFixed = 220.dp ,
                textBottonName = R.string.force_error
            )
        }
    }
}
}


fun InitRemoteConfig() {
    mFirebaseRemoteConfig = Firebase.remoteConfig
    /**
     * Necesitamos configurar una frecuencia de actualización, en este caso estamos dando un tiempo
     * de actualización de 1 hora, por lo que no debería tardar más de una hora en actualizar en
     * todos los usuarios, build se encarga de construir la configuración.
     * */
    val configSettings: FirebaseRemoteConfigSettings = FirebaseRemoteConfigSettings.Builder()
        .setMinimumFetchIntervalInSeconds(3600)
        .build()
    /**
     *  Una vez que tenemos la configuración lista, tenemos que asignarla a la instancia de remote
     *  config.*/
    mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings)

    /**
     * En caso de que no se tenga conexión a internet es bueno tener un parámetro de manera local
     * un parámetro  con las mismas funciones que remote config, y que al momento en que se habilite
     * la conexión a internet se actualice a la configuración de remote config
     * agregamos la configuración con el archivo xml en caso de que no tengamos conexión a internet
     * usando setDefaultsAsync()
     * */
    mFirebaseRemoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)

    /**
     * construimos un listener para manejar las actualizaciones de configuración que nosotros tengamos
     * */
    mFirebaseRemoteConfig.addOnConfigUpdateListener(object : ConfigUpdateListener{
        override fun onUpdate(configUpdate: ConfigUpdate){
            Log.d("HomeScreen", "Updated Keys:" + configUpdate.updatedKeys)
            if(
                configUpdate.updatedKeys.contains(IS_BUTTON_VISIBLE_KEY)
                || configUpdate.updatedKeys.contains(WELCOME_MESSAGE_KEY)
                ){
                mFirebaseRemoteConfig.activate().addOnCompleteListener{
                    displayWelcomeMessage()
                }
            }
        }
        override fun onError(error: FirebaseRemoteConfigException){

        }
    })

    fetchWelcome()

}


/**
 *Función extra que nos permite hacer una solitud para recuperar las configuraciones y los parámetros
 * desde firabase remote config
 * fetchWelcome activa la configuración con los parámetros que hemos recibido
 * */
fun fetchWelcome(){
    /**
     * obtenemos la actualización constante que posamos recibir de remote config,
     * */
    mFirebaseRemoteConfig.fetchAndActivate()
        .addOnCompleteListener { task ->
            if(task.isSuccessful){
                val updated  = task.result
                println("Parámetros actualizados: $updated")
            }else{
                println("Fetch failed")
            }
        }
}

fun displayWelcomeMessage(){
    welcomeMessage = mFirebaseRemoteConfig[WELCOME_MESSAGE_KEY].asString()
    isButtonVisible = mFirebaseRemoteConfig[IS_BUTTON_VISIBLE_KEY].asBoolean()
}

@Composable
@Preview(showBackground = true)
fun HomeScreenYourPeopleElementsPreview(){
    GupsMileTheme {
        HomeScreenYourPeopleElements()
    }
}