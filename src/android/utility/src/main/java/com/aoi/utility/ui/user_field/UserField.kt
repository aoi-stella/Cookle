package com.aoi.utility.ui.user_field

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

/**
 * ユーザー入力欄
 *
 * @param parameterName 項目名
 * @param value 入力された値
 * @param leadingIconId 入力欄の先頭に表示するアイコンのリソースID
 * @param onValueChange 入力された値が変更されたときのコールバック
 */
@Composable
fun UserInputField(
    parameterName: String,
    value: String,
    leadingIconId: Int,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 16.dp),
        value = value,
        onValueChange = { onValueChange(it) },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedTextColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.primary
        ),
        leadingIcon = {
            Icon(
                painter = painterResource(leadingIconId),
                contentDescription = "Icon",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
        },
        label = { Text(parameterName) }
    )
}