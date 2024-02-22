package gupsmile.com.data

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import gupsmile.com.data.firebaseManager.AnalitycsManager
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleServices{
    @Singleton
    @Provides
    fun provideFirebaseAnalyticsInstance(
        @ApplicationContext context: Context
    ): FirebaseAnalytics{
        return FirebaseAnalytics.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideFirebaseAuthenticationInstance(
        @ApplicationContext context: Context
    ): FirebaseAuth{
        return Firebase.auth
    }

    @Singleton
    @Provides
    fun provideFirebaseCloudStorageInstance():FirebaseStorage{
        return Firebase.storage
    }

    @Singleton
    @Provides
    fun provideFirebaseStoreInstance(): FirebaseFirestore{
        return Firebase.firestore
    }

    @Singleton
    @Provides
    fun provideFirebaseRealTimeDataBaseInstance(): DatabaseReference {
        return   FirebaseDatabase.getInstance().reference
    }
}
