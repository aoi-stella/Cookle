package com.aoi.utility.ui.user_field

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

/**
 * ユーザー入力欄
 *
 * @param parameterName 項目名
 * @param value 入力された値
 * @param leadingIconId 入力欄の先頭に表示するアイコンのリソースID
 * @param onValueChange 入力された値が変更されたときのコールバック
 * @param isPasswordField パスワード入力欄かどうか
 */
@Composable
fun CookleUserInputField(
    parameterName: String,
    value: String,
    leadingIconId: Int,
    onValueChange: (String) -> Unit,
    isPasswordField: Boolean = false,
) {
    val keyboardOptions = if(isPasswordField){
        KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password)
    }else{
        KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Text)
    }

    val visualTransformation = if(isPasswordField){
        PasswordVisualTransformation()
    }
    else{
        VisualTransformation.None
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 16.dp),
        value = value,
        onValueChange = { onValueChange(it) },
        colors = OutlinedTextFieldDefaults.colors(),
        leadingIcon = {
            Icon(
                painter = painterResource(leadingIconId),
                contentDescription = "Icon",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
        },
        label = { Text(parameterName) },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions(onDone = { /* テキストフィールドのフォーカスを外す処理 */ })
    )
}