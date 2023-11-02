package gupsmile.com.data

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.android.internal.Contexts
import dagger.hilt.components.SingletonComponent
import gupsmile.com.data.firebaseManager.AnalitycsManager
import javax.inject.Singleton
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context

@Module
@InstallIn(SingletonComponent::class)
object MyModuleAnalytics {

    @Singleton
    @Provides
    fun provideAnalyticsManagerInstance(
        @ApplicationContext context: Context
    ): AnalitycsManager {
        return  AnalitycsManager(context = context)
    }
}