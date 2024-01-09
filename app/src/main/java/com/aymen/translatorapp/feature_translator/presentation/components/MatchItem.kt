package com.aymen.translatorapp.feature_translator.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aymen.translatorapp.feature_translator.domain.models.Match
import com.aymen.translatorapp.ui.theme.Orange

@Composable
fun MatchItem(match: Match, modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Orange.copy(0.8f))
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(.7f),
                    text = match.translation,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.weight(.3f),
                    text = "Match: ${match.match * 100}%",
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.End
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = match.source + " to " + match.target,
                color = Color.LightGray,
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "By: ${match.createdBy}",
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Usage Count: ${match.usageCount}",
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

    }
}