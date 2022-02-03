package com.bayu.instagramhomepage.ui.home

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bayu.instagramhomepage.R
import com.bayu.instagramhomepage.ui.MainViewModel
import com.bayu.instagramhomepage.ui.theme.InstagramHomePageTheme

internal val colorsInstagram = listOf(
    Color(0xFFDD2A7B),
    Color(0xFFFEDA77),
    Color(0xFFF58529),
)

@Composable
fun HomeScreen(
    mainViewModel: MainViewModel = viewModel(),
) {
    Column {
        TopAppBar()
        HomeContent(
            onShowBottomSheet = { mainViewModel.showBottomSheet() },
            onHideBottomSheet = { /* TODO hide bottom sheet */ }
        )
    }
}

@Composable
fun TopAppBar() {
    AppBar {
        IconButton(imageVector = Icons.Outlined.AddBox)
        IconButton(imageVector = Icons.Outlined.FavoriteBorder)
        IconButton(imageVector = Icons.Outlined.ChatBubbleOutline)
    }
}

@Composable
fun HomeContent(
    onShowBottomSheet: () -> Unit,
    onHideBottomSheet: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        item {
            Stories()
        }
        item {
            Divider(modifier = Modifier.padding(top = 12.dp))
        }
        items(20) {
            Post(
                onShowBottomSheet = onShowBottomSheet,
            )
        }
    }
}

@Composable
fun Post(
    onShowBottomSheet: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth()
    ) {
        PostHeader(
            onMorePostClicked = onShowBottomSheet,
            name = "stevdza_san",
            image = painterResource(id = R.drawable.image),
        )
        PostContent(
            image = painterResource(id = R.drawable.image)
        )
        PostFooter()
    }
}

@Composable
fun PostHeader(
    onMorePostClicked: () -> Unit,
    name: String,
    image: Painter,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .padding(start = 16.dp, end = 0.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CircleBackground(
                modifier = Modifier.size(35.dp),
                border = BorderStroke(
                    width = 2.dp,
                    brush = Brush.verticalGradient(colorsInstagram)
                )
            ) {
                Surface(
                    modifier = Modifier
                        .padding(3.dp),
                    shape = CircleShape,
                    border = BorderStroke(
                        1.dp,
                        MaterialTheme.colors.onSurface.copy(alpha = 0.12f)
                    ),
                ) {
                    Image(
                        painter = image,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = name,
                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.SemiBold)
            )
        }
        IconButton(imageVector = Icons.Outlined.MoreVert, onClick = onMorePostClicked)
    }
}

@Composable
fun PostContent(
    image: Painter,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = image,
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth(),
        contentScale = ContentScale.FillWidth,
    )
}

@Composable
fun ColumnScope.PostFooter() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row {
            Icon(
                imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = null,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                imageVector = Icons.Outlined.ChatBubbleOutline,
                contentDescription = null,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                imageVector = Icons.Outlined.Send,
                contentDescription = null,
                modifier = Modifier.size(28.dp)
            )
        }
        Icon(
            imageVector = Icons.Outlined.BookmarkBorder,
            contentDescription = null,
            modifier = Modifier.size(28.dp)
        )
    }
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .animateContentSize(
                animationSpec = tween(600),
            ),
    ) {
        var isShowMore by remember { mutableStateOf(false) }

        Text(text = "1,432 likes", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(4.dp))
        Row {
            val description = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )
                ) {
                    append("samehadaku.care")
                }
                append(" ")
                append("is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.")
            }
            if (isShowMore) {
                Column(
                    modifier = Modifier.weight(1F),
                ) {
                    Text(
                        text = description,
                        fontSize = 14.sp,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(
                            text = "less",
                            modifier = Modifier
                                .clickable { isShowMore = false },
                            fontSize = 14.sp
                        )
                    }
                }
            } else {
                Text(
                    text = description,
                    modifier = Modifier.weight(1F),
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = "more",
                        modifier = Modifier
                            .clickable { isShowMore = true },
                        fontSize = 14.sp
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = "View All 53 comments",
                fontSize = 14.sp,
                fontWeight = FontWeight.W500,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "2 days aog", fontSize = 12.sp)
        }
    }
}

@Composable
fun Stories() {
    LazyRow {
        item {
            YourStory()
        }
        items(10) {
            Story(
                name = "neojapan_",
                painter = painterResource(id = R.drawable.image)
            )
        }
    }
}

@Composable
fun Story(
    name: String,
    painter: Painter,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(horizontal = 8.dp)
    ) {
        CircleBackground(
            modifier = Modifier.size(75.dp),
            border = BorderStroke(
                width = 2.dp,
                brush = Brush.verticalGradient(colorsInstagram)
            )
        ) {
            Surface(
                modifier = Modifier
                    .padding(6.dp),
                shape = CircleShape,
                border = BorderStroke(1.dp, Color.LightGray),
            ) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        LabelStory(text = name)
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    InstagramHomePageTheme {
        HomeScreen()
    }
}
