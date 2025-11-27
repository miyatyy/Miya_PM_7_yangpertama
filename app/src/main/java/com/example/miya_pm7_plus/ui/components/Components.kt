package com.example.miya_pm7_plus.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.Dp
import com.example.miya_pm7_plus.ui.theme.AppShapes

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    height: Dp = 48.dp
) {
    ElevatedButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        shape = AppShapes.medium
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun RoundedCard(
    modifier: Modifier = Modifier,
    elevation: Dp = 6.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = AppShapes.large,
        elevation = CardDefaults.cardElevation(defaultElevation = elevation)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            content = content
        )
    }
}

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    singleLine: Boolean = true,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = singleLine,
        shape = AppShapes.medium,
        modifier = modifier.fillMaxWidth()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        navigationIcon = navigationIcon,
        actions = actions
    )
}
