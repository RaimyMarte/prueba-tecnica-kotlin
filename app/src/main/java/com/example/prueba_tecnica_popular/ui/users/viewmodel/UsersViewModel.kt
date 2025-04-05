package com.example.prueba_tecnica_popular.ui.users.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prueba_tecnica_popular.data.api.ApiResult
import com.example.prueba_tecnica_popular.data.user.model.UserApiResponseModel
import com.example.prueba_tecnica_popular.data.user.model.UserModel
import com.example.prueba_tecnica_popular.domain.users.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class UsersViewModel @Inject constructor(private val getUsersUseCase: GetUsersUseCase) :
    ViewModel() {
    private val _successData = MutableLiveData<UserApiResponseModel>()
    val successData: LiveData<UserApiResponseModel> = _successData

    private val _isLastPage = MutableLiveData<Boolean>()
    val isLastPage: LiveData<Boolean> = _isLastPage

    private val _currentPage = MutableLiveData<Int>(1)

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> = _success

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    val users = mutableListOf<UserModel>()

    init {
        fetchUsers()
    }

    private fun fetchUsers(refresh: Boolean = false) {
        _isLoading.value = true

        viewModelScope.launch {
            if (refresh) {
                _currentPage.value = 1
                users.clear()
            }

            val result = getUsersUseCase(_currentPage.value ?: 1)

            when (result) {
                is ApiResult.Success -> {
                    val newData = result.data

                    _successData.value = newData
                    _success.value = true
                    _error.value = null

                    users.addAll(newData.data)

                    _isLastPage.value = newData.page >= newData.totalPages
                }

                is ApiResult.Error -> {
                    _success.value = false
                    _error.value = result.message
                }
            }

            _isLoading.value = false
        }
    }

    fun onLoadMoreUsers() {
        _currentPage.value = _currentPage.value!! + 1
        fetchUsers()
    }

    fun refreshUsers() {
        fetchUsers(refresh = true)
    }
}