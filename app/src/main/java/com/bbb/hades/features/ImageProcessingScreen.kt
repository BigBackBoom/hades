package com.bbb.hades.features

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.bbb.glesimageprocessor.GLES3JNIView
import com.bbb.hades.R
import com.bbb.hades.viewmodel.ImageProcessingViewModel


@Composable
fun ImageProcessingScreen(
    modifier: Modifier = Modifier,
    viewModel: ImageProcessingViewModel = hiltViewModel()
) {
    val imageByte = viewModel.imageByte.collectAsState().value
    val processingTime = viewModel.processingTime.collectAsState().value
    val originalImage = viewModel.originalImage.collectAsState().value

    ImageProcessingContent(
        modifier = modifier,
        originalImage = originalImage,
        imageByte = imageByte,
        processingTime = processingTime,
        onClickNativeButton = {
            viewModel.loadImage(ImageProcessingViewModel.ImageProcessingType.NATIVE)
        },
        onClickJavaButton = {
            viewModel.loadImage(ImageProcessingViewModel.ImageProcessingType.JAVA)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ImageProcessingContent(
    modifier: Modifier,
    originalImage: ByteArray,
    imageByte: ByteArray,
    processingTime: Long,
    onClickNativeButton: () -> Unit = {},
    onClickJavaButton: () -> Unit = {}
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
            modifier = modifier.padding(innerPadding)
        ) {
            if (imageByte.isNotEmpty()) {
                val bitmap = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.size)
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = "Image"
                )
                Text(
                    text = stringResource(
                        R.string.image_processing_processing_time,
                        processingTime
                    ),
                    style = MaterialTheme.typography.bodyMedium
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.sample),
                    contentDescription = "Image"
                )
            }

            ImageProcessingExecuteButtons(
                modifier = Modifier.align(CenterHorizontally),
                onClickNativeButton = onClickNativeButton,
                onClickJavaButton = onClickJavaButton
            )

            if (originalImage.isNotEmpty()) {
                GlScreen(originalImage.sliceArray(IntRange(54, originalImage.size - 1)))
            }
        }
    }
}

@Composable
private fun ImageProcessingExecuteButtons(
    modifier: Modifier,
    onClickNativeButton: () -> Unit = {},
    onClickJavaButton: () -> Unit = {}
) {
    Button(
        modifier = modifier
            .padding(24.dp)
            .fillMaxWidth(),
        onClick = onClickNativeButton,
    ) {
        Text(text = stringResource(R.string.image_processing_start_ndk_button))
    }

    Button(
        modifier = modifier
            .padding(24.dp)
            .fillMaxWidth(),
        onClick = onClickJavaButton,
    ) {
        Text(text = stringResource(R.string.image_processing_start_java_button))
    }
}

@Composable
private fun ImageProcessingColorField() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        TextField(
            modifier = Modifier
                .width(96.dp)
                .padding(horizontal = 8.dp),
            value = "",
            onValueChange = { },
            label = { Text(text = stringResource(R.string.image_processing_red_text_field_label)) },

        )
        TextField(
            modifier = Modifier
                .width(96.dp)
                .padding(horizontal = 8.dp),
            value = "",
            onValueChange = { },
            label = { Text(text = stringResource(R.string.image_processing_green_text_field_label)) },
        )
        TextField(
            modifier = Modifier
                .width(96.dp)
                .padding(horizontal = 8.dp),
            value = "",
            onValueChange = { },
            label = { Text(text = stringResource(R.string.image_processing_blue_text_field_label)) },
        )
        TextField(
            modifier = Modifier
                .width(96.dp)
                .padding(horizontal = 8.dp),
            value = "",
            onValueChange = { },
            label = { Text(text = stringResource(R.string.image_processing_alpha_text_field_label)) },
        )
    }
}

@Composable
fun GlScreen(imageByteArray: ByteArray) {
    AndroidView(
        modifier = Modifier
            .fillMaxWidth(),
        factory = { context ->
            GLES3JNIView(context, imageByteArray)
        }
    )
}
