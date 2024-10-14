package com.aoi.presentation.home.ingredient_bulk

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * 食材一括管理画面
 *
 * @param vm ViewModel
 * @param onNavigateForIngredientDetail 食材詳細画面への遷移
 * @param onNavigateForAddIngredient 食材追加画面への遷移
 */
@Composable
fun BulkIngredient(
    onNavigateForIngredientDetail: () -> Unit,
    onNavigateForAddIngredient: () -> Unit,
    vm: BulkIngredientViewModel = hiltViewModel()
) {
    val selectedCategory by vm.selectedCategory.collectAsState()
    val categoryList by vm.categoryList.collectAsState()
    val updateIngredientCategory by vm.updateIngredientCategory.collectAsState()
    val state = BulkIngredientScreenState(
        selectedCategory = selectedCategory,
        categoryList = categoryList,
        updateIngredient = updateIngredientCategory
    )

    val event = BulkIngredientScreenEvent(
        updateIngredientCategory = { vm.updateIngredientCategory() },
        onChangedCategory = { vm.onChangedCategory(it) }
    )

    BulkIngredient(state, event)
}

@Composable
fun BulkIngredient(
    state: BulkIngredientScreenState,
    event: BulkIngredientScreenEvent
) {

    LaunchedEffect(key1 = state.updateIngredient){
        if(state.updateIngredient){
            event.updateIngredientCategory()
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
            }
        }
    )
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
    showSystemUi = false,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun Preview() {
    val state = BulkIngredientScreenState(
        selectedCategory = "全て",
        categoryList = listOf("全て", "野菜", "肉", "魚", "卵", "乳製品", "穀物", "豆", "果物"),
        updateIngredient = true
    )
    val event = BulkIngredientScreenEvent(
        updateIngredientCategory = {},
        onChangedCategory = {}
    )
    BulkIngredient(state,event)
}