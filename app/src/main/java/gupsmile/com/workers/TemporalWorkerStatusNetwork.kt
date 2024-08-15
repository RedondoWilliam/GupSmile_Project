package gupsmile.com.workers

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.core.content.getSystemService
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import gupsmile.com.network.isNetworkActive
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


private const val TAG = "TemporalStatusNetworkWorker"
const val KEY_TEMPORAL_STATUS_NETWORK = "TEMPORAL_OUTPUT"
class TemporalWorkerStatusNetwork(
    ctx: Context, params: WorkerParameters
): CoroutineWorker(ctx, params) {
    private val conectivityManager = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO){
            return@withContext try {
                val statusNetwork = isNetworkActive(conectivityManager)
                val outputData = workDataOf(KEY_TEMPORAL_STATUS_NETWORK to statusNetwork)
                Log.e(TAG, "conección de red activa")
                Result.success(outputData)
            }catch (throwable: Throwable){
                Log.d(TAG, "conección de red inactiva, error: ${throwable.message.toString()}")
                Result.failure()
            }
        }
    }
}