package com.hscmate.app.physics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
fun PhysicsNotesScreen(
    chapterName: String,
    selectedClass: String,
    onBack: () -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {

            Text(
                text = "←  Back",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2563EB),
                modifier = Modifier.padding(bottom = 10.dp)
            )

            Text(
                text = "📖 $chapterName",
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF172554)
            )

            Spacer(
                modifier = Modifier.height(5.dp)
            )

            Text(
                text = selectedClass,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        item {

            NotesSection(
                title = "Introduction",
                content = getIntroduction(chapterName)
            )
        }

        item {

            NotesSection(
                title = "Key Concepts",
                content = getKeyConcepts(chapterName)
            )
        }

        item {

            NotesSection(
                title = "Important Points",
                content = getImportantPoints(chapterName)
            )
        }

        item {

            NotesSection(
                title = "Quick Revision",
                content = getQuickRevision(chapterName)
            )
        }
    }
}


@Composable
fun NotesSection(
    title: String,
    content: String
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
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
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF172554)
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Text(
                text = content,
                fontSize = 15.sp,
                lineHeight = 23.sp,
                color = Color(0xFF374151)
            )
        }
    }
}


fun getIntroduction(chapterName: String): String {

    return when (chapterName) {

        "Rotational Dynamics" ->
            """
            Rotational Dynamics deals with the motion of objects rotating about an axis.

            It is the rotational equivalent of the concepts used in linear motion.

            In this chapter we study torque, angular velocity, angular acceleration,
            moment of inertia and angular momentum.
            """.trimIndent()

        "Current Electricity" ->
            """
            Current Electricity deals with the flow of electric charge through a conductor.

            Important concepts include electric current, potential difference,
            resistance, Ohm's law and electrical circuits.
            """.trimIndent()

        else ->
            """
            This chapter contains important concepts from Maharashtra HSC Physics.

            Study each concept carefully and practice numerical problems regularly.
            """.trimIndent()
    }
}


fun getKeyConcepts(chapterName: String): String {

    return when (chapterName) {

        "Rotational Dynamics" ->
            """
            • Angular displacement
            • Angular velocity
            • Angular acceleration
            • Torque
            • Moment of inertia
            • Radius of gyration
            • Angular momentum
            • Conservation of angular momentum
            """.trimIndent()

        "Current Electricity" ->
            """
            • Electric current
            • Drift velocity
            • Ohm's law
            • Resistance
            • Resistivity
            • Series and parallel circuits
            • Kirchhoff's laws
            """.trimIndent()

        else ->
            """
            • Understand the basic definitions.
            • Learn the important formulas.
            • Practice numerical problems.
            • Revise important derivations.
            """.trimIndent()
    }
}


fun getImportantPoints(chapterName: String): String {

    return when (chapterName) {

        "Rotational Dynamics" ->
            """
            • Torque produces rotational acceleration.
            • Moment of inertia depends on mass distribution.
            • Angular momentum is conserved when external torque is zero.
            • SI unit of torque is N m.
            • SI unit of angular momentum is kg m²/s.
            """.trimIndent()

        "Current Electricity" ->
            """
            • Current is the rate of flow of electric charge.
            • Ohm's law: V = IR
            • Resistance depends on length, area and material.
            • Resistivity is a property of the material.
            """.trimIndent()

        else ->
            """
            • Learn definitions accurately.
            • Remember SI units.
            • Practice formulas with numerical problems.
            • Focus on HSC Board-style questions.
            """.trimIndent()
    }
}


fun getQuickRevision(chapterName: String): String {

    return when (chapterName) {

        "Rotational Dynamics" ->
            """
            Torque:
            τ = Iα

            Angular momentum:
            L = Iω

            Rotational kinetic energy:
            K = ½Iω²

            These formulas are important for numerical problems.
            """.trimIndent()

        "Current Electricity" ->
            """
            Ohm's Law:
            V = IR

            Electric power:
            P = VI

            Also remember:
            P = I²R
            P = V²/R
            """.trimIndent()

        else ->
            """
            Revise the definitions, formulas, important derivations
            and solved numerical problems before attempting the HSC examination.
            """.trimIndent()
    }
}