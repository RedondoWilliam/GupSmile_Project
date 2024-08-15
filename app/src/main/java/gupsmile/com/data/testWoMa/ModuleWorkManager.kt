package gupsmile.com.data.testWoMa

import android.content.Context
import android.net.ConnectivityManager
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.WorkManager
import com.google.firebase.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import gupsmile.com.data.ModuleServices
import gupsmile.com.data.firebaseManager.AnalitycsManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleWorkManager {

    @Singleton
    @Provides
    fun provideWorkManagerInstance(
        @ApplicationContext context: Context
    ): WorkManager {
        return  WorkManager.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideConectivityManagerInstance(
        @ApplicationContext context: Context
    ):ConnectivityManager{
        return  context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Provides
    @Singleton
    fun provideWorkManagerConfiguration(
        workerFactory: HiltWorkerFactory
    ):Configuration{
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }



}