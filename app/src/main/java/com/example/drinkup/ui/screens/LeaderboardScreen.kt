package com.example.drinkup.ui.screens

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.drinkup.data.entities.LeaderboardEntry
import com.example.drinkup.di.AppModule
import com.example.drinkup.navigation.Screen
import com.example.drinkup.ui.components.DrinkUpBottomNavBar
import com.example.drinkup.viewmodels.FriendViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaderboardScreen(
    navController: NavController,
    friendViewModel: FriendViewModel,
    onNavigate: (String) -> Unit
) {
    val context = LocalContext.current
    val preferencesManager = AppModule.providePreferencesManager()
    val userId = preferencesManager.getUserId()

    BackHandler {
        if (!navController.popBackStack()) {
            if (context is Activity) {
                context.finish()
            }
        }
    }

    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var friendUsername by remember { mutableStateOf("") }
    var showAddFriend by remember { mutableStateOf(false) }
    var friendToRemove by remember { mutableStateOf<LeaderboardEntry?>(null) }

    val leaderboardResult by friendViewModel.leaderboard.observeAsState()
    val addFriendResult by friendViewModel.addFriendResult.observeAsState()
    val removeFriendResult by friendViewModel.removeFriendResult.observeAsState()


    LaunchedEffect(Unit) {
        isLoading = true
        errorMessage = null
        friendViewModel.loadLeaderboard(userId)
    }


    LaunchedEffect(leaderboardResult) {
        leaderboardResult?.let {
            isLoading = false
        }
    }


    LaunchedEffect(addFriendResult) {
        addFriendResult?.let { result ->
            isLoading = false
            result.fold(
                onSuccess = {
                    if (it) {
                        friendUsername = ""
                        showAddFriend = false
                        friendViewModel.loadLeaderboard(userId)
                    } else {
                        errorMessage = "Failed to add friend"
                    }
                },
                onFailure = {
                    errorMessage = it.message ?: "Failed to add friend"
                }
            )
        }
    }


    LaunchedEffect(removeFriendResult) {
        removeFriendResult?.let { result ->
            isLoading = false
            result.fold(
                onSuccess = {
                    if (it) {
                        friendViewModel.loadLeaderboard(userId)
                    } else {
                        errorMessage = "Failed to remove friend"
                    }
                },
                onFailure = {
                    errorMessage = it.message ?: "Failed to remove friend"
                }
            )
        }
    }


    val leaderboard = leaderboardResult?.getOrNull() ?: emptyList()
    val currentUser = leaderboard.find { it.userId == userId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Leaderboard") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            DrinkUpBottomNavBar(
                currentRoute = Screen.Leaderboard.route,
                onNavigate = onNavigate
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            currentUser?.let { user ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "User Profile",
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Your Ranking",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                if (user.rank <= 3) {
                                    Icon(
                                        imageVector = Icons.Default.EmojiEvents,
                                        contentDescription = when (user.rank) {
                                            1 -> "Gold Trophy"
                                            2 -> "Silver Trophy"
                                            3 -> "Bronze Trophy"
                                            else -> "Trophy"
                                        },
                                        tint = when (user.rank) {
                                            1 -> Color(0xFFFFD700) // Gold
                                            2 -> Color(0xFFC0C0C0) // Silver
                                            3 -> Color(0xFFCD7F32) // Bronze
                                            else -> Color.Gray
                                        },
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }

                            Text(
                                text = "${user.rank}${getOrdinalSuffix(user.rank)} out of ${leaderboard.size} friends",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "${user.todayIntake / 1000f}L",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "Today",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }


            if (showAddFriend) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = friendUsername,
                        onValueChange = { friendUsername = it },
                        label = { Text("Friend's Username") },
                        modifier = Modifier.weight(1f),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = {
                            if (friendUsername.isNotBlank()) {
                                isLoading = true
                                errorMessage = null
                                friendViewModel.addFriend(userId, friendUsername)
                            } else {
                                errorMessage = "Please enter a username"
                            }
                        },
                        enabled = !isLoading && friendUsername.isNotBlank()
                    ) {
                        Text("Add")
                    }
                }
            }

            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Loading...",
                        color = Color.Gray,
                        fontSize = 16.sp
                    )
                }
            } else {

                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(leaderboard.filter { it.userId != currentUser?.userId }) { user ->
                        LeaderboardItem(
                            user = user,
                            onLongPress = { friendToRemove = it }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            Button(
                onClick = {
                    if (!showAddFriend) {
                        showAddFriend = true
                    } else {

                        val inviteCode = List(6) { ('A'..'Z') + ('0'..'9') }.flatten().shuffled().take(6).joinToString("")
                        val message = "Come join me on DrinkUp! https://drinkup.com/invite/$inviteCode"
                        val intent = Intent(Intent.ACTION_VIEW).apply {
                            data = Uri.parse("sms:")
                            putExtra("sms_body", message)
                        }
                        context.startActivity(intent)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = if (showAddFriend) Icons.Default.Person else Icons.Default.Search,
                    contentDescription = if (showAddFriend) "Invite Friends" else "Add Friend"
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = if (showAddFriend) "Invite Friends" else "Add Friend",
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }


            errorMessage?.let {
                Spacer(modifier = Modifier.height(16.dp))
                Snackbar(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = it)
                }
            }
        }


        friendToRemove?.let { friend ->
            AlertDialog(
                onDismissRequest = { friendToRemove = null },
                title = { Text("Remove Friend") },
                text = { Text("Are you sure you want to remove ${friend.username} from your friends?") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            isLoading = true
                            errorMessage = null
                            friendViewModel.removeFriend(userId, friend.userId)
                            friendToRemove = null

                            CoroutineScope(Dispatchers.Main).launch {
                                delay(500)
                                friendViewModel.loadLeaderboard(userId)
                            }
                        }
                    ) {
                        Text("Remove")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { friendToRemove = null }
                    ) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LeaderboardItem(
    user: LeaderboardEntry,
    onLongPress: (LeaderboardEntry) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .combinedClickable(
                onClick = { /* Regular click does nothing */ },
                onLongClick = { onLongPress(user) }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Rank
        Text(
            text = "${user.rank}",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.width(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // User avatar and name
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "User Profile",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = user.username,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = "${user.percentage.toInt()}% of daily goal",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        // Today's intake
        Text(
            text = "${user.todayIntake / 1000f}L",
            fontWeight = FontWeight.Bold
        )

        // Trophy icon for top 3
        if (user.rank <= 3) {
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.EmojiEvents,
                contentDescription = "Trophy",
                tint = when (user.rank) {
                    1 -> Color(0xFFFFD700) // Gold
                    2 -> Color(0xFFC0C0C0) // Silver
                    3 -> Color(0xFFCD7F32) // Bronze
                    else -> Color.Gray
                }
            )
        }
    }
}


fun getOrdinalSuffix(n: Int): String {
    return when {
        n % 100 in 11..13 -> "th"
        n % 10 == 1 -> "st"
        n % 10 == 2 -> "nd"
        n % 10 == 3 -> "rd"
        else -> "th"
    }
}