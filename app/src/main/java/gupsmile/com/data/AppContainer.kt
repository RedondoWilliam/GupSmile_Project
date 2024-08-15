package gupsmile.com.data

import android.content.Context
import gupsmile.com.data.repositories.TemporalNetworkStatusRepository
import gupsmile.com.data.repositories.WMTemporalStatusNetworkRepository

interface AppContainer {
    val temporalNetworkStatusRepository:  TemporalNetworkStatusRepository
}

class DefaultAppContainer(context: Context): AppContainer{
    override val temporalNetworkStatusRepository = WMTemporalStatusNetworkRepository(context)

}