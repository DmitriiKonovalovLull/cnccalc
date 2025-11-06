package com.example.cnccalc.ui.assistant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cnccalc.data.models.ChatMessage
import com.example.cnccalc.domain.use_cases.ProcessQuestionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AssistantViewModel @Inject constructor(
    private val processQuestionUseCase: ProcessQuestionUseCase
) : ViewModel() {

    private val _chatMessages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val chatMessages: StateFlow<List<ChatMessage>> = _chatMessages

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun sendMessage(message: String) {
        viewModelScope.launch {
            // Add user message
            val userMessage = ChatMessage(
                id = System.currentTimeMillis().toString(),
                message = message,
                isUser = true,
                timestamp = System.currentTimeMillis()
            )
            _chatMessages.value = _chatMessages.value + userMessage

            // Get AI response
            _isLoading.value = true
            try {
                val response = processQuestionUseCase(message)
                val aiMessage = ChatMessage(
                    id = (System.currentTimeMillis() + 1).toString(),
                    message = response,
                    isUser = false,
                    timestamp = System.currentTimeMillis()
                )
                _chatMessages.value = _chatMessages.value + aiMessage
            } catch (e: Exception) {
                val errorMessage = ChatMessage(
                    id = (System.currentTimeMillis() + 1).toString(),
                    message = "Sorry, I encountered an error: ${e.message}",
                    isUser = false,
                    timestamp = System.currentTimeMillis()
                )
                _chatMessages.value = _chatMessages.value + errorMessage
            } finally {
                _isLoading.value = false
            }
        }
    }
}