package com.bbb.imageprocessor.view

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.bbb.imageprocessor.R
import com.bbb.imageprocessor.viewmodel.ImageProcessingViewModel


@Composable
fun ImageProcessingScreen(
    modifier: Modifier = Modifier,
    viewModel: ImageProcessingViewModel = hiltViewModel()
) {
    val imageByte = viewModel.imageByte.collectAsState().value
    val processingTime = viewModel.processingTime.collectAsState().value

    ImageProcessingContent(
        modifier = modifier,
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
                    painter = painterResource(id = com.bbb.hades.core.common.R.drawable.sample),
                    contentDescription = "Image"
                )
            }

            ImageProcessingExecuteButtons(
                modifier = Modifier.align(CenterHorizontally),
                onClickNativeButton = onClickNativeButton,
                onClickJavaButton = onClickJavaButton
            )
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