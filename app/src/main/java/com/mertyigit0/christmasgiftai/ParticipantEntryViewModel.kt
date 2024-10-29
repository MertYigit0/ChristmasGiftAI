package com.mertyigit0.christmasgiftai

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf


class ParticipantEntryViewModel : ViewModel() {
    // mutableStateOf() kullanarak var'ı val olarak tanımlayın
    private val _participantCount = mutableStateOf("")
    val participantCount: String
        get() = _participantCount.value

    private val _isNameEntryVisible = mutableStateOf(false)
    val isNameEntryVisible: Boolean
        get() = _isNameEntryVisible.value

    val participantNames = mutableStateListOf<String>()

    fun onParticipantCountChange(count: String) {
        _participantCount.value = count
    }

    fun startNameEntry() {
        val count = _participantCount.value.toIntOrNull()
        if (count != null && count > 1) {
            participantNames.clear()
            repeat(count) { participantNames.add("") }
            _isNameEntryVisible.value = true
        }
    }

    fun addParticipantName(index: Int, name: String) {
        participantNames[index] = name
    }

    fun canStartDraw(): Boolean {
        return participantNames.all { it.isNotBlank() }
    }
}

