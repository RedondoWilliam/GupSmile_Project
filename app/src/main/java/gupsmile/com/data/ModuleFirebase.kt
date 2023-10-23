package gupsmile.com.data

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
//import dagger.hilt.android.internal.Contexts
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import gupsmile.com.data.firebaseManager.AnalitycsManager
import gupsmile.com.data.firebaseManager.AuthManager
import org.intellij.lang.annotations.PrintFormat
import javax.inject.Singleton
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context

@Module
@InstallIn(SingletonComponent::class)
object MyModule {

    @Singleton
    @Provides
    fun provideAnalyticsManagerInstance(
        context: Context
    ): AnalitycsManager {
        return  AnalitycsManager(context = context)
    }
}