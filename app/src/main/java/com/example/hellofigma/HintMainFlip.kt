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
import com.example.hellofigma.hintpage.HintPage
import com.google.relay.compose.BoxScopeInstance.boxAlign
import com.google.relay.compose.RelayContainer
import com.google.relay.compose.RelayImage

data class HintState(
    val onOpen1: Boolean,
    val onOpen2: Boolean,
    val onOpen3: Boolean,
    val onOpen4: Boolean
)
@Composable
fun HintMainFlipPage(
    modifier: Modifier = Modifier,
    onHint1: () -> Unit = {},
    onHint3: () -> Unit = {},
    onHint4: () -> Unit = {},
    onHint2: () -> Unit = {},
    onClaim: () -> Unit = {},
    hintState: HintState,
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        HintPage(modifier = Modifier.fillMaxSize(), onClaim = onClaim)
        Hint1(isOpen1 = hintState.onOpen1, onHint1 = onHint1, modifier = Modifier.boxAlign(alignment = Alignment.TopStart, offset = DpOffset( x = 24.0.dp,
            y = 198.0.dp)))
        Hint3(isOpen1 = hintState.onOpen3, onHint1 = onHint3, modifier = Modifier.boxAlign(alignment = Alignment.TopStart, offset = DpOffset(  x = 21.0.dp,
            y = 374.0.dp)))
        Hint4(isOpen1 = hintState.onOpen4, onHint1 = onHint4, modifier = Modifier.boxAlign(alignment = Alignment.TopStart, offset = DpOffset( x = 211.0.dp,
            y = 374.0.dp)))
        Hint2(isOpen1 = hintState.onOpen2, onHint1 = onHint2, modifier = Modifier.boxAlign(alignment = Alignment.TopStart, offset = DpOffset(x = 203.0.dp,
            y = 198.0.dp)))
    }
}

@Preview(widthDp = 387, heightDp = 793)
@Composable
private fun HintMainFlipPagePreview() {
    MaterialTheme {
        RelayContainer {
            HintMainFlipPage(
                modifier = Modifier,
                onHint1 = {},
                onHint3 = {},
                onHint4 = {},
                onHint2 = {},
                onClaim = {},
                hintState = HintState(
                        onOpen1 = false,
                        onOpen2 = false,
                        onOpen3 = false,
                        onOpen4 = false
                        )
            )
        }
    }
}

@Composable
fun Hint1(
    isOpen1: Boolean,
    onHint1: () -> Unit,
    modifier: Modifier = Modifier,
    initialImageId: Int = R.drawable.hint_page_hint_1
) {
    var pictureID = if (isOpen1) R.drawable.hint_1_place else initialImageId
    RelayImage(
        image = painterResource(pictureID),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .clickable { onHint1() }
            .requiredWidth(161.549560546875.dp).requiredHeight(154.4423828125.dp)
    )
}

@Composable
fun Hint2(
    isOpen1: Boolean,
    onHint1: () -> Unit,
    modifier: Modifier = Modifier,
    initialImageId: Int = R.drawable.hint_page_hint_2
) {
    var pictureID = if (isOpen1) R.drawable.hint_page_3 else initialImageId
    RelayImage(
        image = painterResource(pictureID),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .clickable { onHint1() }
            .requiredWidth(160.0.dp).requiredHeight(152.0.dp)
    )
}

@Composable
fun Hint3(
    isOpen1: Boolean,
    onHint1: () -> Unit,
    modifier: Modifier = Modifier,
    initialImageId: Int = R.drawable.hint_page_hint_3
) {
    var pictureID = if (isOpen1) R.drawable.hint_page_0 else initialImageId
    RelayImage(
        image = painterResource(pictureID),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .clickable { onHint1() }
            .requiredWidth(162.0.dp).requiredHeight(159.0.dp)
    )
}

@Composable
fun Hint4(
    isOpen1: Boolean,
    onHint1: () -> Unit,
    modifier: Modifier = Modifier,
    initialImageId: Int = R.drawable.hint_page_hint_4
) {
    var pictureID = if (isOpen1) R.drawable.hint_page_box else initialImageId
    RelayImage(
        image = painterResource(pictureID),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .clickable { onHint1() }
            .requiredWidth(163.0.dp).requiredHeight(156.0.dp)
    )
}
