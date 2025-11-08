package com.victorkirui.production.ui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.victorkirui.designsystem.theme.AppTheme
import com.victorkirui.production.domain.AddProductionEvents
import com.victorkirui.production.components.AddProductionOutlinedTextField
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun AddProductionUiRoute(windowWidthSizeClass: WindowWidthSizeClass,
                         viewModel: AddProductionViewModel = hiltViewModel()){

    val eventReference = viewModel::onEvent
    val uiState = viewModel.uiState.collectAsState()

    AddProductionUiScreen(
        windowWidthSizeClass = windowWidthSizeClass,
        productValue = uiState.value.productName,
        onProductValueChange = {eventReference(AddProductionEvents.ProductName(it))},
        productTextFieldError = uiState.value.productAmountTextFieldError,
        amountValue = uiState.value.productQuantity,
        onAmountValueChange = {eventReference(AddProductionEvents.ProductQuantity(it))},
        unitValue = uiState.value.productUnit,
        onDismissMenu = { eventReference(AddProductionEvents.DismissDropDownMenu) },
        onItemClicked = {eventReference(AddProductionEvents.ProductUnit(it))},
        dropDownBoxIsExpanded = uiState.value.shouldShowDropDownMenu,
        dateValue = uiState.value.date,
        shouldShowDatePicker = { eventReference(AddProductionEvents.ShowDatePickerDialog) },
        showDatePicker = uiState.value.shouldShowDatePickerDialog,
        dismissDialog = { eventReference(AddProductionEvents.DismissDatePickerDialog) },
        updateDate = {eventReference(AddProductionEvents.ProductDate(it))},
        saveProduction = {eventReference(AddProductionEvents.saveProduction)}
    )


}

@Composable
internal fun AddProductionUiScreen(windowWidthSizeClass: WindowWidthSizeClass,
                                   productValue: String,
                                   onProductValueChange:(String) -> Unit,
                                   productTextFieldError: Boolean,
                                   amountValue: String,
                                   onAmountValueChange:(String) -> Unit,
                                   unitValue: String,
                                   onDismissMenu: () -> Unit,
                                   onItemClicked: (String) -> Unit,
                                   dropDownBoxIsExpanded: Boolean,
                                   dateValue: String,
                                   shouldShowDatePicker:() -> Unit,
                                   showDatePicker: Boolean,
                                   dismissDialog:() -> Unit,
                                   updateDate: (String) -> Unit,
                                   saveProduction: () -> Unit){
    when(windowWidthSizeClass){
        WindowWidthSizeClass.Compact ->{
            AddProductionUiCompact(
                productValue = productValue,
                onProductValueChange = {onProductValueChange(it)},
                productTextFieldError = productTextFieldError,
                amountValue = amountValue,
                onAmountValueChange = {onAmountValueChange(it)},
                unitValue = unitValue,
                onDismissMenu = { onDismissMenu() },
                onExpandedChanged = { /*TODO*/ },
                onItemClicked = {onItemClicked(it)},
                dropDownBoxIsExpanded = dropDownBoxIsExpanded,
                dateValue = dateValue,
                shouldShowDatePicker = { shouldShowDatePicker() },
                showDatePicker = showDatePicker,
                dismissDialog = { dismissDialog() },
                updateDate = {updateDate(it)},
                saveProduction = {saveProduction()}
            )
        }

        WindowWidthSizeClass.Medium ->{
            AddProductionUiMedium(
                productValue = productValue,
                onProductValueChange = {onProductValueChange(it)},
                productTextFieldError = productTextFieldError,
                amountValue = amountValue,
                onAmountValueChange = {onAmountValueChange(it)},
                unitValue = unitValue,
                onDismissMenu = { onDismissMenu() },
                onExpandedChanged = { /*TODO*/ },
                onItemClicked = {onItemClicked(it)},
                dropDownBoxIsExpanded = dropDownBoxIsExpanded,
                dateValue = dateValue,
                shouldShowDatePicker = { shouldShowDatePicker() },
                showDatePicker = showDatePicker,
                dismissDialog = { dismissDialog() },
                updateDate = {updateDate(it)},
                saveProduction = {saveProduction()}
            )
        }
    }

}

