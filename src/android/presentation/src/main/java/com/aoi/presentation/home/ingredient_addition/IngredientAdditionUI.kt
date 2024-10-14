package com.aoi.presentation.home.ingredient_addition

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
fun IngredientAddition(
    onNavigateForIngredientDetail: () -> Unit,
    onNavigateForAddIngredient: () -> Unit,
    vm: IngredientAdditionViewModel = hiltViewModel()
) {
    val selectedCategory by vm.selectedCategory.collectAsState()
    val categoryList by vm.categoryList.collectAsState()
    val updateIngredientCategory by vm.updateIngredientCategory.collectAsState()
    val contentList by vm.contentList.collectAsState()
    val state = IngredientAdditionScreenState(
        selectedCategory = selectedCategory,
        categoryList = categoryList,
        updateIngredient = updateIngredientCategory,
        contentList = contentList
    )

    val event = IngredientAdditionScreenEvent(
        updateIngredientCategory = { vm.updateIngredientCategory() },
        onChangedCategory = { vm.onChangedCategory(it) },
        onClickContentCard = { onNavigateForIngredientDetail() },
    )

    IngredientAddition(state, event)
}

@Composable
fun IngredientAddition(
    state: IngredientAdditionScreenState,
    event: IngredientAdditionScreenEvent
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
                    .background(MaterialTheme.colorScheme.surface)
                    .verticalScroll(rememberScrollState())
            ) {
                state.categoryList.forEach {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = it,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.fillMaxWidth()
                    )
                    ContentRow(it, state.contentList, event.onClickContentCard)
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    )
}

@Composable
fun ContentRow(contentKind: String,
               contentsList: List<ContentCard>,
               onClickContentCard: () -> Unit
) {
    val displayContentsList = contentsList.filter { it.category == contentKind }
    if(displayContentsList.isNotEmpty()){
        LazyRow(
            modifier =Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            items(contentsList.size) { index ->
                if(contentsList[index].category == contentKind) {
                    ContentCard(contentsList[index], onClickContentCard)
                }
            }
        }
    }
    else {
        Text(
            text = "データを新規追加してください",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ContentCard(contentCard: ContentCard, onClickContentCard: () -> Unit) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.clickable { onClickContentCard() }
    ) {
        val imageSize = 100.dp
        Column(
            modifier = Modifier
                .padding(8.dp)
                .wrapContentWidth()
        ) {
            var painter = painterResource(R.drawable.ic_content_card_icon_default)
            var colorFilter: ColorFilter? = null
            var imageModifier = Modifier.size(imageSize)
                .clip(RoundedCornerShape(16.dp))

            if(contentCard.iconURL.isEmpty()){
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onTertiaryContainer)
                painter = painterResource(R.drawable.ic_content_card_icon_default)
                imageModifier = imageModifier
                    .background(MaterialTheme.colorScheme.tertiaryContainer)
            }
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                colorFilter = colorFilter,
                modifier = imageModifier
            )
            Text(
                modifier = Modifier
                    .width(imageSize)
                    .padding(top = 8.dp),
                text = contentCard.title,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2,
            )
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
    showSystemUi = false,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun Preview() {
    val state = IngredientAdditionScreenState(
        selectedCategory = "全て",
        categoryList = listOf("全て", "野菜", "果物", "肉", "魚", "乳製品", "穀物", "その他"),
        updateIngredient = true,
        contentList = listOf(
            ContentCard("トマト", "", "野菜"),
            ContentCard("じゃがいも", "", "野菜"),
            ContentCard("人参", "", "野菜"),
            ContentCard("パセリ", "", "野菜"),
            ContentCard("ハンバーグ用牛肉", "", "肉"),
            ContentCard("挽肉", "", "肉"),
            ContentCard("ステーキ用フィレステーキ", "", "肉"),
            ContentCard("牛乳", "", "乳製品"),
            ContentCard("チェダーチーズ", "", "乳製品"),
            ContentCard("カマンベールチーズ", "", "乳製品"),
            ContentCard("ラクレット", "", "乳製品"),
            ContentCard("鯵", "", "魚"),
            ContentCard("秋刀魚", "", "魚"),
            ContentCard("鮭", "", "魚"),
            ContentCard("浅利", "", "魚"),
        )
    )
    val event = IngredientAdditionScreenEvent(
        updateIngredientCategory = {},
        onChangedCategory = {},
        onClickContentCard = {}
    )
    IngredientAddition(state,event)
}