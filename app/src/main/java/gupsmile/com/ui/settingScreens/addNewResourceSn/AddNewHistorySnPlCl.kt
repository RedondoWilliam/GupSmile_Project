package gupsmile.com.ui.settingScreens.addNewResourceSn

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.NavHostController
import gupsmile.com.data.MyModuleAuthentication
import gupsmile.com.ui.navigationApp.RoutesMainScreens
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects

@Composable
fun AddNewHistorySnPlCl(
    modifier: Modifier = Modifier,
    navController: NavHostController
){
    val arroBackTopBottom: () -> Unit = {
        navController.navigate(route = RoutesMainScreens.HomeScreen.route)
    }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val storage = MyModuleAuthentication.provideCloudStorageManagerInstance(context)

    /**
     * creamos un archivo con el nombre de la extensión y también definir donde se va a encontrar
     * específicamente la ruta, una vez tengamos esos valores se situa en ese lugar, en este caso,
     * una vez que saquemos la fotografía.*/
    val file = context.createImageFile()

    /**
     * creanis un uri que have uso de la clase FileProvider de android para obtener un uri que
     * representa un archivo en el sistema de archivos del dispositivo*/
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        "gupsmile.com" + ".provider", file
    )

    /**
     * creamos una variable  que controle el estado de la imagen que estamos tomando con la cámara
     * */
    var captureImageUri by remember{ mutableStateOf<Uri>(Uri.EMPTY) }


    /**
     * En caso de que el permiso para tomar la fotografía sea autorizado lanzamos una actividad para
     * tomar la fotografía, con rememberLaucherForActivityResult que nos devuelve un booleano*/
    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ){
        if(it){
            Toast.makeText(context, "Foto tomada", Toast.LENGTH_SHORT).show()
            captureImageUri = uri
            captureImageUri.let { uri ->
                scope.launch {
                    storage.uploadFile(file.name, uri)
                }
            }

        }else{
            Toast.makeText(context, "No se pudo tomar la foto $it", Toast.LENGTH_SHORT).show()

        }
    }

    /**
     * Necesitamos abrir un cuadro de dialogo en donde se pregunta si se permite o nó el uso de la
     * cámara, el resultado es un valor booleano.
     * */
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){
        if(it){
            Toast.makeText(context, "Permiso autorizado", Toast.LENGTH_SHORT).show()
            cameraLauncher.launch(uri)
        }else{
            Toast.makeText(context, "Permiso denegado", Toast.LENGTH_SHORT).show()
        }
    }

    val takePhotoActionBottom: () -> Unit = {
        /**
         * Necesitamos verificar si tenemos el permiso habilitado o no para el sercivio de cámara
         * */
        val permissionCheckResult =
            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
        if(permissionCheckResult == PackageManager.PERMISSION_GRANTED){
            /**Activamos el servicio de fotografía*/
            cameraLauncher.launch(uri)
        }else{
            /**
             * En caso de que el permiso no esté habilitado, necesitamos contruir una función que
             * nor permita preguntar al usuario si permite el acceso a la cámara
             * */
            permissionLauncher.launch(Manifest.permission.CAMERA)

        }
    }

    AddNewResourceSn(
        titleHistoryText = "",
        titleHistoryOnTextChange = {},
        contentHistoryText = "",
        contentHistoryOnTextChange = {} ,
        takePhotoActionBottom = { takePhotoActionBottom() },
        doneBottomAction = {},
        showNewPhoto = {},
        arrowBackTopBottom = arroBackTopBottom
    )
}

/**creamos un archivo configurando su nombre y extensión*/
fun Context.createImageFile(): File {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    return File.createTempFile(
        imageFileName,
        ".jpg",
        externalCacheDir
    )
}