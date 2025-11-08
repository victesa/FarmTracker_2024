package com.victorkirui.production.domain

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class AddProductionState(
    val productName: String = "",
    val productQuantity: String = "",
    val productUnit: String = "",
    val date: String = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString(),
    val shouldShowDatePickerDialog: Boolean = false,
    val shouldShowDropDownMenu: Boolean = false,
    val productNameTextFieldError: Boolean = false,
    val productAmountTextFieldError: Boolean = false
)
