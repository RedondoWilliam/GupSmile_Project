package gupsmile.com.data.testWoMa

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.asFlow
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import dagger.hilt.android.qualifiers.ApplicationContext
import gupsmile.com.data.testWoMa.workers.ShowMessage
import gupsmile.com.network.isNetworkActive
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

class TestWomaRepository @Inject constructor(
    private val workManager: WorkManager,
    private val connectivityManager: ConnectivityManager
): TestWomaRepositoryIn {


     private val workRequestId= MutableSharedFlow<UUID>(replay = 1)


    @OptIn(ExperimentalCoroutinesApi::class)
    override val outputWorkInfo: Flow<WorkInfo> =
        workRequestId
            .flatMapLatest {workId ->
                Log.d("ShowMessage", "transformación de flujo  $workId")
                workManager.getWorkInfoByIdLiveData(workId)
                    .asFlow()
                    .filterNotNull()
                    .also { Log.d("ShowMessage", "Convirtiendo LiveData a Flow para $workId") }
            }




    override suspend fun showMessage() {

        val showMessageBuilder =
            OneTimeWorkRequestBuilder<ShowMessage>()
                .addTag(TAG_OUTPUT_)
                .build()

        val workId = showMessageBuilder.id
        workManager.enqueue(showMessageBuilder)
        workRequestId.emit(workId)
        Log.d("ShowMessage", "dato emitido al flujo $workId")

    }

    override suspend fun validadeStateNetowrk() = isNetworkActive(connectivityManager = connectivityManager )



    override fun cancelwork() {

    }
}


