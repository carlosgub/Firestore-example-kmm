import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore

@Composable
fun App() {
    MaterialTheme {
        Column(Modifier.fillMaxWidth()) {
            var list by remember { mutableStateOf(listOf<User>()) }
            LaunchedEffect(Unit) {
                list = getUsers()
            }
            LazyColumn {
                items(list) {
                    UserItem(it)
                }
            }
        }
    }
}

@Composable
fun UserItem(user: User) {
    Column {
        Text(
            text = user.name
        )
        Text(
            text = user.age.toString()
        )
    }
}

suspend fun getUsers(): List<User> {
    val firebaseFirestore = Firebase.firestore
    try {
        val userResponse =
            firebaseFirestore.collection("USERS").get()
        return userResponse.documents.map {
            it.data()
        }
    } catch (e: Exception) {
        throw e
    }
}

expect fun getPlatformName(): String