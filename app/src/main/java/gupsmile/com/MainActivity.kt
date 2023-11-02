package gupsmile.com

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import gupsmile.com.ui.navigationApp.NavigationMainScreens
//import gupsmile.com.ui.navigationApp.navigationAppPanelControl.NavigationAppPanelControl
import gupsmile.com.ui.theme.GupsMileTheme
import gupsmile.com.ui.viewModelPanelControl.viewModelAnalytics.ViewModelAnalytics
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.ViewModelAuthentication


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GupsMileTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModelAuthentication: ViewModelAuthentication = viewModel()
                   NavigationMainScreens(
                       context = this,
                       viewModelAuthentication = viewModelAuthentication
                   )
                }
            }
        }
    }
}
