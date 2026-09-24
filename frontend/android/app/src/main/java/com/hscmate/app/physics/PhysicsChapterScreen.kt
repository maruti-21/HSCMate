package com.hscmate.app.physics

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PhysicsChapterScreen(
    chapterName: String,
    selectedClass: String,
    onBack: () -> Unit,
    onStudyNotesClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "←  Physics",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2563EB),
            modifier = Modifier
                .clickable {
                    onBack()
                }
                .padding(bottom = 20.dp)
        )

        Text(
            text = chapterName,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF172554)
        )

        Spacer(
            modifier = Modifier.height(6.dp)
        )

        Text(
            text = selectedClass,
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(
            modifier = Modifier.height(25.dp)
        )

        Text(
            text = "Chapter Resources",
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF111827)
        )

        Spacer(
            modifier = Modifier.height(15.dp)
        )

        ChapterOption(
            emoji = "📖",
            title = "Study Notes",
            description = "Learn the complete chapter",
            onClick = onStudyNotesClick
        )

        ChapterOption(
            emoji = "📐",
            title = "Formula Sheet",
            description = "Important formulas and equations"
        )

        ChapterOption(
            emoji = "⭐",
            title = "Important Points",
            description = "Quick revision before exams"
        )

        ChapterOption(
            emoji = "📝",
            title = "Board Questions",
            description = "Important HSC questions"
        )

        ChapterOption(
            emoji = "🧮",
            title = "Practice Problems",
            description = "Practice and improve your concepts"
        )
    }
}


@Composable
fun ChapterOption(
    emoji: String,
    title: String,
    description: String,
    onClick: () -> Unit = {}
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
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
            modifier = Modifier.padding(18.dp)
        ) {

            Text(
                text = "$emoji  $title",
                fontSize = 18.sp,
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
    }
}