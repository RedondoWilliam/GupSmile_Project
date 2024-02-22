package gupsmile.com.data.firebaseManager

import android.content.Context
import android.net.Uri
import android.util.Log
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import gupsmile.com.data.firebaseService.FirebaseCloudStorageService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.util.concurrent.Flow
import javax.inject.Inject

class CloudStorageManager @Inject constructor(
    private val context: Context,
    private val authManager: AuthManager,
    private val storage: FirebaseStorage
): FirebaseCloudStorageService{
    override val storageRef = storage.reference
    /**
     * necesitamos asociar la uid del usuario únicamente registrado
     * */
    override val userId = authManager.getCurrentUser()?.uid

    /**
     * construimos una referencia a un child en storage, que si no existe lo va a crear y que también
     * me acceda a un child que está definifo por el userId, y de la misma forma, si no existe, lo va
     * a crear, también que si no existe va a ser vacío.*/
    override fun getStorageReference(): StorageReference {
        return storageRef.child("photos").child(userId ?: "")
    }

    /**
     * filePath -> es la ruta donde se encuentra el archivo en el dispositivo
     * */
    override suspend fun uploadFile(fileName: String, filePath: Uri){
        /**
         * creamos una referencia del archivo de donde vayamos a almacenarlo, estamos anidando
         * otra child en la referencia de getStorageReference
         * */
        val fileRef = getStorageReference().child(fileName)
        /**
         * definiomos la tarea que se va a desarrollar que es de poner o subir un archivo
         * */
        val uploadTask = fileRef.putFile(filePath)
        uploadTask.await()

    }

    /**
     *obtenemos una lista con las urls de las imágenes para poder descargarlas, para poder mostrarlas
     * utilizando coil
     * */
    override  suspend fun getUserImages(): List<String>{
        val imageUrls = mutableListOf<String>()
        /**
         * obtenemos un listado de todos los elementos contenidos en getStorageReference de manera
         * asíncrona, ListResult es un objeto Firebase
         * */
        val listResult: ListResult = getStorageReference().listAll().await()
        for(item in listResult.items){
            val url = item.downloadUrl.await().toString()
            imageUrls.add(url)
        }
        return imageUrls
    }

    fun getUserImagesTwo()  = flow<List<String>> {
        val imageUrls = mutableListOf<String>()
        val listResult: ListResult = getStorageReference().listAll().await()
        for(item in listResult.items){
            val url = item.downloadUrl.await().toString()
            imageUrls.add(url)
        }

        emit(imageUrls)
        Log.d("FlowDectect", "Productor activo Url Images")
    }




}