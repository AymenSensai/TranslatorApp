package com.aymen.translatorapp.feature_translator.presentation

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aymen.translatorapp.R
import com.aymen.translatorapp.feature_translator.presentation.components.LanguagesDialog
import com.aymen.translatorapp.feature_translator.presentation.components.MatchItem
import com.aymen.translatorapp.ui.theme.Orange
import com.aymen.translatorapp.ui.theme.SectionColor

@Composable
fun TranslatorScreen(
    state: TranslatorState,
    onEvent: (TranslatorEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val languages = listOf(
        Lang.English,
        Lang.Arabic,
        Lang.Spanish,
        Lang.Japanese,
    )

    var sourceChange by remember {
        mutableStateOf(false)
    }

    var dialogOpen by remember {
        mutableStateOf(false)
    }

    if (dialogOpen) {
        LanguagesDialog(
            languages = languages,
            onClick = {
                if (sourceChange) {
                    onEvent(TranslatorEvent.OnSourceChange(it))
                } else {
                    onEvent(TranslatorEvent.OnTargetChange(it))
                }
                dialogOpen = false
            },
            onDialogDismiss = { dialogOpen = false }
        )
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Card(
                elevation = CardDefaults.cardElevation(16.dp),
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxSize(),
                colors = CardDefaults.cardColors(containerColor = SectionColor)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .clickable {
                                sourceChange = true
                                dialogOpen = true
                            }
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape),
                            painter = painterResource(id = state.source.icon),
                            contentScale = ContentScale.Crop,
                            contentDescription = state.source.name
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = state.source.name,
                            color = Color.White,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    IconButton(
                        onClick = {
                            val source = state.source
                            val target = state.target
                            onEvent(TranslatorEvent.OnSourceChange(target))
                            onEvent(TranslatorEvent.OnTargetChange(source))
                        }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_swap),
                            contentDescription = null,
                            tint = Orange,
                            modifier = Modifier.size(48.dp)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .clickable {
                                sourceChange = false
                                dialogOpen = true
                            }
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape),
                            painter = painterResource(id = state.target.icon),
                            contentScale = ContentScale.Crop,
                            contentDescription = state.target.name
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = state.target.name,
                            color = Color.White,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
        item {
            Card(
                elevation = CardDefaults.cardElevation(16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .height(200.dp),
                colors = CardDefaults.cardColors(containerColor = SectionColor)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp, start = 10.dp)
                ) {
                    state.query.let { query ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = state.source.name,
                                color = Color.White,
                                style = MaterialTheme.typography.bodyLarge
                            )
                            IconButton(onClick = {
                                onEvent(TranslatorEvent.Clear)
                            }) {
                                Icon(
                                    contentDescription = "Clear Text",
                                    imageVector = Icons.Default.Close,
                                    tint = Color.White
                                )
                            }
                        }
                        Box {
                            BasicTextField(
                                value = query,
                                onValueChange = { newQuery ->
                                    onEvent(TranslatorEvent.Translate(newQuery))
                                },
                                textStyle = MaterialTheme.typography.bodyLarge.copy(
                                    color = Color.White
                                ),
                            )
                            if (state.query.isEmpty()) {
                                Text(
                                    text = "Your Text...",
                                    color = Color.Gray,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                    }
                }
            }
        }
        item {
            Card(
                elevation = CardDefaults.cardElevation(16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .height(200.dp),
                colors = CardDefaults.cardColors(containerColor = SectionColor)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            start = 10.dp,
                            end = 10.dp,
                            top = 16.dp
                        )
                ) {
                    Text(
                        text = state.target.name, color = Color.White,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    when {
                        state.isLoading -> {
                            Text(text = "Translating...", color = Color.Gray)
                        }

                        else -> {
                            state.translation?.let {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(1f),
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = it.responseData.translatedText,
                                        color = Color.White,
                                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                                    )
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.End
                                    ) {
                                        IconButton(onClick = { context.copyToClipboard(it.responseData.translatedText) }) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.ic_copy),
                                                contentDescription = null,
                                                tint = Color.White
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        item {
            AnimatedVisibility(
                visible = state.translation != null,
                enter = fadeIn() + scaleIn(),
                exit = fadeOut() + scaleOut()
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    state.translation?.let {
                        repeat(it.matches.count()) { index ->
                            val match = it.matches[index]
                            MatchItem(
                                match = match,
                                modifier = Modifier
                                    .clickable {
                                        context.copyToClipboard(match.translation)
                                    }
                            )
                        }
                    }
                }
            }
        }

    }
}

private fun Context.copyToClipboard(text: String) {
    val clipboardManager = this.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clipData = ClipData.newPlainText("Translated Text", text)
    clipboardManager.setPrimaryClip(clipData)
    Toast.makeText(this, "Copied to Clipboard.", Toast.LENGTH_SHORT).show()
}