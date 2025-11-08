package com.victorkirui.animals.ui.addingAnimals

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.victorkirui.animals.ui.components.CameraDialog
import com.victorkirui.designsystem.theme.AppTheme

@Composable
fun AddAnimalRoute(viewModel: AddingAnimalsViewModel = hiltViewModel(),
                            windowWidthSizeClass: WindowWidthSizeClass,
                   navigateToPreviousScreen:() -> Unit,
                   currentAnimal: String){

    val eventReference = viewModel::onEvent

    val uiState = viewModel.uiState.collectAsState()

    val context = LocalContext.current

    if (uiState.value.shouldShowCameraDialog){
        CameraDialog(onImageSelected = {
            eventReference(
                AddingAnimalsEvents.SaveAnimalPicture(
                    context = context,
                    pictureUri = it,
                    currentAnimal = currentAnimal
                )
            )

            navigateToPreviousScreen()

            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
        }, dismissDialog = {
            eventReference(AddingAnimalsEvents.DismissDialog(currentAnimal))

            navigateToPreviousScreen()

            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
        })
    }

    AddAnimalScreen(
        windowWidthSizeClass = windowWidthSizeClass,
        nameValue = uiState.value.name,
        onNameValueChange = { eventReference(AddingAnimalsEvents.AnimalName(it)) },
        breedValue = uiState.value.breed,
        onBreedValueChange = { eventReference(AddingAnimalsEvents.AnimalBreed(it))},
        ageValue = uiState.value.age,
        onAgeValueChange = {eventReference(AddingAnimalsEvents.AnimalAge(it))},
        genderValue = uiState.value.gender,
        onGenderValueChange = {eventReference(AddingAnimalsEvents.AnimalGender(it))},
        descriptionValue = uiState.value.description,
        onDescriptionValueChange = {eventReference(AddingAnimalsEvents.AnimalDescription(it))},
        nameError = !uiState.value.nameIsFilled,
        genderError = !uiState.value.genderIsFilled,
        navigateToPreviousScreen = {navigateToPreviousScreen()},
        onSaveClicked = {eventReference(AddingAnimalsEvents.SaveAnimal)}
    )
}

@Composable
internal fun AddAnimalScreen(windowWidthSizeClass: WindowWidthSizeClass,
                             nameValue: String,
                             onNameValueChange:(String) -> Unit,
                             breedValue: String,
                             onBreedValueChange:(String) -> Unit,
                             ageValue: String,
                             onAgeValueChange:(String) -> Unit,
                             genderValue: String,
                             onGenderValueChange:(String) -> Unit,
                             descriptionValue: String,
                             onDescriptionValueChange:(String) -> Unit,
                             nameError: Boolean,
                             genderError: Boolean,
                             navigateToPreviousScreen: () -> Unit,
                             onSaveClicked: () -> Unit){

    when(windowWidthSizeClass){
        WindowWidthSizeClass.Compact ->{
            AddAnimalCompact(
                nameValue = nameValue,
                onNameValueChange = { onNameValueChange(it) },
                breedValue = breedValue,
                onBreedValueChange = {onBreedValueChange(it)},
                ageValue = ageValue,
                onAgeValueChange = {onAgeValueChange(it)},
                genderValue = genderValue,
                onGenderValueChange = {onGenderValueChange(it)},
                descriptionValue = descriptionValue,
                onDescriptionValueChange = {onDescriptionValueChange(it)},
                nameError = nameError,
                genderError = genderError,
                navigateToPreviousScreen = navigateToPreviousScreen,
                onSaveClicked
            )
        }

        WindowWidthSizeClass.Medium ->{
            AddAnimalMedium(
                nameValue = nameValue,
                onNameValueChange = { onNameValueChange(it) },
                breedValue = breedValue,
                onBreedValueChange = {onBreedValueChange(it)},
                ageValue = ageValue,
                onAgeValueChange = {onAgeValueChange(it)},
                genderValue = genderValue,
                onGenderValueChange = {onGenderValueChange(it)},
                descriptionValue = descriptionValue,
                onDescriptionValueChange = {onDescriptionValueChange(it)},
                nameError = nameError,
                genderError = genderError,
                navigateToPreviousScreen = navigateToPreviousScreen,
                onSaveClicked
            )
        }
    }

}


@Composable
fun AddAnimalCompact(nameValue: String,
                     onNameValueChange:(String) -> Unit,
                     breedValue: String,
                     onBreedValueChange:(String) -> Unit,
                     ageValue: String,
                     onAgeValueChange:(String) -> Unit,
                     genderValue: String,
                     onGenderValueChange:(String) -> Unit,
                     descriptionValue: String,
                     onDescriptionValueChange:(String) -> Unit,
                     nameError: Boolean,
                     genderError: Boolean,
                     navigateToPreviousScreen: () -> Unit,
                     onSaveClicked: () -> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.1f), verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Default.Close,
                contentDescription = null,
                modifier = Modifier.clickable {
                    navigateToPreviousScreen()
                })
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.8f)) {
            AnimalTextField(value = nameValue,
                onValueChange = { onNameValueChange(it) },
                placeHolderText = "Name",
                paddingVertical = 16.dp,
                isError = nameError)

            AnimalTextField(value = breedValue,
                onValueChange = { onBreedValueChange(it) },
                placeHolderText = "Breed",
                paddingVertical = 16.dp,
                isError = false)

            OutlinedTextField(value = ageValue,
                onValueChange = {onAgeValueChange(it)},
                placeholder = {
                    Text(text = "Age")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            AnimalTextField(value = genderValue,
                onValueChange = { onGenderValueChange(it) },
                placeHolderText = "Gender",
                paddingVertical = 16.dp, genderError)

            OutlinedTextField(value = descriptionValue,
                onValueChange = {onDescriptionValueChange(it)},
                placeholder = { Text(text = "Description")},
                singleLine = false,
                minLines = 5,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp))

        }

        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            SaveButton(fontSize = 16.sp, paddingVertical = 8.dp, onSaveClicked = onSaveClicked)
        }
    }

}

