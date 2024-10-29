package com.mertyigit0.christmasgiftai

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun GiftDrawScreen(participants: List<String>) {
    var currentIndex by remember { mutableStateOf(0) }
    val pairs = remember { generateGiftPairs(participants) }
    var showEnvelope by remember { mutableStateOf(true) }

    if (currentIndex < participants.size) {
        val currentPerson = participants[currentIndex]
        val giftee = pairs[currentPerson]

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "$currentPerson'in hediye alacağı kişi...",
                //style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (showEnvelope) {
                // Kapalı Zarf Görseli
                Image(
                    painter = painterResource(id = R.drawable.baked_goods_1),
                    contentDescription = "Kapalı Zarf",
                    modifier = Modifier
                        .size(150.dp)
                        .clickable { showEnvelope = false }
                )
            } else {
                // Açık Zarf - Hediyeyi Alacak Kişi
                if (giftee != null) {
                    Text(
                        text = giftee,
                        // style = MaterialTheme.typography.h4,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = {
                showEnvelope = true
                currentIndex++
            }) {
                Text(text = if (currentIndex < participants.size - 1) "Sonraki" else "Tamamla")
            }
        }
    } else {
        Text(
            text = "Çekiliş Tamamlandı!",
            //style = MaterialTheme.typography.h4,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            textAlign = TextAlign.Center
        )
    }
}


fun generateGiftPairs(participants: List<String>): Map<String, String> {
    require(participants.size >= 2) { "Eşleştirme yapmak için en az 2 kişi olmalı" }

    var shuffled: List<String>
    var pairs: Map<String, String>

    do {
        shuffled = participants.shuffled()
        pairs = participants.zip(shuffled).toMap()
    } while (pairs.any { (giver, receiver) -> giver == receiver })

    return pairs
}


