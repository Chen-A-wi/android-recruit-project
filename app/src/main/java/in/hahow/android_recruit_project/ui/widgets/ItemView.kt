package `in`.hahow.android_recruit_project.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import `in`.hahow.android_recruit_project.ui.theme.AndroidrecruitprojectTheme

@Composable
fun ItemView(
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(110.dp),
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            ImageCover(
                Modifier
                    .weight(2f)
                    .align(Alignment.CenterHorizontally)
            )
            ProgressBarLayout(Modifier.weight(1f))
        }
        Column(
            Modifier.weight(2f)
        ) {
            ItemContent()
        }
    }
}

@Composable
fun ImageCover(modifier: Modifier) {
    Box(
        modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp),
    ) {
        AsyncImage(
            contentScale = ContentScale.Fit,
            modifier = Modifier.clip(
                shape = RoundedCornerShape(10.dp)
            ),
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://imgur.com/7LlswEL.png")
                .crossfade(true)
                .build(),
            contentDescription = null,
        )
        Text(
            text = "企業課程",
            modifier = Modifier
                .align(Alignment.TopStart)
                .clip(
                    shape = RoundedCornerShape(topStart = 10.dp, bottomEnd = 10.dp)
                )
                .background(Color.Red),
            fontSize = 12.sp
        )
        Text(
            text = "8天前觀看",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .clip(
                    shape = RoundedCornerShape(topStart = 10.dp, bottomEnd = 10.dp)
                )
                .background(Color.Red),
            fontSize = 12.sp
        )
    }
}

@Composable
fun ProgressBarLayout(modifier: Modifier) {
    Row(
        modifier = modifier
            .padding(start = 8.dp)
            .fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        LinearProgressIndicator(
            modifier = Modifier
                .weight(2f)
                .align(Alignment.CenterVertically).clip(
                    shape = RoundedCornerShape(6.dp)
                ),
            progress = { 0.2f },
            color = Yellow,
            trackColor = Color.DarkGray,
            strokeCap = ProgressIndicatorDefaults.LinearStrokeCap
        )

        Text(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
            textAlign = TextAlign.Center,
            text = "50%",
            fontSize = 12.sp
        )
    }
}

@Composable
fun ItemContent() {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            text = "百萬 YouTuber 攻心剪輯術：後製技巧與 YouTube 經營心法"
        )

        ConstraintLayout(
            modifier = Modifier.fillMaxWidth()
        ) {
            val (dueDateId, tagTitleStatusId, icMoreId) = remember {
                createRefs()
            }

            Text(
                text = "2023-07-14 通過",
                fontSize = 12.sp,
                modifier = Modifier.constrainAs(dueDateId) {
                    start.linkTo(parent.start, margin = 8.dp)
                    bottom.linkTo(parent.bottom)
                })

            Text(
                text = "必修",
                fontSize = 12.sp,
                modifier = Modifier.constrainAs(tagTitleStatusId) {
                    start.linkTo(dueDateId.end, margin = 8.dp)
                    bottom.linkTo(parent.bottom)
                })

            Icon(
                modifier = Modifier.constrainAs(icMoreId) {
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
                imageVector = Icons.Filled.MoreVert,
                tint = MaterialTheme.colorScheme.outline,
                contentDescription = "More",
            )
        }
    }
}

@Preview(showBackground = true, heightDp = 600)
@Composable
fun ItemViewPreview() {
    AndroidrecruitprojectTheme {
        ItemView(Modifier)
    }
}
