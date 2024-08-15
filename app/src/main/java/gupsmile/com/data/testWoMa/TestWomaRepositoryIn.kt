package gupsmile.com.data.testWoMa

import androidx.work.WorkInfo
import kotlinx.coroutines.flow.Flow

interface TestWomaRepositoryIn {
    val outputWorkInfo: Flow<WorkInfo>
     suspend fun showMessage()

    fun cancelwork()
}