package biz.ei6.eluvplages


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import biz.ei6.eluvplages.screens.AppNav3

import biz.ei6.eluvplages.ui.theme.PlagesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlagesTheme (darkTheme = true){
               AppNav3( org.koin.androidx.compose.koinViewModel())
            }
        }
    }
}
