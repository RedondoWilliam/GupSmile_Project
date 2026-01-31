package gupsmile.com.data.testWoMa

import androidx.work.WorkInfo
import kotlinx.coroutines.flow.Flow

interface TestWomaRepositoryIn {
    val outputWorkInfo: Flow<WorkInfo>



     suspend fun showMessage()


     /**
      * valida el estado de red sin la necesidad de usar un worker, para los casos en los que
      * necesitemos sabes el estado de red ùnicamente cuando estemos con la app en primer plano
      * */
     suspend fun validadeStateNetowrk():Boolean

    fun cancelwork()
}