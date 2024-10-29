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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun GiftDrawScreen(
    participants: List<String>,
    viewModel: GiftDrawViewModel = viewModel()
) {
    // participants listesini ViewModel’e ilk kez aktar
    LaunchedEffect(participants) {
        viewModel.setParticipants(participants)
    }

    val currentPerson = viewModel.currentPerson
    val giftee = viewModel.giftee
    val showEnvelope = viewModel.showEnvelope
    val currentIndex = viewModel.currentIndex

    if (currentIndex < participants.size) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "$currentPerson'in hediye alacağı kişi...",
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (showEnvelope) {
                // Kapalı Zarf Görseli
                Image(
                    painter = painterResource(id = R.drawable.baked_goods_1),
                    contentDescription = "Kapalı Zarf",
                    modifier = Modifier
                        .size(150.dp)
                        .clickable { viewModel.toggleEnvelope() }
                )
            } else {
                // Açık Zarf - Hediyeyi Alacak Kişi
                Text(
                    text = giftee,
                    modifier = Modifier.padding(16.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = { viewModel.goToNext() }) {
                Text(text = if (currentIndex < participants.size - 1) "Sonraki" else "Tamamla")
            }
        }
    } else {
        Text(
            text = "Çekiliş Tamamlandı!",
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            textAlign = TextAlign.Center
        )
    }
}


