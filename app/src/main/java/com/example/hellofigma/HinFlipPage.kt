package com.example.hellofigma

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.hellofigma.hint1.Hint1
import com.google.relay.compose.BoxScopeInstance.boxAlign
import com.google.relay.compose.RelayContainer
import com.google.relay.compose.RelayImage

@Composable
fun HintFlipPage(
    hintState: HintState,
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
        HintImage(onVector = onVector,
                modifier= Modifier.boxAlign(
                    alignment = Alignment.TopStart,
                    offset = DpOffset(
                        x = 82.0.dp,
                        y = 181.0.dp
                    )
                ),
                initialImageId=initialImageId)

    }


}
@Preview(widthDp = 387, heightDp = 793)
@Composable
private fun HintFlipPreview() {
    MaterialTheme {
        RelayContainer {
            HintFlipPage(
                hintState = HintState(
                    onOpen1 = false,
                    onOpen2 = false,
                    onOpen3 = false,
                    onOpen4 = false
                ),
                onVector = {},
                onUnlock = {},
                challenge = "Some challenge",
                count = "10",
                modifier = Modifier.rowWeight(1.0f).columnWeight(1.0f),
                initialImageId = R.drawable.hint_1_vector,
            )
        }
    }
}

@Composable
fun HintImage(
    onVector: () -> Unit,
    modifier: Modifier = Modifier,
    initialImageId: Int = R.drawable.hint_1_vector
) {
    var pictureID = initialImageId

    RelayImage(
        image = painterResource(pictureID),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .clickable {
                // On click, update the image ID
                pictureID = initialImageId
                onVector.invoke()
            }
            .requiredWidth(222.1591796875.dp)
            .requiredHeight(247.9072265625.dp)
    )
}


