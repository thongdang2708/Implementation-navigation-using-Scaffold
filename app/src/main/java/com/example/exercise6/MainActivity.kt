package com.example.exercise6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.exercise6.ui.theme.Exercise6Theme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Exercise6Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScaffoldApp();
                }
            }
        }
    }
}

@Composable
fun MainTopBar (title : String, navController: NavController) {

    var expanded = remember {
        mutableStateOf(false);
    }

    TopAppBar(
        title = { Text(text = "Top App Bar")},
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = { expanded.value = !expanded.value }) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = null)
            }
            DropdownMenu(expanded = expanded.value, onDismissRequest = { expanded.value = false }) {
                DropdownMenuItem(onClick = { navController.navigate("info") }) {
                    Text("Info")
                }
                DropdownMenuItem(onClick = { navController.navigate("settings") }) {
                    Text("Settings")
                }
            }
        }
    )
}

@Composable
fun ScreenTopBar (title : String, navController: NavController) {
    TopAppBar(
        title = { Text(title)},
        navigationIcon = {
            IconButton(onClick = {navController.navigateUp()}) {
                 Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
            }
        }
        
    )
}

@Composable
fun MainScreen (navController: NavController) {
    Scaffold(
        topBar = { MainTopBar(title = "My App", navController)},
        content = { Text(text = "Content for Home Screen")}
    )
}

@Composable
fun InfoScreen (navController : NavController) {
    Scaffold(
        topBar = { ScreenTopBar(title = "Info", navController = navController)},
        content = { Text(text = "Content for Info Screen")}
    )
}

@Composable
fun SettingsScreen (navController: NavController) {
    Scaffold(
        topBar = { ScreenTopBar(title = "Settings", navController = navController)},
        content = { Text(text = "Content for Settings screen")}
    )
}

@Composable
fun ScaffoldApp () {
    val navController = rememberNavController();
    
    NavHost(
        navController = navController,
        startDestination = "Home"
    ) {
        composable(route = "Home") {
            MainScreen(navController)
        }

        composable(route = "Info") {
            InfoScreen(navController)
        }

        composable(route = "Settings") {
            SettingsScreen(navController)
        }
        
    }
    
    
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Exercise6Theme {
        ScaffoldApp();
    }
}