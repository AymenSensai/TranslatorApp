package com.aymen.translatorapp.feature_translator.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aymen.translatorapp.feature_translator.domain.repository.TranslationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TranslatorViewModel @Inject constructor(
    private val repo: TranslationRepository
) : ViewModel() {

    private val _state: MutableStateFlow<TranslatorState> = MutableStateFlow(TranslatorState())
    val state = _state.asStateFlow()

    private var translateJob: Job? = null

    fun onEvent(event: TranslatorEvent) {
        when (event) {
            is TranslatorEvent.Translate -> translate(event.query)
            is TranslatorEvent.Clear -> clear()
            is TranslatorEvent.OnSourceChange -> onSourceChange(event.lang)
            is TranslatorEvent.OnTargetChange -> onTargetChange(event.lang)
        }
    }

    private fun translate(query: String) {
        _state.update { _state.value.copy(query = query, isLoading = true) }
        translateJob?.cancel()
        if (query.isNotBlank()) {
            translateJob = viewModelScope.launch(Dispatchers.IO) {
                _state.value.let {
                    delay(500L)
                    repo.translate(it.query, it.source.symbol, it.target.symbol).also { response ->
                        _state.value =
                            _state.value.copy(
                                translation = response.data,
                                isLoading = false
                            )
                    }
                }
            }
        } else {
            _state.update { _state.value.copy(isLoading = false) }
        }
    }

    private fun clear() {
        _state.update { _state.value.copy(translation = null, query = "") }
    }

    private fun onSourceChange(lang: Lang) {
        _state.update { _state.value.copy(source = lang) }
    }

    private fun onTargetChange(lang: Lang) {
        _state.update { _state.value.copy(target = lang) }
    }

}