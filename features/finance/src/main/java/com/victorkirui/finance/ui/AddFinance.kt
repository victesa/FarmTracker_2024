package com.victorkirui.finance.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.victorkirui.designsystem.theme.AppTheme
import com.victorkirui.finance.ui.components.FinanceOutlinedTextField
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date

@Composable
fun AddFinanceRoute(widthSizeClass: WindowWidthSizeClass,
                    viewModel: AddFinanceViewModel = hiltViewModel(),
                    financeType: String,
                    navigateToPreviousScreen: () -> Unit){

    val uiState = viewModel.addFinanceState.collectAsState()
    val eventsReference = viewModel::onEvent
    val context = LocalContext.current

    AddFinanceScreen(
        widthSizeClass = widthSizeClass,
        financeName = uiState.value.name,
        onFinanceNameChange = {eventsReference(AddFinanceEvents.OnNameChange(it))},
        financeNameError = uiState.value.amountIsError,
        showDatePicker = uiState.value.shouldShowDatePicker,
        dismissDialog = { eventsReference(AddFinanceEvents.DismissDialog) },
        dateValue = SimpleDateFormat("dd/MM/yyyy").format(uiState.value.date),
        updateDate = {eventsReference(AddFinanceEvents.OnUpdateDate(it))},
        shouldShowDatePicker = { eventsReference(AddFinanceEvents.ShowDatePicker) },
        financeAmount = uiState.value.amount,
        onFinanceAmountChange = {eventsReference(AddFinanceEvents.OnAmountChange(it))},
        descriptionValue = uiState.value.description,
        onDescriptionChange = {eventsReference(AddFinanceEvents.OnDescriptionChange(it))},
        saveProduction = {
            eventsReference(AddFinanceEvents.SaveData(financeType, context))
            navigateToPreviousScreen()},
        navigateToPreviousScreen = {navigateToPreviousScreen()}
    )

}

@Composable
internal fun AddFinanceScreen(widthSizeClass: WindowWidthSizeClass,
                              financeName: String,
                              onFinanceNameChange:(String) -> Unit,
                              financeNameError: Boolean,
                              showDatePicker: Boolean,
                              dismissDialog: () -> Unit,
                              dateValue: String,
                              updateDate: (Date) -> Unit,
                              shouldShowDatePicker:() -> Unit,
                              financeAmount: String,
                              onFinanceAmountChange:(String) ->Unit,
                              descriptionValue: String,
                              onDescriptionChange:(String)-> Unit,
                              saveProduction:() -> Unit,
                              navigateToPreviousScreen: () -> Unit){
    when(widthSizeClass){
        WindowWidthSizeClass.Compact ->{
            AddFinanceCompact(
                financeName = financeName,
                onFinanceNameChange = {onFinanceNameChange(it)},
                financeNameError = financeNameError,
                showDatePicker = showDatePicker,
                dismissDialog = { dismissDialog() },
                dateValue = dateValue,
                updateDate = {updateDate(it)},
                shouldShowDatePicker = { shouldShowDatePicker() },
                financeAmount = financeAmount,
                onFinanceAmountChange = {onFinanceAmountChange(it)},
                descriptionValue = descriptionValue,
                onDescriptionChange = {onDescriptionChange(it)},
                saveProduction = {saveProduction()},
                navigateToPreviousScreen = {navigateToPreviousScreen()}
            )
        }

        WindowWidthSizeClass.Medium ->{
            AddFinanceMedium(
                financeName = financeName,
                onFinanceNameChange = {onFinanceAmountChange(it)},
                financeNameError = financeNameError,
                showDatePicker = showDatePicker,
                dismissDialog = { /*TODO*/ },
                dateValue = dateValue,
                updateDate = {updateDate(it)},
                shouldShowDatePicker = { shouldShowDatePicker() },
                financeAmount = financeAmount,
                onFinanceAmountChange = {onFinanceAmountChange(it)},
                descriptionValue = descriptionValue,
                onDescriptionChange = {onDescriptionChange(it)},
                saveProduction = {saveProduction()},
                navigateToPreviousScreen = {navigateToPreviousScreen()}
            )
        }
    }

}

