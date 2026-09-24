package com.hscmate.app.physics

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
fun PhysicsScreen(
    selectedClass: String,
    onBack: () -> Unit,
    onChapterClick: (String) -> Unit
) {

    val chapters = if (selectedClass == "11th Science") {

        listOf(
            "Units and Measurements",
            "Mathematical Methods",
            "Motion in a Straight Line",
            "Motion in a Plane",
            "Laws of Motion",
            "Gravitation",
            "Mechanical Properties of Solids",
            "Mechanical Properties of Fluids",
            "Thermal Properties of Matter",
            "Sound",
            "Optics",
            "Electrostatics"
        )

    } else {

        listOf(
            "Rotational Dynamics",
            "Mechanical Properties of Fluids",
            "Kinetic Theory of Gases and Radiation",
            "Thermodynamics",
            "Oscillations",
            "Superposition of Waves",
            "Wave Optics",
            "Electrostatics",
            "Current Electricity",
            "Magnetic Fields due to Electric Current",
            "Magnetic Materials",
            "Electromagnetic Induction",
            "AC Circuits",
            "Dual Nature of Radiation and Matter",
            "Structure of Atoms and Nuclei",
            "Semiconductor Devices"
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        // BACK BUTTON

        Text(
            text = "←  Back",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2563EB),
            modifier = Modifier
                .clickable {
                    onBack()
                }
                .padding(vertical = 8.dp)
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        // TITLE

        Text(
            text = "⚛️ Physics",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF172554)
        )

        Spacer(
            modifier = Modifier.height(5.dp)
        )

        Text(
            text = selectedClass,
            fontSize = 15.sp,
            color = Color.Gray
        )

        Spacer(
            modifier = Modifier.height(25.dp)
        )

        Text(
            text = "Physics Chapters",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF111827)
        )

        Spacer(
            modifier = Modifier.height(15.dp)
        )

        // CHAPTER LIST

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(
                items = chapters,
                key = { it }
            ) { chapter ->

                ChapterCard(
                    chapterNumber = chapters.indexOf(chapter) + 1,
                    chapterName = chapter,
                    onClick = {
                        onChapterClick(chapter)
                    }
                )
            }
        }
    }
}


@Composable
fun ChapterCard(
    chapterNumber: Int,
    chapterName: String,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {

            Text(
                text = "%02d".format(chapterNumber),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2563EB)
            )

            Spacer(
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Text(
                text = chapterName,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1F2937)
            )
        }
    }
}