package com.rk_softwares.e_commerce.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rk_softwares.e_commerce.activity.ui.theme.E_commerceTheme

class Act_login : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            E_commerceTheme {

                FullScreen()

            }
        }
    }//on create===========================================
}//class===================================================


@Preview(showBackground = true)
@Composable
private fun FullScreen() {

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

    }//scaffold

}//fun end

