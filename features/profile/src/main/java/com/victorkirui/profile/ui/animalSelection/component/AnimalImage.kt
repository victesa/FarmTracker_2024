package com.victorkirui.profile.ui.animalSelection.component

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun RoundImageComponent(
    animalImage: AsyncImagePainter,
    animalName: String,
    fontSize: TextUnit,
    imageSize: Dp,
    onClick:() -> Unit,
){
    var clicked by rememberSaveable {
        mutableStateOf(false)
    }

    Column(modifier = Modifier
        .clickable{
                  clicked = !clicked

            onClick()
        },
        horizontalAlignment = Alignment.CenterHorizontally) {
        if (clicked){
            ImageSelected(animalImage = animalImage, imageSize = imageSize)
        }else{
            ImageUnselected(animalImage = animalImage, imageSize = imageSize)
        }

        Text(text = animalName, fontSize = fontSize)
    }

}

@Composable
fun ShimmerListItem(
    animalUrl: String,
    onClick: () -> Unit,
    animalName: String,
    fontSize: TextUnit,
    imageSize: Dp
) {
    var isLoading by rememberSaveable {
        mutableStateOf(true)
    }

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(animalUrl)
            .size(Size.ORIGINAL)
            .build()
    )

    if (painter.state is AsyncImagePainter.State.Success) {
        isLoading = false
    }

    if (isLoading) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            ImageIsLoading(imageSize = imageSize)
            Box(modifier = Modifier
                .size(width = 40.dp, height = 10.dp)
                .shimmerEffect())
        }
    } else {
        RoundImageComponent(
            animalImage = painter,
            animalName = animalName,
            fontSize = fontSize,
            imageSize = imageSize,
            onClick = onClick
        )
    }
}



@Composable
fun ImageIsLoading(imageSize: Dp){
    Box(modifier = Modifier
        .size(imageSize)
        .clip(CircleShape)
        .shimmerEffect())

}

@Composable
fun ImageSelected(
    animalImage: AsyncImagePainter,
    imageSize: Dp){
    Image(painter = animalImage, contentDescription = "Cow",
        modifier = Modifier
            .size(imageSize)
            .clip(shape = CircleShape)
            .border(2.dp, Color.Green, CircleShape),
        contentScale = ContentScale.Crop)
}

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition(label = "")
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        ), label = ""
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFB8B5B5),
                Color(0xFF8F8B8B),
                Color(0xFFB8B5B5),
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    )
        .onGloballyPositioned {
            size = it.size
        }
}

@Composable
fun ImageUnselected(
    animalImage: AsyncImagePainter,
    imageSize: Dp){
    Image(painter = animalImage, contentDescription = "Cow",
        modifier = Modifier
            .size(imageSize)
            .clip(shape = CircleShape),
        contentScale = ContentScale.Crop)
}

@Composable
@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
internal fun Preview(){
}