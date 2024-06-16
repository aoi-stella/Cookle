package com.aoi.presentation.home.ingredient_detail

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aoi.presentation.R

/**
 * 食材詳細画面
 *
 * @param vm ViewModel
 */
@Composable
fun IngredientDetailUI(
    vm: IngredientDetailViewModel = viewModel()
) {
    val enabledNotify = vm.enabledNotify.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Start),
            text = "詳細情報",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineMedium)
        InfoSpaceDivider()
        AlertInfoCard(
            isSwitchEnabled = enabledNotify.value,
            onChangedSwitch = { vm.onChangedEnabledNotify(it) }
        )
        InfoSpaceDivider()
        IngredientDetail()
    }
}

/**
 * 領域分割
 */
@Composable
fun InfoSpaceDivider(){
    Spacer(modifier = Modifier.height(18.dp))
}

/**
 * アラートカード
 *
 * @param isSwitchEnabled スイッチの有効無効
 * @param onChangedSwitch スイッチの変更時の処理
 */
@Composable
fun AlertInfoCard(
    isSwitchEnabled: Boolean,
    onChangedSwitch: (Boolean) -> Unit
) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Left,
        text = "アラーム設定情報",
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.headlineSmall)
    Column(modifier = Modifier.wrapContentHeight()) {
        Card(
            modifier = Modifier
                .padding(top = 12.dp)
                .width(325.dp)
                .wrapContentHeight(),
            elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(modifier = Modifier.padding(top = 12.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_circle_notifications_24),
                        contentDescription = "icon",
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp)) // アイコンとテキストの間にスペースを追加
                    Text(
                        text = "アラームについて",
                        fontSize = MaterialTheme.typography.titleMedium.fontSize
                    )
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, start = 16.dp),
                    verticalAlignment = Alignment.CenterVertically){
                    Text(
                        text = "ON/OFF",
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                    Switch(
                        checked = isSwitchEnabled,
                        onCheckedChange = { onChangedSwitch(it) },
                        modifier = Modifier.weight(1f))
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp, start = 16.dp),
                    verticalAlignment = Alignment.CenterVertically){
                    Text(
                        text = "通知日時",
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "2024/12/1 12:00",
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary)
                ) {
                    Text(
                        text = "アラームの日時設定を行う",
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        textAlign = TextAlign.Center)
                }
            }
        }
    }
}

/**
 * 食材情報
 */
@Composable
fun IngredientDetail(){
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        textAlign = TextAlign.Left,
        text = "食材情報",
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.headlineSmall)
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 12.dp)) {
        DetailInfoItem("食材名", "トマト")
        DetailInfoItem("賞味期限", "2024/12/25")
    }
}

/**
 * 項目と値を表示する
 *
 * @param itemName 項目名
 * @param itemValue 値
 */
@Composable
fun DetailInfoItem(itemName: String, itemValue: String){
    Column{
        Text(
            text = itemName,
            fontSize = MaterialTheme.typography.bodySmall.fontSize)
        Text(
            text =itemValue,
            fontSize = MaterialTheme.typography.titleLarge.fontSize)
    }
    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    )
}

/**
 * 食材詳細画面のプレビュー
 */
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun PreviewIngredientDetailUI() {
    IngredientDetailUI()
}
