package gupsmile.com.data.repositories

import android.content.Context
import androidx.lifecycle.asFlow
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import gupsmile.com.workers.TemporalWorkerStatusNetwork
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull


const val TAG_OUTPUT = "OUTPUT"

class WMTemporalStatusNetworkRepository(context: Context): TemporalNetworkStatusRepository {


    private val workManager = WorkManager.getInstance(context)
    override val outputWorkInfo: Flow<WorkInfo> =
        workManager.getWorkInfosByTagLiveData(TAG_OUTPUT)
            .asFlow()
            .mapNotNull {
                if(it.isNotEmpty()) it.first() else null
            }

    override fun listenableStatusNetwork() {
        val statusNetworkBuilder = OneTimeWorkRequestBuilder<TemporalWorkerStatusNetwork>()
        workManager.enqueue(statusNetworkBuilder.build())
    }
}