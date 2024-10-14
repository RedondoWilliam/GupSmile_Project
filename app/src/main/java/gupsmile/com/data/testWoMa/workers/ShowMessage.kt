package gupsmile.com.data.testWoMa.workers

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import gupsmile.com.data.testWoMa.ERROR
import gupsmile.com.data.testWoMa.FAILURE
import gupsmile.com.data.testWoMa.SHOW_MESSAGE
import gupsmile.com.data.testWoMa.SUCCESS
import gupsmile.com.network.isNetworkActive
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.internal.wait
import javax.inject.Inject

private const val TAG = "ShowMessage"


class ShowMessage @AssistedInject constructor(
    @Assisted ctx: Context,
    @Assisted params: WorkerParameters,
    private val connectivityManager: ConnectivityManager
): CoroutineWorker(ctx, params){


    override suspend fun doWork(): Result {


        return withContext(Dispatchers.IO){
            return@withContext try {

                val statusNetwork = isNetworkActive(connectivityManager = connectivityManager )

                val outputData: Data =
                    if(statusNetwork){
                        workDataOf(SHOW_MESSAGE to SUCCESS)
                    }else{
                        workDataOf(SHOW_MESSAGE to FAILURE)
                    }

                Log.e("ShowMessage", "el dato ha sigo enviado ${outputData.getString(SHOW_MESSAGE)} ")
                Result.success(outputData)

            }catch (throwable: Throwable){
                Log.e(
                    TAG,
                    "Error Testing status Network",
                    throwable
                )
                val outputDataError = workDataOf(SHOW_MESSAGE to ERROR)
                Log.e("ShowMessage", "el dato ha sigo enviado ${outputDataError.getString(SHOW_MESSAGE)} ")
                Result.failure(outputDataError)

            }
        }


    }
}