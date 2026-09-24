package com.hscmate.app

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hscmate.app.physics.PhysicsChapterScreen
import com.hscmate.app.physics.PhysicsNotesScreen
import com.hscmate.app.physics.PhysicsScreen
import com.hscmate.app.ui.theme.HSCMateTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            HSCMateTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFF7F8FC)
                ) {

                    HSCMateApp(this)
                }
            }
        }
    }
}


// ======================================================
// MAIN APP
// ======================================================

@Composable
fun HSCMateApp(context: Context) {

    val preferences = remember {
        context.getSharedPreferences(
            "hscmate_preferences",
            Context.MODE_PRIVATE
        )
    }

    var selectedClass by remember {

        mutableStateOf(
            preferences.getString(
                "selected_class",
                null
            )
        )
    }

    // Current screen
    var currentScreen by remember {
        mutableStateOf("home")
    }

    // Selected Physics chapter
    var selectedChapter by remember {
        mutableStateOf("")
    }


    // ==================================================
    // CLASS SELECTION
    // ==================================================

    if (selectedClass == null) {

        ClassSelectionScreen(

            onClassSelected = { className ->

                preferences.edit()
                    .putString(
                        "selected_class",
                        className
                    )
                    .apply()

                selectedClass = className
            }
        )

    } else {


        // ==================================================
        // PHYSICS NOTES
        // ==================================================

        if (currentScreen == "notes") {

            PhysicsNotesScreen(


                chapterName = selectedChapter,

                selectedClass = selectedClass!!,

                onBack = {

                    currentScreen = "chapter"
                }
            )
        }


        // ==================================================
        // PHYSICS CHAPTER RESOURCE SCREEN
        // ==================================================

        else if (currentScreen == "chapter") {

            PhysicsChapterScreen(

                chapterName = selectedChapter,

                selectedClass = selectedClass!!,

                onBack = {

                    currentScreen = "physics"
                },

                onStudyNotesClick = {

                    currentScreen = "notes"
                }
            )
        }


        // ==================================================
        // PHYSICS CHAPTER LIST
        // ==================================================

        else if (currentScreen == "physics") {

            PhysicsScreen(

                selectedClass = selectedClass!!,

                onBack = {

                    currentScreen = "home"
                },

                onChapterClick = { chapterName ->

                    selectedChapter = chapterName

                    currentScreen = "chapter"
                }
            )
        }


        // ==================================================
        // HOME DASHBOARD
        // ==================================================

        else {

            HSCMateHome(

                selectedClass = selectedClass!!,

                onChangeClass = {

                    preferences.edit()
                        .remove("selected_class")
                        .apply()

                    selectedClass = null

                    currentScreen = "home"
                },

                onPhysicsClick = {

                    currentScreen = "physics"
                }
            )
        }
    }
}


// ======================================================
// CLASS SELECTION
// ======================================================

@Composable
fun ClassSelectionScreen(
    onClassSelected: (String) -> Unit
) {

    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F8FC))
            .padding(horizontal = 24.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(
            modifier = Modifier.height(80.dp)
        )

        Text(
            text = "HSCMate",
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF172554)
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Maharashtra HSC Science",
            fontSize = 17.sp,
            color = Color.Gray
        )

        Spacer(
            modifier = Modifier.height(55.dp)
        )

        Text(
            text = "Which class are you in?",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF111827)
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Text(
            text = "Choose your class to personalize your learning.",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(
            modifier = Modifier.height(30.dp)
        )

        ClassCard(
            emoji = "📚",
            title = "11th Science",
            description = "Build your fundamentals",
            onClick = {

                onClassSelected("11th Science")
            }
        )

        Spacer(
            modifier = Modifier.height(18.dp)
        )

        ClassCard(
            emoji = "🎓",
            title = "12th Science",
            description = "Prepare for HSC Board",
            onClick = {

                onClassSelected("12th Science")
            }
        )

        Spacer(
            modifier = Modifier.weight(1f)
        )

        Text(
            text = "Physics • Chemistry • Mathematics • Biology",
            fontSize = 12.sp,
            color = Color.Gray
        )

        Spacer(
            modifier = Modifier.height(30.dp)
        )
    }
}


// ======================================================
// CLASS CARD
// ======================================================

