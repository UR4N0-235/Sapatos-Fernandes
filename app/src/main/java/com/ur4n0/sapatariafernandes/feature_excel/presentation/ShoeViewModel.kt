package com.ur4n0.sapatariafernandes.feature_excel.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ur4n0.sapatariafernandes.core.util.Resource
import com.ur4n0.sapatariafernandes.core.util.isValidLong
import com.ur4n0.sapatariafernandes.feature_excel.domain.use_case.GetShoe
import com.ur4n0.sapatariafernandes.feature_excel.domain.use_case.GetShoes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoeViewModel @Inject constructor(
    private val getShoes: GetShoes,
    private val getOneShoe: GetShoe
): ViewModel() {
    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private val _state = mutableStateOf(ShoeState())
    val state: State<ShoeState> = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null

    fun onSearch(query: String){
        if(isValidLong(query)){ // from core.util.isValidNumber
            _searchQuery.value = query
            searchJob?.cancel()
            searchJob = viewModelScope.launch{
                delay(500L)
                getOneShoe(query.toLong()).onEach { result ->
                    when(result){
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                shoeItems = result.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                        is Resource.Loading -> {
                            _state.value = state.value.copy(
                                shoeItems = result.data ?: emptyList(),
                                isLoading = true
                            )
                        }
                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                shoeItems = result.data ?: emptyList(),
                                isLoading = false
                            )
                            _eventFlow.emit(UIEvent.ShowSnackbar(
                                result.message ?: "Erro desconhecido"
                            ))
                        }
                    }
                }.launchIn(this)
            }
        }else{
            getShoes().onEach {result ->
                when(result){
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            shoeItems = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            shoeItems = result.data ?: emptyList(),
                            isLoading = true
                        )
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            shoeItems = result.data ?: emptyList(),
                            isLoading = false
                        )
                        _eventFlow.emit(UIEvent.ShowSnackbar(
                            result.message ?: "Erro desconhecido"
                        ))
                    }
                }
            }
        }
    }

    sealed class UIEvent{
        data class ShowSnackbar(val message: String): UIEvent()
    }
}