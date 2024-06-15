package gupsmile.com.data.repositories

import android.content.Context
import androidx.work.WorkInfo
import androidx.work.WorkManager
import gupsmile.com.workers.TAG_OUTPUT_STATUS_NETWORK
import kotlinx.coroutines.flow.Flow
import androidx.lifecycle.asFlow
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import gupsmile.com.workers.NetworkStatusWorker
import gupsmile.com.workers.VERIFY_STATUS_NETWORK_WORK_NAME
import kotlinx.coroutines.flow.mapNotNull
import java.time.Duration
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.time.toDuration


class WMNetworkStatusRepository @Inject constructor( @ApplicationContext context: Context): NetworkStatusRepository {

    private val workManager = WorkManager.getInstance(context)
    override val outputWorkInfo: Flow<WorkInfo> =
        workManager.getWorkInfosByTagLiveData(TAG_OUTPUT_STATUS_NETWORK).asFlow().mapNotNull {
            if(it.isNotEmpty()) it.first() else null
        }


    override fun listenableStatusNetwork() {


        val periodicWorkRequest =
            PeriodicWorkRequestBuilder<NetworkStatusWorker>(Duration.ofSeconds(5))
                .addTag(TAG_OUTPUT_STATUS_NETWORK)



        workManager.enqueueUniquePeriodicWork(
            VERIFY_STATUS_NETWORK_WORK_NAME,
            ExistingPeriodicWorkPolicy.UPDATE,
            periodicWorkRequest.build()
        )
        workManager.enqueue(periodicWorkRequest.build())

    }
}