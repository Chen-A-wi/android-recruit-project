package `in`.hahow.android_recruit_project.ui.widgets

import androidx.annotation.StringRes
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.graphics.toColorInt
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hahow.data.local.CourseStateType
import com.hahow.data.local.ItemData
import com.hahow.data.local.SubscriptType
import `in`.hahow.android_recruit_project.R

@Composable
fun ItemView(
    course: ItemData
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
    ) {
        Column(
            modifier = Modifier.width(140.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            ImageCover(
                modifier = Modifier
                    .weight(2f)
                    .align(Alignment.CenterHorizontally),
                course = course
            )
            ProgressBarLayout(modifier = Modifier.weight(1f), course = course)
        }
        Column(
            Modifier.fillMaxWidth()
        ) {
            ItemContent(course)
        }
    }
}

@Composable
fun ImageCover(modifier: Modifier, course: ItemData) {
    val tagSubscriptVisible by remember { mutableStateOf(course.subscripts.isVisible) }

    Box(
        modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp),
    ) {
        AsyncImage(
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.clip(
                shape = RoundedCornerShape(10.dp)
            ),
            model = ImageRequest.Builder(LocalContext.current)
                .data(course.imgUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
        )
        if (tagSubscriptVisible) {
            Text(
                text = stringResource(
                    if (course.subscripts.type == SubscriptType.BY) {
                        R.string.cp_by
                    } else {
                        R.string.cp_review_before
                    }, course.subscripts.cpValue
                ),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .clip(
                        shape = RoundedCornerShape(topStart = 10.dp, bottomEnd = 10.dp)
                    )
                    .background(Color.DarkGray)
                    .padding(horizontal = 4.dp),
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun ProgressBarLayout(modifier: Modifier, course: ItemData) {
    Row(
        modifier = modifier
            .padding(start = 8.dp)
            .fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        LinearProgressIndicator(
            modifier = Modifier
                .weight(2f)
                .align(Alignment.CenterVertically)
                .clip(
                    shape = RoundedCornerShape(6.dp)
                ),
            progress = { course.progressValue },
            color = Yellow,
            trackColor = Color.DarkGray,
            strokeCap = ProgressIndicatorDefaults.LinearStrokeCap
        )

        Text(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
            textAlign = TextAlign.Center,
            text = stringResource(
                R.string.cp_progress_percentage,
                course.progressNum
            ),
            fontSize = 12.sp
        )
    }
}

@Composable
fun ItemContent(course: ItemData) {
    val tagTenantVisible by remember { mutableStateOf(course.isTenant) }
    val tagCourseStateVisible by remember { mutableStateOf(course.courseState != CourseStateType.NONE) }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(bottom = 8.dp, top = 8.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 8.dp),
            text = course.title,
            style = MaterialTheme.typography.titleMedium,
        )

        ConstraintLayout(
            modifier = Modifier.fillMaxWidth()
        ) {
            val (dueDateId, tagCourseStateId, tagTenantId, icMoreId) = remember {
                createRefs()
            }

            Text(
                text = "2023-07-14 通過",
                fontSize = 12.sp,
                modifier = Modifier.constrainAs(dueDateId) {
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                })

            if (tagCourseStateVisible) {
                Text(
                    text = stringResource(
                        id = if (course.courseState == CourseStateType.COMPULSORY) {
                            R.string.lab_compulsory
                        } else {
                            R.string.lab_elective
                        }
                    ),
                    fontWeight = FontWeight.Bold,
                    color = if (course.courseState == CourseStateType.COMPULSORY) {
                        Color("#b62d3f".toColorInt())
                    } else {
                        MaterialTheme.colorScheme.onSecondaryContainer
                    },
                    fontSize = 12.sp,
                    modifier = Modifier.constrainAs(tagCourseStateId) {
                        start.linkTo(dueDateId.end, margin = 8.dp)
                        bottom.linkTo(parent.bottom)
                    })
            }

            if (tagTenantVisible) {
                Text(
                    text = stringResource(id = R.string.lab_tenant),
                    fontWeight = FontWeight.Bold,
                    color = Color("#c2ecf8".toColorInt()),
                    fontSize = 12.sp,
                    modifier = Modifier.constrainAs(tagTenantId) {
                        start.linkTo(tagCourseStateId.end, margin = 8.dp)
                        bottom.linkTo(parent.bottom)
                    })
            }

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
