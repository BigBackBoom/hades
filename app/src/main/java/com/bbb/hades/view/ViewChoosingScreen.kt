package com.bbb.hades.view

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bbb.glesimageprocessor.navigation.navigateToImageGlProcessing
import com.bbb.hades.R
import com.bbb.hades.core.common.LocalNavController
import com.bbb.imageprocessor.navigation.navigateToImageProcessing

@Composable
fun ViewChoosingScreen(
    modifier: Modifier = Modifier
) {
    val navController = LocalNavController.current
    ViewChoosingContent(
        modifier = modifier,
        onImageProcessorNavigationClicked = {
            navController.navigateToImageProcessing()
        },
        onImageProcessorGlNavigationClicked = {
            navController.navigateToImageGlProcessing()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ViewChoosingContent(
    modifier: Modifier,
    onImageProcessorNavigationClicked: () -> Unit = {},
    onImageProcessorGlNavigationClicked: () -> Unit = {}
){
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
                        text = stringResource(R.string.view_changing_screen_title),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            )
        }
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onClick = onImageProcessorNavigationClicked
            ){
                Text(text = "Navigate to Image Processing Screen")
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onClick = onImageProcessorGlNavigationClicked
            ){
                Text(text = "Navigate to Image Processing GL Screen")
            }
        }
    }
}

@Preview
@Composable
fun ViewChoosingScreenPreview(){
    ViewChoosingContent(
        modifier = Modifier
    )
}
