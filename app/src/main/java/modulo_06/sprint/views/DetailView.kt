package modulo_06.sprint.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import modulo_06.sprint.navigation.NavigationDestination
import modulo_06.sprint.room.Phone
import java.util.Locale

object DetailDestination : NavigationDestination {
    override val route = "detail"
    override val titleRes :Int = 1

}


@Composable
fun DetailView(
    phone : PhoneModel,
    navigateBack: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    )
    {
//        AsyncImage(
//            model = phone.image,
//            contentDescription = "My Image", // Provide a content description
//        )
        Text(
            text = phone.name?:"",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = String.format(Locale.getDefault(), "%,d", phone.price ?: 0),
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = phone.description?:"",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = String.format(Locale.getDefault(), "%,d", phone.lastPrice ?: 0),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = (phone.credit.toString()?:""),
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
//                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(phone.url))
//                //LocalContext.current.startActivity(intent)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        {
            Text(text = "Read more")
        }
    }
}