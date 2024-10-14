package com.aoi.presentation.home.ingredient_bulk

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aoi.presentation.R

/**
 * 食材一括管理画面
 *
 * @param vm ViewModel
 * @param onNavigateForIngredientDetail 食材詳細画面への遷移
 * @param onNavigateForAddIngredient 食材追加画面への遷移
 */
@Composable
fun BulkIngredientUI(
    onNavigateForIngredientDetail: () -> Unit,
    onNavigateForAddIngredient: () -> Unit,
    vm: BulkIngredientViewModel = hiltViewModel()
) {
    val selectedCategory = vm.selectedCategory.collectAsState()
    val categoryList = vm.categoryList.collectAsState()
    val updateIngredient = vm.updateIngredientCategory.collectAsState()

    LaunchedEffect(key1 = updateIngredient){
        if(updateIngredient.value){
            vm.updateIngredientCategory()
        }
    }

    Scaffold(
        topBar = { Header() },
        content = {
            paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                IngredientList(onNavigateForIngredientDetail)
            }
        }
    )
}

/**
 * 食材リスト
 *
 * @param onNavigateForIngredientDetail 食材詳細画面への遷移
 */
@Composable
fun IngredientList(
    onNavigateForIngredientDetail: () -> Unit
){
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items((1..10).map { "モケラ $it 号" }) { name ->
            IngredientCard(name, onNavigateForIngredientDetail)
        }
    }
}

/**
 * 食材カード
 *
 * @param name 食材名
 * @param onNavigateForIngredientDetail 食材詳細画面への遷移
 */
@Composable
fun IngredientCard(
    name: String,
    onNavigateForIngredientDetail: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .padding(top = 12.dp, bottom = 12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            // 食材画像
            Image(
                painter = painterResource(id = R.drawable.ic_vegetable_thumbnail),
                contentDescription = "Grilled Lemon Herb Chicken",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                // 食材名
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                // 説明
                Text(
                    text = "Juicy grilled chicken marinated in a flavorful blend of lemon juice, herbs...",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // 賞味期限
                    Text(
                        text = "$12.32",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    // 詳細遷移ボタン
                    Button(
                        onClick = { onNavigateForIngredientDetail() },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("詳細", color = MaterialTheme.colorScheme.onPrimary)
                    }
                }
            }
        }
    }
}

/**
 * ヘッダー
 */
@Composable
fun Header(){
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 8.dp
    ) {
        Column(
            modifier = Modifier.padding(start = 12.dp, top = 36.dp, end = 12.dp, bottom = 12.dp)
        ) {
            Text(
                text = "リスト",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

/**
 * プレビュー
 */
@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun Preview() {
    BulkIngredientUI({},{})
}