@Composable
internal fun AddFinanceCompact(
    financeName: String,
    onFinanceNameChange:(String) -> Unit,
    financeNameError: Boolean,
    showDatePicker: Boolean,
    dismissDialog: () -> Unit,
    dateValue: String,
    updateDate: (Date) -> Unit,
    shouldShowDatePicker:() -> Unit,
    financeAmount: String,
    onFinanceAmountChange:(String) ->Unit,
    descriptionValue: String,
    onDescriptionChange:(String)-> Unit,
    saveProduction:() -> Unit,
    navigateToPreviousScreen:() -> Unit
){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.1f), verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.Close, contentDescription = null,
                modifier = Modifier.clickable { navigateToPreviousScreen() })
        }

        FinanceOutlinedTextField(
            value = financeName,
            onValueChange = {onFinanceNameChange(it)},
            placeholderText = "Name",
            keyboardType = KeyboardType.Text,
            error = financeNameError,
            widthPercentage = 1f,
            minLines = 1,
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(32.dp))

        FinanceOutlinedTextField(
            value = financeAmount,
            onValueChange = {onFinanceAmountChange(it)},
            placeholderText = "Amount",
            keyboardType = KeyboardType.Text,
            error = financeNameError,
            widthPercentage = 1f,
            minLines = 1,
            maxLines = 1
        )

        DatePickerComposable(
            showDatePicker = showDatePicker,
            dismissDialog = { dismissDialog() },
            updateDate = updateDate
        )


        Column(modifier = Modifier
            .padding(top = 24.dp)
            .fillMaxHeight(.8f)
        ) {
            Text(text = "Date")

            val source = remember {
                MutableInteractionSource()
            }

            OutlinedTextField(
                value = dateValue,
                onValueChange = {},
                readOnly = true,
                interactionSource = source,
                modifier = Modifier
                    .fillMaxWidth(.5f)
            )

            if (source.collectIsPressedAsState().value){
                shouldShowDatePicker()

            }
            Spacer(modifier = Modifier.height(40.dp))

            FinanceOutlinedTextField(
                value = descriptionValue,
                onValueChange = {onDescriptionChange(it)},
                placeholderText = "Description",
                keyboardType = KeyboardType.Text,
                error = financeNameError,
                widthPercentage = 1f,
                minLines = 6,
                maxLines = 6
            )
        }





        Button(onClick = { saveProduction() },
            modifier = Modifier.fillMaxWidth()) {
            Text(text = "Save", modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
internal fun AddFinanceMedium(financeName: String,
                              onFinanceNameChange:(String) -> Unit,
                              financeNameError: Boolean,
                              showDatePicker: Boolean,
                              dismissDialog: () -> Unit,
                              dateValue: String,
                              updateDate: (Date) -> Unit,
                              shouldShowDatePicker:() -> Unit,
                              financeAmount: String,
                              onFinanceAmountChange:(String) ->Unit,
                              descriptionValue: String,
                              onDescriptionChange:(String)-> Unit,
                              saveProduction:() -> Unit,
                              navigateToPreviousScreen:() -> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 24.dp)) {

        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.1f), verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Default.Close, contentDescription = null,
                modifier = Modifier.clickable { navigateToPreviousScreen() })
        }

        FinanceOutlinedTextField(
            value = financeName,
            onValueChange = {onFinanceNameChange(it)},
            placeholderText = "Name",
            keyboardType = KeyboardType.Text,
            error = financeNameError,
            widthPercentage = 1f,
            minLines = 1,
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(32.dp))

        FinanceOutlinedTextField(
            value = financeAmount,
            onValueChange = {onFinanceAmountChange(it)},
            placeholderText = "Amount",
            keyboardType = KeyboardType.Text,
            error = financeNameError,
            widthPercentage = 1f,
            minLines = 1,
            maxLines = 1
        )

        DatePickerComposable(
            showDatePicker = showDatePicker,
            dismissDialog = { dismissDialog() },
            updateDate = updateDate
        )


        Column(modifier = Modifier
            .padding(top = 24.dp)
            .fillMaxHeight(.8f)
        ) {
            Text(text = "Date")

            val source = remember {
                MutableInteractionSource()
            }

            OutlinedTextField(
                value = dateValue,
                onValueChange = {},
                readOnly = true,
                interactionSource = source,
                modifier = Modifier
                    .fillMaxWidth(.5f)
            )

            if (source.collectIsPressedAsState().value){
                shouldShowDatePicker()

            }
            Spacer(modifier = Modifier.height(40.dp))

            FinanceOutlinedTextField(
                value = descriptionValue,
                onValueChange = {onDescriptionChange(it)},
                placeholderText = "Description",
                keyboardType = KeyboardType.Text,
                error = financeNameError,
                widthPercentage = 1f,
                minLines = 6,
                maxLines = 6
            )
        }





        Button(onClick = { saveProduction() },
            modifier = Modifier.fillMaxWidth()) {
            Text(text = "Save", modifier = Modifier.padding(8.dp))
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerComposable(showDatePicker: Boolean,
                         dismissDialog: () -> Unit,
                         updateDate: (Date) -> Unit){

    val dialogState = rememberDatePickerState(
        initialSelectedDateMillis = System.currentTimeMillis()
    )

    if(showDatePicker){
        DatePickerDialog(onDismissRequest = { dismissDialog() },
            confirmButton = {
                TextButton(onClick = {
                    if (dialogState.selectedDateMillis != null){
                        updateDate(convertLongToTime(dialogState.selectedDateMillis!!))
                        dismissDialog()
                    }

                }) {
                    Text(text = "Ok")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    dismissDialog()
                }) {
                    Text(text = "Cancel")
                }
            }) {
            DatePicker(state = dialogState)
        }
    }
}

fun convertLongToTime(time: Long): Date {
    return Date(time)
}

@Composable
@Preview(showBackground = true)
internal fun AddFinanceCompactPreview(){
    AppTheme {
        AddFinanceCompact(
            financeName = "" ,
            onFinanceNameChange = {},
            financeNameError = false,
            showDatePicker = false,
            dismissDialog = { /*TODO*/ },
            dateValue = LocalDate.now().toString(),
            updateDate = {},
            {},
            "",
            {},
            "",
            {},
            {},
            {}
        )
    }
}

@Composable
@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
internal fun AddFinanceMediumPreview(){
    AppTheme {
        AddFinanceCompact(
            financeName = "",
            onFinanceNameChange = {},
            financeNameError = false,
            showDatePicker = false,
            dismissDialog = { /*TODO*/ },
            dateValue = "",
            updateDate = {},
            shouldShowDatePicker = { /*TODO*/ },
            financeAmount = "",
            onFinanceAmountChange = {},
            descriptionValue = "" ,
            onDescriptionChange = {},
            {}
        ) {

        }
    }
}
