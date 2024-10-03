package com.bbb.glesimageprocessor.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.bbb.glesimageprocessor.R
import com.bbb.glesimageprocessor.viewmodel.ImageProcessingGlViewModel
import kotlin.math.roundToInt

@Composable
fun ImageProcessingGlScreen(
    modifier: Modifier = Modifier,
    viewModel: ImageProcessingGlViewModel = hiltViewModel()
) {
    val originalImage = viewModel.originalImage.collectAsState().value
    val processingSpeed = viewModel.processingSpeed.collectAsState().value
    val fps = viewModel.fps.collectAsState().value

    ImageProcessingContent(
        modifier = modifier,
        originalImage = originalImage,
        processingSpeed = processingSpeed,
        fps = fps,
        onFrameProcessFinished = viewModel::onFrameProcessFinished
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ImageProcessingContent(
    modifier: Modifier,
    originalImage: ByteArray,
    processingSpeed: List<Long>,
    fps: Int,
    onFrameProcessFinished: (Long) -> Unit = {}
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = stringResource(R.string.image_processing_title),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            if (originalImage.isNotEmpty()) {
                GlScreen(
                    originalImage.sliceArray(IntRange(54, originalImage.size - 1)),
                    onFrameProcessFinished = onFrameProcessFinished
                )
            }

            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                text = if (processingSpeed.isNotEmpty()) {
                    "Processing Speed: ${processingSpeed.average().roundToInt()} ms"
                } else {
                    "Processing Speed: ? ms"
                }
            )

            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                text = "$fps fps"
            )
        }
    }
}

@Composable
private fun GlScreen(
    imageByteArray: ByteArray,
    onFrameProcessFinished: (Long) -> Unit = {}
) {
    // Try to avoid recompose by remember original image
    val image by remember { mutableStateOf(imageByteArray) }

    AndroidView(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth(),
        factory = { context ->
            GLES3JNIView(context, image, onFrameProcessFinished)
        }
    )
}
