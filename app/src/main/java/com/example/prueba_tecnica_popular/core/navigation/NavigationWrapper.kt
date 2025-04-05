package com.example.prueba_tecnica_popular.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prueba_tecnica_popular.ui.auth.view.LoginScreen
import com.example.prueba_tecnica_popular.ui.auth.viewmodel.LoginViewModel
import com.example.prueba_tecnica_popular.ui.users.view.UsersListScreen
import com.example.prueba_tecnica_popular.ui.users.viewmodel.UsersViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.toRoute
import com.example.prueba_tecnica_popular.data.user.model.UserModel
import com.example.prueba_tecnica_popular.ui.users.view.UserDetailsScreen
import com.google.gson.Gson


@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Login) {
        composable<Login> {
            val loginViewModel: LoginViewModel = hiltViewModel()

            LoginScreen(
                loginViewModel,
                navigateToHome = { navController.navigate(UsersList) },
                navigateToSignUp = { navController.navigate(UsersList) })
        }

        composable<UsersList> {
            val usersViewModel: UsersViewModel = hiltViewModel()

            UsersListScreen(
                usersViewModel,
                navigateToUserDetails = { user ->
                    navController.navigate(
                        UserDetails(
                            Gson().toJson(
                                user
                            )
                        )
                    )
                })
        }

        composable<UserDetails> { backStackEntry ->
            val userDetails = backStackEntry.toRoute<UserDetails>()
            val user = Gson().fromJson(userDetails.user, UserModel::class.java)

            UserDetailsScreen(user,navigateBack = { navController.navigateUp() },)
        }
    }
}
