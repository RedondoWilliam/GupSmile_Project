package gupsmile.com

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import gupsmile.com.data.AppContainer
import gupsmile.com.data.DefaultAppContainer
import javax.inject.Inject


@HiltAndroidApp
class GupSmileApp: Application(), Configuration.Provider{




    @Inject
    lateinit var workerConfiguration: Configuration



    /**
     * establecemos una configuración para versiones de android superiores a Oreo
     * */
    companion object{
        const val FCM_CHANNEL_ID = "FCM_CHANNEL_ID"
    }

    override fun onCreate() {
        super.onCreate()

        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            val fcmChannel = NotificationChannel(
                FCM_CHANNEL_ID, "FCM_Channel", NotificationManager.IMPORTANCE_HIGH
            )
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            /**
             * cramos el canal de notificaciones pero ahora a nivel de aplicación,
             * teniendo en cuenta los permisos en tiempo real, las versiones 13 o superiores de
             * android  cambian el manejo de las notificaciones, dado que su funcionalidad también
             * se unió a los permisos en tiempo real, por lo que se tiene que solicitar un permiso
             * en tiempo real
             *
             *
             * */
            manager.createNotificationChannel(fcmChannel)

        }
        

    }



    override val workManagerConfiguration: Configuration
    get() = workerConfiguration



}