@Composable
fun ClassCard(
    emoji: String,
    title: String,
    description: String,
    onClick: () -> Unit
) {

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .height(115.dp)
            .clickable {

                onClick()
            },

        shape = RoundedCornerShape(22.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Row(

            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 22.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(

                modifier = Modifier
                    .size(65.dp)
                    .background(
                        Color(0xFFEFF6FF),
                        RoundedCornerShape(18.dp)
                    ),

                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = emoji,
                    fontSize = 32.sp
                )
            }

            Spacer(
                modifier = Modifier.width(18.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF111827)
                )

                Spacer(
                    modifier = Modifier.height(5.dp)
                )

                Text(
                    text = description,
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }

            Text(
                text = "→",
                fontSize = 28.sp,
                color = Color(0xFF2563EB)
            )
        }
    }
}


// ======================================================
// HOME DASHBOARD
// ======================================================

@Composable
fun HSCMateHome(
    selectedClass: String,
    onChangeClass: () -> Unit,
    onPhysicsClick: () -> Unit
) {

    LazyColumn(

        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),

        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {

            Spacer(
                modifier = Modifier.height(28.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = "HSCMate",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF172554)
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(
                        text = selectedClass,
                        fontSize = 15.sp,
                        color = Color.Gray
                    )
                }

                Text(
                    text = "Change",

                    modifier = Modifier
                        .clickable {
                            onChangeClass()
                        }
                        .padding(8.dp),

                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2563EB)
                )
            }
        }


        item {

            WelcomeCard(
                selectedClass = selectedClass
            )
        }


        item {

            Text(
                text = "Your Subjects",
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF111827)
            )
        }


        item {

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                SubjectCard(
                    emoji = "⚛️",
                    subject = "Physics",
                    modifier = Modifier.weight(1f),
                    onClick = onPhysicsClick
                )

                SubjectCard(
                    emoji = "🧪",
                    subject = "Chemistry",
                    modifier = Modifier.weight(1f)
                )
            }
        }


        item {

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                SubjectCard(
                    emoji = "📐",
                    subject = "Mathematics",
                    modifier = Modifier.weight(1f)
                )

                SubjectCard(
                    emoji = "🧬",
                    subject = "Biology",
                    modifier = Modifier.weight(1f)
                )
            }
        }


        item {

            Text(
                text = "Study Tools",
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF111827),
                modifier = Modifier.padding(top = 8.dp)
            )
        }


        item {

            StudyToolCard(
                emoji = "🧠",
                title = "AI Doubt Solver",
                description = "Ask questions and understand concepts step-by-step."
            )
        }


        item {

            StudyToolCard(
                emoji = "📷",
                title = "Scan a Question",
                description = "Take a photo of a problem and get its solution."
            )
        }


        item {

            StudyToolCard(
                emoji = "📝",
                title = "Practice Tests",
                description = "Test your knowledge with chapter-wise questions."
            )
        }


        item {

            Spacer(
                modifier = Modifier.height(20.dp)
            )
        }
    }
}


// ======================================================
// WELCOME CARD
// ======================================================

@Composable
fun WelcomeCard(
    selectedClass: String
) {

    Card(

        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(22.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1D4ED8)
        )
    ) {

        Column(
            modifier = Modifier.padding(22.dp)
        ) {

            Text(
                text = "Welcome to HSCMate 👋",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Your personalized $selectedClass study companion.",
                fontSize = 15.sp,
                color = Color.White.copy(alpha = 0.9f)
            )

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            Button(

                onClick = { },

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFF1D4ED8)
                ),

                shape = RoundedCornerShape(12.dp)
            ) {

                Text(
                    text = "Start Learning",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


// ======================================================
// SUBJECT CARD
// ======================================================

@Composable
fun SubjectCard(
    emoji: String,
    subject: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {

    Card(

        modifier = modifier
            .height(145.dp)
            .clickable {

                onClick()
            },

        shape = RoundedCornerShape(18.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = emoji,
                fontSize = 34.sp
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Text(
                text = subject,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1F2937)
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = "View chapters",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}


// ======================================================
// STUDY TOOL CARD
// ======================================================

@Composable
fun StudyToolCard(
    emoji: String,
    title: String,
    description: String
) {

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .clickable { },

        shape = RoundedCornerShape(18.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Row(

            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(

                modifier = Modifier
                    .size(52.dp)
                    .background(
                        color = Color(0xFFEFF6FF),
                        shape = RoundedCornerShape(14.dp)
                    ),

                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = emoji,
                    fontSize = 26.sp
                )
            }

            Spacer(
                modifier = Modifier.width(15.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = title,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF111827)
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = description,
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }
        }
    }
}