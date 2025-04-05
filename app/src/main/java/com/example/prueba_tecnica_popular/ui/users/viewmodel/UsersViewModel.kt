package com.example.prueba_tecnica_popular.ui.users.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prueba_tecnica_popular.data.api.ApiResult
import com.example.prueba_tecnica_popular.data.user.model.UserApiResponseModel
import com.example.prueba_tecnica_popular.data.user.model.UserModel
import com.example.prueba_tecnica_popular.domain.auth.LogoutUseCase
import com.example.prueba_tecnica_popular.domain.users.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val logoutUseCase: LogoutUseCase
) :
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

    private val _openSearchInput = MutableLiveData<Boolean>()
    val openSearchInput: LiveData<Boolean> = _openSearchInput

    private val _searchQuery = MutableLiveData<String>("")
    val searchQuery: LiveData<String> = _searchQuery

    private val _filteredUsers = MutableLiveData<List<UserModel>>(emptyList())

    init {
        fetchUsers()
    }

    private fun fetchUsers(refresh: Boolean = false) {
        _isLoading.value = true

        viewModelScope.launch {
            if (refresh) {
                _searchQuery.value = ""
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

                    if (!_searchQuery.value.isNullOrEmpty()) {
                        filterUsers(_searchQuery.value!!)
                    } else {
                        _filteredUsers.value = users.toList()
                    }

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

    fun onLogout() {
        viewModelScope.launch {
            _currentPage.value = 1
            users.clear()

            logoutUseCase()
        }
    }

    fun onLoadMoreUsers() {
        _currentPage.value = _currentPage.value!! + 1
        fetchUsers()
    }

    fun refreshUsers() {
        fetchUsers(refresh = true)
    }


    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        filterUsers(query)
    }

    fun onOpenSearchInput() {
        _openSearchInput.value = true
    }

    fun onCloseSearchInput(){
        _openSearchInput.value = false
    }

    fun clearSearch() {
        _searchQuery.value = ""
        _filteredUsers.value = users.toList()
    }

    private fun filterUsers(query: String) {
        if (query.isEmpty()) {
            _filteredUsers.value = users.toList()
            return
        }

        val lowercaseQuery = query.lowercase()
        _filteredUsers.value = users.filter { user ->
            user.firstName.lowercase().contains(lowercaseQuery) ||
                    user.lastName.lowercase().contains(lowercaseQuery) ||
                    user.email.lowercase().contains(lowercaseQuery)
        }
    }

    fun getDisplayedUsers(): List<UserModel> {
        return if (!_searchQuery.value.isNullOrEmpty()) {
            _filteredUsers.value ?: emptyList()
        } else {
            users
        }
    }
}