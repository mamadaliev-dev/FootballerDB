package uz.mamadalievdev.footballerdb.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import uz.mamadalievdev.footballerdb.data.base.BaseNetworkResult
import uz.mamadalievdev.footballerdb.data.response.Player
import uz.mamadalievdev.footballerdb.domain.BaseUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainUseCase: BaseUseCase,
) : ViewModel() {

    private val searchedArticles = MutableLiveData<List<Player>>()
    val searchedArticlesLiveData: LiveData<List<Player>> get() = searchedArticles

    private val _isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoadingLiveData: LiveData<Boolean> get() = _isLoadingLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorLiveData

    fun getSearchedFootballers(query: String) {
        viewModelScope.launch {
            mainUseCase.getSearchedFootballers(query).catch {
                Log.d("DDDD", "getServicesResponse: $this")
            }.collect { result ->
                when (result) {
                    is BaseNetworkResult.Success -> {
                        result.data?.let { item ->
                            searchedArticles.value = item.player
                        }
                    }
                    is BaseNetworkResult.Error -> {
                        result.message.let {
                            _errorLiveData.value = it
                        }
                    }
                    is BaseNetworkResult.Loading -> {
                        result.isLoading?.let {
                            _isLoadingLiveData.value = it
                        }
                    }
                }
            }
        }
    }
}