@Composable
internal fun AddProductionUiCompact(productValue: String,
                                    onProductValueChange:(String) -> Unit,
                                    productTextFieldError: Boolean,
                                    amountValue: String,
                                    onAmountValueChange:(String) -> Unit,
                                    unitValue: String,
                                    onDismissMenu: () -> Unit,
                                    onExpandedChanged: () -> Unit,
                                    onItemClicked: (String) -> Unit,
                                    dropDownBoxIsExpanded: Boolean,
                                    dateValue: String,
                                    shouldShowDatePicker:() -> Unit,
                                    showDatePicker: Boolean,
                                    dismissDialog:() -> Unit,
                                    updateDate: (String) -> Unit,
                                    saveProduction: () -> Unit){

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.1f), verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.Close, contentDescription = null)
        }

        AddProductionOutlinedTextField(
            placeholderText = "Product Name",
            value = productValue,
            onValueChange = {onProductValueChange(it)},
            keyboardType = KeyboardType.Text,
            errorBoolean = productTextFieldError,
            widthPercentage = 1f
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp)
        ) {
            AddProductionOutlinedTextField(
                placeholderText = "Amount",
                value = amountValue,
                onValueChange = {onAmountValueChange(it)},
                keyboardType = KeyboardType.Number,
                errorBoolean = productTextFieldError,
                widthPercentage = .5f
            )

            UnitDropDownBox(
                unitValue = unitValue,
                onDismissMenu = { onDismissMenu() },
                onExpandedChanged = { onExpandedChanged() },
                onItemClicked = { onItemClicked(it) },
                dropDownBoxIsExpanded = dropDownBoxIsExpanded
            )

        }

        DatePickerComposable(
            showDatePicker = showDatePicker,
            dismissDialog = { dismissDialog() },
            updateDate = updateDate
        )


        Column(modifier = Modifier
            .padding(top = 40.dp)
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
        }



        Button(onClick = { saveProduction() },
            modifier = Modifier.fillMaxWidth()) {
            Text(text = "Save", modifier = Modifier.padding(8.dp))
        }
    }

}

@Composable
internal fun AddProductionUiMedium(productValue: String,
                                   onProductValueChange:(String) -> Unit,
                                   productTextFieldError: Boolean,
                                   amountValue: String,
                                   onAmountValueChange:(String) -> Unit,
                                   unitValue: String,
                                   onDismissMenu: () -> Unit,
                                   onExpandedChanged: () -> Unit,
                                   onItemClicked: (String) -> Unit,
                                   dropDownBoxIsExpanded: Boolean,
                                   dateValue: String,
                                   shouldShowDatePicker:() -> Unit,
                                   showDatePicker: Boolean,
                                   dismissDialog:() -> Unit,
                                   updateDate: (String) -> Unit,
                                   saveProduction:() -> Unit){

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 24.dp)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.1f), verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Default.Close, contentDescription = null)
        }

        AddProductionOutlinedTextField(
            placeholderText = "Product Name",
            value = productValue,
            onValueChange = { onProductValueChange(it) },
            keyboardType = KeyboardType.Text,
            errorBoolean = productTextFieldError,
            widthPercentage = 1f
        )

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp)) {
            OutlinedTextField(value = amountValue,
                onValueChange = {onAmountValueChange(it)},
                modifier = Modifier.fillMaxWidth(.5f),
                placeholder = {
                    Text(text = "Amount")
                }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            UnitDropDownBox(
                dropDownBoxIsExpanded = dropDownBoxIsExpanded,
                onExpandedChanged = { onExpandedChanged() },
                unitValue = unitValue,
                onItemClicked = {onItemClicked(it)},
                onDismissMenu = {onDismissMenu()}
            )
        }

        DatePickerComposable(showDatePicker = showDatePicker,
            dismissDialog = { dismissDialog()},
            updateDate = updateDate)

        val source = remember {
            MutableInteractionSource()
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp)
            .fillMaxHeight(.8f)) {
            Text(text = "Date")

            OutlinedTextField(value = dateValue,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth(.4f))
        }

        if (source.collectIsPressedAsState().value){
            shouldShowDatePicker()

        }



        Button(onClick = { saveProduction() },
            modifier = Modifier.fillMaxWidth()) {
            Text(text = "Save", modifier = Modifier.padding(8.dp))
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitDropDownBox(dropDownBoxIsExpanded: Boolean,
                    onExpandedChanged:() -> Unit,
                    unitValue: String,
                    onItemClicked:(String) -> Unit,
                    onDismissMenu:() -> Unit){
    ExposedDropdownMenuBox(expanded = dropDownBoxIsExpanded,
        onExpandedChange = {onExpandedChanged()},
        modifier = Modifier.padding(start = 24.dp)) {
        OutlinedTextField(value = unitValue,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor()
            ,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = dropDownBoxIsExpanded)
            },
            maxLines = 1)

        ExposedDropdownMenu(expanded = dropDownBoxIsExpanded,
            onDismissRequest = {onDismissMenu()}) {
            DropdownMenuItem(text = { Text(text = "Litres") },
                onClick = {
                    onItemClicked("Litres")
                })
            DropdownMenuItem(text = { Text(text = "Kgs") },
                onClick = {
                    onItemClicked("Kgs")
                })
            DropdownMenuItem(text = { Text(text = "Grams") },
                onClick = {
                    onItemClicked("Grams")
                })
        }

    }
}

