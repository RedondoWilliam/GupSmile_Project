package gupsmile.com

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlin.random.Random

/**
 * se va a encargar de controlar cualquier cambio o notificación del toquen que se vaya gernerar por
 * este dispositivo
 * */
class MyFirebaseService: FirebaseMessagingService() {

    /**
     * nos va a permitir generar identificadores random para nuestras notificaciones */
    private val random = Random

    /**
     * se va a llamar cuando se reciba un nuevo mensaje de firebaseMessaging
     * */
    override fun onMessageReceived(message: RemoteMessage) {
        message.notification?.let {message ->
            Log.i("FCM Title", "${message.title}")
            Log.i("FCM Body", "${message.body}")
            sendNotification(message)
        }
    }

    /**
     * se va encargar de mostrar la  notificación en la parte de arriba
     * */
    private fun sendNotification(message: RemoteMessage.Notification) {
        /**
         * se deseamos que las notificaciones aparezcan en primer plano:
         * cuando hagamos click en la notificación que navegue mainActivity
         * */
        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(FLAG_ACTIVITY_CLEAR_TOP)
        }

        /**
         * Si queremos envolver nuestro destino de navegación dentro de un intent, podemos utilizar
         * pendingIntent. Para que Intent pueda funcionar fuera de la aplicación necesitamos un
         * pendingIntent */
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent, FLAG_IMMUTABLE
        )

        /**
         *Necesitamos construir la configuración para nuestra notificación, para ello, para las
         * últimas versiones de android, necesitamos crear un canal de notificación o los channel que
         * se usan para notificaciones en android y para lo cual necesitamos darle un nombre
         * especifico o un identificador cualquiera. creamos nuestra notificación con
         * NotificationCompact, inicialmente la contruimos con el contexto y el id del canal
         * -es siempre recomendable utilizar íconos vectorizados
         * -este canal de noficaciones es solo para versiones de android OREO o superiores
         *
         * */
        val channelId = this.getString(R.string.default_notification_channel_id)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setContentTitle(message.title)
            .setContentText(message.body)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSmallIcon(R.drawable.icon_google)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        /**
         * Ahora necesitamos accder al administrador de notificaciones del sistema, utilizando
         * la clase NotificationManager
         * */
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        /**
         * neesitamos realizar una validación de la versión de android del dispositivo,
         * verificamos si el sdk del dispositivo donde se esté ejecutando la app es mayor o igual a
         * la versión o, o también llamada Oreo,
         * para versiones menores a Oreo usamos .notify()
         * */
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(channelId, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel)
        }
        manager.notify(random.nextInt(), notificationBuilder.build())




    }

    /**
     * cuando se instalé la app en un nuevo dispositivo onNewToken se encargará de generar un nuevo
     * token para el dispositivo, de tal forma que quede registrado en el proyecto de firebase,
     * ocurre cuando se ejecuta la aplicación por primera vez. El toquen nos puede servir para
     * envíar notificaciones específicas a un usario específica o a un grupo de usuarios específicos
     * */
    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    companion object{
        const val CHANNEL_NAME = "FCM notification channel"
    }
}
