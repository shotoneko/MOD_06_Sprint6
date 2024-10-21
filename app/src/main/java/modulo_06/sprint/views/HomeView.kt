package modulo_06.sprint.views

import android.os.Parcelable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import modulo_06.sprint.R
import modulo_06.sprint.navigation.NavigationDestination
import modulo_06.sprint.room.Phone
import modulo_06.sprint.util.StringSanitizer
import java.util.Locale

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.home_screen_title
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    viewModel: HomeViewVM = hiltViewModel(),
    navigateToDetailScreen: (PhoneModel) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Phone Shoping Cart", color = Color.White) },
                actions = {
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(Icons.Default.Add, "Add", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFF8A65)
                )
            )
        }
    ) {
        ContentHomeView(it, viewModel, navigateToDetailScreen)
    }
}

@Composable
fun ContentHomeView(
    paddingValues: PaddingValues,
    viewModel: HomeViewVM,
    navigateToDetailScreen: (PhoneModel) -> Unit = {}
) {
    val phones by viewModel.phones.collectAsState(listOf())
    val isLoading by viewModel.isLoading.collectAsState(false)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(8.dp)
    ) {
        items(phones) { phone ->
            PhoneCard(phone = phone, onDeleteClick = { viewModel.deletePhone(phone) })
        }
        // Loading indicator at the bottom
        item {
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxWidth().
                    wrapContentSize(Alignment.Center).clickable { navigateToDetailScreen(PhoneModel()) }
                ) {
                        CircularProgressIndicator()
                }
            }
        }
    }
}


@Composable
fun PhoneCard(phone: Phone, onDeleteClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(phone.name, style = TextStyle(fontWeight = FontWeight.Bold))
                Text(String.format(Locale.getDefault(), "%,d", phone.price ?: 0))
            }
            Spacer(modifier = Modifier.width(16.dp))
            IconButton(
                onClick = onDeleteClick
            ) {
                Icon(Icons.Default.Delete, "Delete")
            }
        }
    }
}


@Parcelize
@Serializable
data class PhoneModel(
    val id: Int? = 0,

//    @Serializable(with = StringSanitizer::class)
//    val image: String? = "",

    val name: String? = "",
    val price: Int? = 0,
    val description: String? = "",
    val lastPrice: Int? = 0,
    val credit: Boolean? = false,


): Parcelable