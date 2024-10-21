package modulo_06.sprint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import modulo_06.sprint.navigation.PhoneNavHost
import modulo_06.sprint.repository.Repository
import modulo_06.sprint.ui.theme.Sprint6Theme
import modulo_06.sprint.views.HomeView
import modulo_06.sprint.views.HomeViewVM
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var repository: Repository
    val viewModel: HomeViewVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Sprint6Theme {
                val navController = rememberNavController()
                PhoneNavHost(navController = navController)
            }
        }
    }
}
//
//@Composable
//fun MainScreen (repo: Repository) {
//    LaunchedEffect(Unit) {
//        // val response = repo.getAllMoviesApi()
//        val response = repo.getAllProducts()
//        //val response2 = repo.getNewMovie()
//
//    }
//
//    Scaffold {it->
//        Text(
//            text = "Hello, World!",
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp)
//        )
//    }
//}