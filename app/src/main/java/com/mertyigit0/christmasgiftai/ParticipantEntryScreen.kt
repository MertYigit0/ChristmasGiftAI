package com.mertyigit0.christmasgiftai

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController

@Preview(showBackground = true)
@Composable
fun ParticipantEntryScreen(navController: NavController) {
    val viewModel: ParticipantEntryViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (!viewModel.isNameEntryVisible) {
            Text("Kaç kişi katılacak?", style = MaterialTheme.typography.titleMedium)
            OutlinedTextField(
                value = viewModel.participantCount,
                onValueChange = { viewModel.onParticipantCountChange(it) },
                label = { Text("Katılımcı Sayısı") },
                modifier = Modifier.padding(8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.startNameEntry()
                },
                enabled = viewModel.participantCount.isNotBlank() && viewModel.participantCount.toIntOrNull() != null && viewModel.participantCount.toInt() > 1
            ) {
                Text("İsimleri Girmeye Başla")
            }
        } else {
            Text("Katılımcı İsimlerini Girin", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(16.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(viewModel.participantNames.size) { index ->
                    OutlinedTextField(
                        value = viewModel.participantNames[index],
                        onValueChange = { viewModel.addParticipantName(index, it) },
                        label = { Text("Katılımcı ${index + 1}") }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val names = viewModel.participantNames.filter { it.isNotBlank() }
                    if (viewModel.canStartDraw()) {
                        navController.navigate("giftDraw/${names.joinToString(",")}")

                    }
                },
                enabled = viewModel.canStartDraw()
            ) {
                Text("Çekilişi Başlat")
            }
        }
    }
}


// Örnek bir önizleme fonksiyonu
@Preview(showBackground = true)
@Composable
fun ParticipantEntryScreenPreview() {
    // Önizleme için bir NavController oluşturuyoruz. Burada gerçek bir NavController kullanılmadığı için
    // dummy bir kontekst oluşturabilirsiniz.
    val dummyNavController = rememberNavController()
    ParticipantEntryScreen(navController = dummyNavController)
}
