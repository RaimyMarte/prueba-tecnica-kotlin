package com.example.prueba_tecnica_popular.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
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
import com.example.prueba_tecnica_popular.ui.session.viewmodel.AppSessionViewModel
import com.example.prueba_tecnica_popular.ui.users.view.UserDetailsScreen
import com.google.gson.Gson


@Composable
fun NavigationWrapper(appSessionViewModel: AppSessionViewModel = hiltViewModel()) {
    val navController = rememberNavController()

    val isAuthenticated = appSessionViewModel.isAuthenticated.observeAsState(initial = false)
    val startDestination = if (isAuthenticated.value) UsersList else Login

    NavHost(navController = navController, startDestination = startDestination) {
        val navigateToLogin = { navController.navigate(Login) }
        val navigateToHome = { navController.navigate(UsersList) }
        val navigateToSignUp = { navController.navigate(SignUp) }

        composable<Login> {
            val loginViewModel: LoginViewModel = hiltViewModel()

            LoginScreen(
                loginViewModel,
                navigateToHome = navigateToHome,
                navigateToSignUp = navigateToSignUp
            )
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
                },
                navigateToLogin = navigateToLogin
            )
        }

        composable<UserDetails> { backStackEntry ->
            val usersViewModel: UsersViewModel = hiltViewModel()

            val userDetails = backStackEntry.toRoute<UserDetails>()
            val user = Gson().fromJson(userDetails.user, UserModel::class.java)

            UserDetailsScreen(
                usersViewModel,
                user,
                navigateBack = { navController.navigateUp() },
                navigateToLogin = navigateToLogin
            )
        }
    }
}
