package com.example.prueba_tecnica_popular.ui.users.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.prueba_tecnica_popular.data.user.model.UserModel
import com.example.prueba_tecnica_popular.ui.users.viewmodel.UsersViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.text.input.KeyboardType
import com.example.prueba_tecnica_popular.ui.components.LoadingIndicator
import com.example.prueba_tecnica_popular.ui.users.view.components.UsersList


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersListScreen(
    viewModel: UsersViewModel,
    navigateToUserDetails: (UserModel) -> Unit,
    navigateToLogin: () -> Unit
) {
    val successData = viewModel.successData.observeAsState()
    val isLastPage = viewModel.isLastPage.observeAsState(initial = false)
    val isLoading = viewModel.isLoading.observeAsState(initial = false)
    val success = viewModel.success.observeAsState(initial = false)
    val error = viewModel.error.observeAsState(initial = null)
    val searchQuery = viewModel.searchQuery.observeAsState(initial = "")
    val openSearchInput = viewModel.openSearchInput.observeAsState(initial = false)

    val users = viewModel.getDisplayedUsers()
    val isSearching = searchQuery.value.isNotEmpty()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Usuarios") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF003262),
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = {
                        if (openSearchInput.value) {
                            viewModel.onCloseSearchInput()
                        } else {
                            viewModel.onOpenSearchInput()
                        }
                    }) {
                        Icon(
                            imageVector = if (openSearchInput.value) Icons.Filled.Clear else Icons.Filled.Search,
                            contentDescription = if (openSearchInput.value) "Limpair búsqueda" else "Buscar",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = {
                        navigateToLogin()
                        viewModel.onLogout()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Logout,
                            contentDescription = "Cerrar sesión",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
        ) {

            if (openSearchInput.value) {
                SearchField(
                    query = searchQuery.value,
                    onQueryChange = { viewModel.onSearchQueryChanged(it) },
                    onClearSearch = { viewModel.clearSearch() }
                )
            }



            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                if (isLoading.value) {
                    LoadingIndicator()
                }
                if (success.value) {
                    UsersList(
                        users = users,
                        navigateToUserDetails = navigateToUserDetails,
                        hasMorePages = !isLastPage.value,
                        onLoadMore = { viewModel.onLoadMoreUsers() },
                        currentUsers = users.size,
                        totalUsers = successData.value?.total ?: 1
                    )
                }

                if (isSearching && users.isEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "No se encontraron resultados para \"${searchQuery.value}\"",
                            color = Color.Gray,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }

                error.value?.let {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = it,
                            color = Color.Red,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { viewModel.refreshUsers() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF003262)
                            )
                        ) {
                            Text("Reintentar")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SearchField(
    query: String,
    onQueryChange: (String) -> Unit,
    onClearSearch: () -> Unit
) {
    TextField(
        value = query,
        onValueChange = { onQueryChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        placeholder = { Text("Buscar usuario...") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Buscar",
                tint = Color(0xFF003262)
            )
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = onClearSearch) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Limpiar búsqueda",
                        tint = Color(0xFF003262)
                    )
                }
            }
        },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color(0xFF003262),
            unfocusedTextColor = Color(0xFF003262),
            cursorColor = Color(0xFF003262),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}


