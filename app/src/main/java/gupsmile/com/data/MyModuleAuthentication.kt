package gupsmile.com.data

import android.content.Context
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import gupsmile.com.data.firebaseManager.AuthManager
import gupsmile.com.data.firebaseManager.CloudStorageManager
import gupsmile.com.data.firebaseManager.FireStoreManager
import gupsmile.com.data.firebaseManager.RealTimeManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MyModuleAuthentication {

    @Singleton
    @Provides
    fun providesAutheticationManagerInstance(
        @ApplicationContext context: Context
    ): AuthManager{
        return AuthManager(
            context,
            auth = ModuleServices.provideFirebaseAuthenticationInstance(context)
        )
    }

    @Singleton
    @Provides
    fun providesRealTimeManagerInstance(
        @ApplicationContext context: Context
    ): RealTimeManager {
        return RealTimeManager(
            context = context,
            authManager = AuthManager(
                context,
                auth = ModuleServices.provideFirebaseAuthenticationInstance(context)
            )
        )
    }

    @Singleton
    @Provides
    fun provideFireStoreManagerInstance(
        @ApplicationContext context: Context
    ): FireStoreManager{
        return FireStoreManager(
            context = context,
            authManager = AuthManager(
                context,
                auth = ModuleServices.provideFirebaseAuthenticationInstance(context)
            ),
            fireStore = ModuleServices.provideFirebaseStoreInstance()
        )
    }

    @Singleton
    @Provides
    fun provideCloudStorageManagerInstance(
        @ApplicationContext context: Context
    ): CloudStorageManager{
        return CloudStorageManager(
            context = context,
            authManager = AuthManager(
                context,
                auth = ModuleServices.provideFirebaseAuthenticationInstance(context)
            ),
            storage = ModuleServices.provideFirebaseCloudStorageInstance()
        )
    }

    @Singleton
    @Provides
    fun provideFireBaseRemoteConfigInstance(): FirebaseRemoteConfig{
        return FirebaseRemoteConfig.getInstance()
    }
}