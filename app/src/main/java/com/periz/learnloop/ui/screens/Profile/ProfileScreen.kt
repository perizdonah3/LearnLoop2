package com.periz.learnloop.ui.screens.Profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.google.firebase.firestore.FirebaseFirestore
import com.periz.learnloop.R
import com.periz.learnloop.models.CloudinaryManager
import com.periz.learnloop.ui.theme.Pink80

@Composable
fun ProfileScreen(navController: NavHostController) {

    val context = LocalContext.current
    val db = FirebaseFirestore.getInstance()

    var imageUrl by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var institution by remember { mutableStateOf("") }
    var skills by remember { mutableStateOf("") }

    var isEditing by remember { mutableStateOf(false) }

    val userId = "demoUser" // later replace with real auth user id

    // LOAD DATA FROM FIREBASE
    LaunchedEffect(Unit) {
        db.collection("users").document(userId)
            .get()
            .addOnSuccessListener { doc ->
                if (doc.exists()) {
                    email = doc.getString("email") ?: ""
                    institution = doc.getString("institution") ?: ""
                    skills = doc.getString("skills") ?: ""
                    imageUrl = doc.getString("imageUrl") ?: ""
                }
            }
    }

    val imagePickerLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                CloudinaryManager.uploadImage(
                    context = context,
                    uri = it,
                    onSuccess = { uploadedUrl ->
                        imageUrl = uploadedUrl

                        // SAVE IMAGE URL
                        db.collection("users").document(userId)
                            .update("imageUrl", uploadedUrl)
                    },
                    onError = {}
                )
            }
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Pink80)
            .systemBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                modifier = Modifier.clickable {
                    navController.popBackStack()
                }
            )
        }

        Box(
            modifier = Modifier
                .size(110.dp)
                .clip(CircleShape)
                .clickable { imagePickerLauncher.launch("image/*") },
            contentAlignment = Alignment.Center
        ) {
            if (imageUrl.isNotEmpty()) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.img_2),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))



        Spacer(modifier = Modifier.height(22.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                EditableProfileItem(
                    Icons.Default.Email,
                    "Email",
                    email,
                    isEditing
                ) { email = it }

                EditableProfileItem(
                    Icons.Default.School,
                    "Institution",
                    institution,
                    isEditing
                ) { institution = it }

                EditableProfileItem(
                    Icons.Default.Edit,
                    "Skills",
                    skills,
                    isEditing
                ) { skills = it }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (isEditing) {
                    // SAVE TO FIRESTORE
                    val data = hashMapOf(
                        "email" to email,
                        "institution" to institution,
                        "skills" to skills,
                        "imageUrl" to imageUrl
                    )

                    db.collection("users").document(userId)
                        .set(data)
                }

                isEditing = !isEditing
            },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Icon(Icons.Default.Edit, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(if (isEditing) "Save Profile" else "Edit Profile")
        }
    }
}

@Composable
fun EditableProfileItem(
    icon: ImageVector,
    title: String,
    value: String,
    isEditing: Boolean,
    onValueChange: (String) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {

        Icon(icon, null)

        Spacer(modifier = Modifier.width(14.dp))

        Column(modifier = Modifier.fillMaxWidth()) {

            Text(title, fontWeight = FontWeight.Bold)

            if (isEditing) {
                OutlinedTextField(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                Text(value)
            }
        }
    }
}

@Preview
@Composable
fun PreviewProfile() {
    ProfileScreen(rememberNavController())
}