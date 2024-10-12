package com.soethan.melodystream

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.navigation.compose.rememberNavController
import com.soethan.melodystream.presentation.components.AlbumTile
import com.soethan.melodystream.presentation.components.ArtistTile
import com.soethan.melodystream.presentation.components.FavoritePlayListTile
import com.soethan.melodystream.presentation.components.MelodyNavDrawer
import com.soethan.melodystream.presentation.components.SongListTitle
import com.soethan.melodystream.presentation.model.permission.PermissionModel
import com.soethan.melodystream.presentation.navigation.AppMainNavigation
import com.soethan.melodystream.presentation.screens.MainScreen
import com.soethan.melodystream.presentation.screens.PermissionHandler
import com.soethan.melodystream.theme.MelodyStreamTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            MelodyStreamTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    var permissionsGranted by remember { mutableStateOf(false) }


                    PermissionHandler(
                        permissions = listOf(
                            PermissionModel(
                                permission = "android.permission.READ_MEDIA_AUDIO",
                                maxSDKVersion = Int.MAX_VALUE,
                                minSDKVersion = 33,
                                rational = "Access to audios is required"
                            )
                        ), askPermission = true,
                        allPermissionGranted = {
                            permissionsGranted = true
                        }

                    )

                    if (permissionsGranted)
                        AppMainNavigation(navController = navController)


                }


            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MelodyStreamTheme {
        Greeting("Android")
    }
}

fun Activity.openAppSettings() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    ).also(::startActivity)
}