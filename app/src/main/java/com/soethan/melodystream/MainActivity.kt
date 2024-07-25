package com.soethan.melodystream

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.soethan.melodystream.components.SongListTitle
import com.soethan.melodystream.presentation.ui.theme.MelodyStreamTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
    class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            MelodyStreamTheme {

                val mainViewModel = hiltViewModel<MainViewModel>()
                val context = LocalContext.current
                var permissionDenied by remember { mutableStateOf(false) }
                var permissionPermanentlyDenied by remember { mutableStateOf(false) }


                val permissionLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestPermission(),
                    onResult = { isGranted ->
//                        mainViewModel.onPermissionChanged(isGranted);
                        //mainViewModel.onPermissionChanged(isGranted)
                        if (isGranted) {
                            permissionDenied = false
                        } else {
                            // Check if permission was denied permanently
                            permissionDenied = true
                            permissionPermanentlyDenied =
                                !shouldShowRequestPermissionRationale(Manifest.permission.RECORD_AUDIO)
                        }
                    })

                LaunchedEffect(key1 = true) {

                    mainViewModel.getAudioFiles()
                    when (ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.READ_MEDIA_AUDIO
                    )) {
                        PackageManager.PERMISSION_GRANTED -> {
                            mainViewModel.onPermissionChanged(true);
                            permissionDenied = false
                        }

                        PackageManager.PERMISSION_DENIED -> {
                            permissionLauncher.launch(Manifest.permission.READ_MEDIA_AUDIO)


                        }
                    }
                }
                
               LifecycleEventEffect(event = Lifecycle.Event.ON_RESUME) {
                   when (ContextCompat.checkSelfPermission(
                       context,
                       Manifest.permission.READ_MEDIA_AUDIO
                   )) {
                       PackageManager.PERMISSION_GRANTED -> {
                           mainViewModel.onPermissionChanged(true);
                           permissionDenied = false
                       }


                   }
               }
//
//                DisposableEffect(key1 = lifecycleOwner){
//                    val observer = LifecycleEventObserver { _, event ->
//                        if (event == Lifecycle.Event.ON_RESUME) {
//
//                        }
//
//                    }
//                    lifecycleOwner.lifecycle.addObserver(observer)
//
//
//                    onDispose {
//                        lifecycleOwner.lifecycle.removeObserver(observer)
//                    }
//
//                }


                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (permissionDenied) {
                        if (permissionPermanentlyDenied) {
                            PermissionDialog(
                                permissionTextProvider = AudioPermissionTextProvider(),
                                isPermanentlyDeclined =
                                true,
                                onDismiss = { /*TODO*/ },
                                onOkClick = {
                                    permissionLauncher.launch(
                                        Manifest.permission.READ_MEDIA_AUDIO
                                    )
                                },
                                onGoToAppSettingsClick = { openAppSettings() })
                        } else {
                            PermissionDialog(
                                permissionTextProvider = AudioPermissionTextProvider(),
                                isPermanentlyDeclined =
                                false,
                                onDismiss = { /*TODO*/ },
                                onOkClick = {
                                    permissionLauncher.launch(
                                        Manifest.permission.READ_MEDIA_AUDIO
                                    )
                                },
                                onGoToAppSettingsClick = { openAppSettings() })
                        }
                    } else {
                        SongListTitle(song = generateSampleSongs()[1])
                    }
//                    if ( mainViewModel.isPermissionAllowed.value == true) {
//
//
//                    } else {
//                        PermissionDialog(
//                            permissionTextProvider = AudioPermissionTextProvider(),
//                            isPermanentlyDeclined =
//                            !shouldShowRequestPermissionRationale(
//                                Manifest.permission.READ_MEDIA_AUDIO
//                            ),
//                            onDismiss = { /*TODO*/ },
//                            onOkClick = {
//                                permissionLauncher.launch(
//                                    Manifest.permission.READ_MEDIA_AUDIO
//                                )
//                            },
//                            onGoToAppSettingsClick = { openAppSettings() })
//                    }


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