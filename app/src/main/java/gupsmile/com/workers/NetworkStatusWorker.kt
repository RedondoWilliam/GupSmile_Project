package gupsmile.com.workers

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import gupsmile.com.network.isNetworkActive
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val TAG = "StatusNetworkWorker"
const val KEY_STATUS_NETWORK = "KEY_STATUS_NETWORK"
const val TAG_OUTPUT_STATUS_NETWORK = "OUTPUT"
const val VERIFY_STATUS_NETWORK_WORK_NAME = "verify_status_network"
class NetworkStatusWorker(ctx: Context, params:WorkerParameters): CoroutineWorker(ctx, params) {

    private val connectivityManager = ctx.getSystemService(Context.CONNECTIVITY_SERVICE)
            as ConnectivityManager
    override suspend fun doWork(): Result {

        return withContext(Dispatchers.IO){
            return@withContext try {
                val networkStatus = isNetworkActive(connectivityManager)
                val outputData = workDataOf(KEY_STATUS_NETWORK to networkStatus)
                Log.e(TAG, "exitoso")
                Result.success(outputData)

            }catch (throwable: Throwable){
                Log.d(
                    TAG,
                    throwable.message.toString()
                )
                Result.failure()
            }
        }
    }
}


