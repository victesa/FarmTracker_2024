package com.victorkirui.animals.ui.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.victorkirui.animals.ui.addingAnimals.AddingAnimalsViewModel
import com.victorkirui.designsystem.theme.AppTheme

@Composable
fun CameraDialog(
    onImageSelected:(Uri) -> Unit,
    dismissDialog:() -> Unit
){


    val pickImageLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent(), onResult = {
        it?.let {
            onImageSelected(it)
        }
    })

    AlertDialog(onDismissRequest = {
    },
        confirmButton = {
            TextButton(onClick = { pickImageLauncher.launch("image/*") }) {
                Text(text = "Yes")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                dismissDialog()
            }) {
                Text(text = "No")
            }
        },
        text = {
            Text(text = "You can choose or take a photo for this animal. Do you wish to select a photo?")
        },
        title = {
            Text(text = "Image Selection")
        })
}




@Composable
@Preview(showBackground = true)
internal fun PreviewCameraDialog(){
    AppTheme {
        CameraDialog({}){}
    }
}