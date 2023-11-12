package com.example.hellofigma

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hellofigma.hint1.Hint1
import com.google.relay.compose.RelayContainer
import com.google.relay.compose.RelayImage

@Composable
fun HintFlipPage(
    modifier: Modifier = Modifier,
    challenge: String = "",
    count: String = "",
    onVector: () -> Unit = {},
    onUnlock: () -> Unit = {},
    initialImageId:  Int = R.drawable.hint_1_vector,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Hint1(
            modifier = modifier,
            challenge= challenge,
            count = count,
            onUnlock = onUnlock
        )
        Vector1(onVector = onVector,
                modifier= modifier,
                initialImageId=initialImageId)

    }


}
@Preview(widthDp = 387, heightDp = 793)
@Composable
private fun Hint1Preview() {
    MaterialTheme {
        RelayContainer {
            HintFlipPage(
                onVector = {},
                onUnlock = {},
                challenge = "Some challenge",
                count = "10",
                modifier = Modifier.rowWeight(1.0f).columnWeight(1.0f),
                initialImageId = R.drawable.hint_1_vector1,
            )
        }
    }
}

@Composable
fun Vector1(
    onVector: () -> Unit,
    modifier: Modifier = Modifier,
    initialImageId: Int = R.drawable.hint_1_vector1
) {
    var pictureID = initialImageId

    RelayImage(
        image = painterResource(pictureID),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .padding(
                paddingValues = PaddingValues(
                    start = 82.0.dp,
                    top = 183.0.dp,
                    end = 82.8408203125.dp,
                    bottom = 364.0927734375.dp
                )
            )
            .clickable {
                // On click, update the image ID
                pictureID = initialImageId
                onVector.invoke()
            }
            .requiredWidth(222.1591796875.dp)
            .requiredHeight(247.9072265625.dp)
            .fillMaxWidth(1.0f)
            .fillMaxHeight(1.0f)
    )
}


