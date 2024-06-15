package com.aoi.presentation.home.ingredient_bulk

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
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
    vm: BulkIngredientViewModel = viewModel()
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
        content = {
            paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                CategoryList(
                    categoryList = categoryList.value,
                    selectedCategory = selectedCategory.value,
                    onSelected = { vm.onChangedCategory(it) }
                )
                Spacer(modifier = Modifier.height(50.dp))
                IngredientList(onNavigateForIngredientDetail)
            }
        }
    )
}

/**
 * カテゴリーリスト
 *
 * @param categoryList カテゴリーリスト
 * @param selectedCategory 選択されたカテゴリー
 * @param onSelected カテゴリー選択時の処理
 */
@Composable
fun CategoryList(
    categoryList: List<String>,
    selectedCategory: String,
    onSelected: (String) -> Unit){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = "カテゴリー",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .horizontalScroll(rememberScrollState()),
            verticalAlignment = Alignment.CenterVertically
        ) {
            categoryList.forEach {
                val isSelected = it == selectedCategory
                val buttonContainerColor = if(isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
                val buttonContentColor = if(isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground
                OutlinedButton(
                    onClick = { onSelected(it) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonContainerColor,
                        contentColor = buttonContentColor
                    ),
                    shape = RoundedCornerShape(50),
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 4.dp)
                ) {
                    Text(
                        it,
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.align(Alignment.CenterVertically))
                }
            }
        }
    }
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
    Text(
        modifier = Modifier.padding(start = 8.dp),
        text = "食材",
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold)
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
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .wrapContentHeight()
            .padding(top = 12.dp, bottom = 12.dp),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
            disabledContainerColor = CardDefaults.cardColors().disabledContainerColor,
            disabledContentColor = CardDefaults.cardColors().disabledContentColor
        ),
        onClick = onNavigateForIngredientDetail
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_vegetable_thumbnail),
                    contentDescription = "image",
                    modifier = Modifier.size(40.dp)
                )
                Text(
                    text = name,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "期限 : 2024/11/22",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface)
                Text(
                    text = "通知 : 1日前",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface)
                Row {
                    TagCard("お気に入り")
                    Spacer(modifier = Modifier.width(2.dp))
                    TagCard("お肉")
                }
                TagCard("いいね")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = "Add",
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .size(36.dp)
                    .background(Color.Transparent)
            )
        }
    }
}

/**
 * タグカード
 *
 * @param tag タグ名
 */
@Composable
fun TagCard(tag: String) {
    Card(
        shape = RoundedCornerShape(50), // 丸みを帯びた角
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
            disabledContentColor = CardDefaults.cardColors().disabledContentColor,
            disabledContainerColor = CardDefaults.cardColors().disabledContainerColor
        ),
        modifier = Modifier
            .width(80.dp)
            .height(24.dp)
            .padding(top = 2.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = tag,
                color = MaterialTheme.colorScheme.onPrimary, // テキストの色
                style = MaterialTheme.typography.labelMedium, // テキストスタイル
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