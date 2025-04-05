package com.example.prueba_tecnica_popular.ui.users.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prueba_tecnica_popular.data.user.model.UserModel

@Composable
fun UsersList(
    users: List<UserModel>,
    navigateToUserDetails: (UserModel) -> Unit,
    hasMorePages: Boolean,
    onLoadMore: () -> Unit,
    currentUsers: Int,
    totalUsers: Int
) {
    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            (lastVisibleItem >= users.size - 3) && hasMorePages
        }.collect { shouldLoad ->
            if (shouldLoad) {
                onLoadMore()
            }
        }
    }

    Box {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize()
        ) {
            items(users.size) { index ->
                val user = users[index]

                UserListItem (user = user, navigateToUserDetails = navigateToUserDetails)
                HorizontalDivider(
                    color = Color.LightGray,
                    thickness = 0.5.dp,
                    modifier = Modifier.padding(start = 72.dp)
                )
            }

            item {
                if (hasMorePages) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    )
                    {
                        CircularProgressIndicator(
                            color = Color(0xFF003262),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                } else if (users.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No hay mas usuarios",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }

        if (users.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .background(Color(0xFF003262).copy(alpha = 0.7f), CircleShape)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "Usuarios $currentUsers/$totalUsers",
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }
    }
}