@Composable
fun AddAnimalMedium(nameValue: String,
                    onNameValueChange:(String) -> Unit,
                    breedValue: String,
                    onBreedValueChange:(String) -> Unit,
                    ageValue: String,
                    onAgeValueChange:(String) -> Unit,
                    genderValue: String,
                    onGenderValueChange:(String) -> Unit,
                    descriptionValue: String,
                    onDescriptionValueChange:(String) -> Unit,
                    nameError: Boolean,
                    genderError: Boolean,
                    navigateToPreviousScreen: () -> Unit,
                    onSaveClicked: () -> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.1f),
            verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Default.Close,
                contentDescription = null,
                modifier = Modifier.clickable {
                    navigateToPreviousScreen()
                })
        }

        Column(modifier = Modifier
            .fillMaxWidth(.8f)
            .fillMaxHeight(.8f)) {
            AnimalTextField(value = nameValue,
                onValueChange = { onNameValueChange(it) },
                placeHolderText = "Name ",
                paddingVertical = 16.dp,
                isError = nameError)

            AnimalTextField(value = breedValue,
                onValueChange = { onBreedValueChange(it) },
                placeHolderText = "Breed",
                paddingVertical = 16.dp,
                isError = false)

            OutlinedTextField(value = ageValue,
                onValueChange = {onAgeValueChange(it)},
                placeholder = {
                    Text(text = "Age")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            AnimalTextField(value = genderValue,
                onValueChange = { onGenderValueChange(it) },
                placeHolderText = "Gender",
                paddingVertical = 16.dp,
                isError = genderError)

            OutlinedTextField(value = descriptionValue,
                onValueChange = {onDescriptionValueChange(it)},
                placeholder = { Text(text = "Description")},
                singleLine = false,
                minLines = 5,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp))

        }

        Column(modifier = Modifier
            .fillMaxWidth(.8f)
            .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            SaveButton(fontSize = 17.sp, 16.dp, onSaveClicked = onSaveClicked)
        }
    }

}

@Composable
fun AnimalTextField(value: String,
                    onValueChange: (String) -> Unit,
                    placeHolderText: String,
                    paddingVertical: Dp,
                    isError: Boolean){
    OutlinedTextField(value = value, onValueChange = {onValueChange(it)},
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = paddingVertical),
        placeholder = {
            Text(text = placeHolderText)
        },
        isError = isError,
        maxLines = 1)
}

@Composable
fun SaveButton(fontSize: TextUnit,
               paddingVertical: Dp,
               onSaveClicked:() -> Unit){
    Button(onClick = { onSaveClicked() }, modifier = Modifier
        .fillMaxWidth()
        .padding(paddingVertical)) {
        Text(text = "Save", color = Color.White, modifier = Modifier.padding(8.dp), fontSize = fontSize)
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewThisCompact(viewModel: AddingAnimalsViewModel = hiltViewModel()){
    AppTheme {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)){

            val eventReference = viewModel::onEvent

            val uiState = viewModel.uiState.collectAsState()

            AddAnimalCompact(
                nameValue = uiState.value.name,
                onNameValueChange = { eventReference(AddingAnimalsEvents.AnimalName(it)) },
                breedValue = uiState.value.breed,
                onBreedValueChange = { eventReference(AddingAnimalsEvents.AnimalBreed(it))},
                ageValue = uiState.value.age.toString(),
                onAgeValueChange = {eventReference(AddingAnimalsEvents.AnimalAge(it))},
                genderValue = uiState.value.gender,
                onGenderValueChange = {eventReference(AddingAnimalsEvents.AnimalGender(it))},
                descriptionValue = uiState.value.description,
                onDescriptionValueChange = {eventReference(AddingAnimalsEvents.AnimalDescription(it))},
                nameError = uiState.value.nameIsFilled,
                genderError = uiState.value.genderIsFilled,
                navigateToPreviousScreen = {}
            ){}
        }
    }
}

@Composable
@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
fun PreviewThisMedium(viewModel: AddingAnimalsViewModel = hiltViewModel()){
    AppTheme {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)) {
            val eventReference = viewModel::onEvent

            val uiState = viewModel.uiState.collectAsState()

            AddAnimalMedium(
                nameValue = uiState.value.name,
                onNameValueChange = { eventReference(AddingAnimalsEvents.AnimalName(it)) },
                breedValue = uiState.value.breed,
                onBreedValueChange = { eventReference(AddingAnimalsEvents.AnimalBreed(it))},
                ageValue = uiState.value.age.toString(),
                onAgeValueChange = {eventReference(AddingAnimalsEvents.AnimalAge(it))},
                genderValue = uiState.value.gender,
                onGenderValueChange = {eventReference(AddingAnimalsEvents.AnimalGender(it))},
                descriptionValue = uiState.value.description,
                onDescriptionValueChange = {eventReference(AddingAnimalsEvents.AnimalDescription(it))},
                nameError = uiState.value.nameIsFilled,
                genderError = uiState.value.genderIsFilled,
                navigateToPreviousScreen = {}
            ){}
        }
    }
}