fun convertLongToTime(time: Long): String {
    val date = Date(time)
    val formatter = SimpleDateFormat("yyyy-MM-dd")
    return formatter.format(date)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerComposable(showDatePicker: Boolean,
                         dismissDialog: () -> Unit,
                         updateDate: (String) -> Unit){

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

@Composable
@Preview(showBackground = true)
internal fun AddProductionUiCompactPreview(viewModel: AddProductionViewModel = hiltViewModel()){
    AppTheme {
        val eventReference = viewModel::onEvent
        val uiState = viewModel.uiState.collectAsState()


        AddProductionUiCompact(
            productValue = uiState.value.productName,
            onProductValueChange = {eventReference(AddProductionEvents.ProductName(it))},
            productTextFieldError = uiState.value.productAmountTextFieldError,
            amountValue = uiState.value.productQuantity,
            onAmountValueChange = {eventReference(AddProductionEvents.ProductQuantity(it))},
            unitValue = uiState.value.productUnit,
            onDismissMenu = { eventReference(AddProductionEvents.DismissDropDownMenu) },
            onExpandedChanged = { eventReference(AddProductionEvents.OnExpandedChanged) },
            onItemClicked = {eventReference(AddProductionEvents.ProductUnit(it))},
            dropDownBoxIsExpanded = uiState.value.shouldShowDropDownMenu,
            dateValue = uiState.value.date,
            shouldShowDatePicker = { eventReference(AddProductionEvents.ShowDatePickerDialog) },
            showDatePicker = uiState.value.shouldShowDatePickerDialog,
            dismissDialog = { eventReference(AddProductionEvents.DismissDatePickerDialog) },
            updateDate = {eventReference(AddProductionEvents.ProductDate(it))},
            {}
        )
    }
}

@Composable
@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
internal fun AddProductionUiMediumPreview(){
    AppTheme {

        AddProductionUiMedium(
            productValue = "",
            onProductValueChange = {},
            productTextFieldError = false,
            amountValue = "",
            onAmountValueChange = {},
            unitValue = "",
            onDismissMenu = { },
            onExpandedChanged = { },
            onItemClicked = {},
            dropDownBoxIsExpanded = false,
            dateValue = "",
            shouldShowDatePicker = {},
            showDatePicker = false,
            dismissDialog = {  },
            updateDate = {}
        ){}
    }
}