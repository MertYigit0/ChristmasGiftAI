package com.mertyigit0.christmasgiftai

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class GiftDrawViewModel : ViewModel() {

    private var participants = listOf<String>()
    private var pairs = mapOf<String, String>()

    // ViewState variables
    var currentIndex by mutableStateOf(0)
        private set

    var showEnvelope by mutableStateOf(true)
        private set

    val currentPerson: String
        get() = participants.getOrNull(currentIndex) ?: ""

    val giftee: String
        get() = pairs[currentPerson] ?: ""

    // Katılımcı listesini ViewModel'e eklemek için bir fonksiyon
    fun setParticipants(newParticipants: List<String>) {
        participants = newParticipants
        pairs = generateGiftPairs(participants)
        currentIndex = 0
        showEnvelope = true
    }

    // Actions
    fun toggleEnvelope() {
        showEnvelope = !showEnvelope
    }

    fun goToNext() {
        if (currentIndex < participants.size - 1) {
            showEnvelope = true
            currentIndex++
        }
    }

    // Çekiliş eşleştirme mantığını ViewModel'de barındırıyoruz.
    private fun generateGiftPairs(participants: List<String>): Map<String, String> {
        require(participants.size >= 2) { "Eşleştirme yapmak için en az 2 kişi olmalı" }

        var shuffled: List<String>
        var pairs: Map<String, String>

        do {
            shuffled = participants.shuffled()
            pairs = participants.zip(shuffled).toMap()
        } while (pairs.any { (giver, receiver) -> giver == receiver })

        return pairs
    }
}
