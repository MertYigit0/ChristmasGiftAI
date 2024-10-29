package com.mertyigit0.christmasgiftai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.mertyigit0.christmasgiftai.ui.theme.ChristmasGiftAITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChristmasGiftAITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                   // BakingScreen()
                    GiftDrawScreen(
                        participants = listOf("Mert", "Can", "Kemal", "Ayşe")
                    )
                }
            }
        }
    }
}