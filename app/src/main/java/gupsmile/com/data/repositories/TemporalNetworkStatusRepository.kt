package gupsmile.com.data.repositories

import androidx.work.WorkInfo
import kotlinx.coroutines.flow.Flow

interface TemporalNetworkStatusRepository {
    val outputWorkInfo: Flow<WorkInfo>
    fun listenableStatusNetwork(){

    }
}