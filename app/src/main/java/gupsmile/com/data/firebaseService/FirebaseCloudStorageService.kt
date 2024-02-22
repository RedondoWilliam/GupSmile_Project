package gupsmile.com.data.firebaseService

import android.net.Uri
import com.google.firebase.storage.StorageReference

interface FirebaseCloudStorageService {
    val storageRef:StorageReference
    val userId: String?
    fun getStorageReference(): StorageReference
    suspend fun uploadFile(fileName: String, filePath: Uri)
    suspend fun getUserImages(): List<String>
}