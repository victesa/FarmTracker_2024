package com.victorkirui.production.domain

sealed class AddProductionEvents{
    data class ProductName(val product: String): AddProductionEvents()

    data class ProductQuantity(val amount: String): AddProductionEvents()

    data class ProductDate(val date: String): AddProductionEvents()

    data class ProductUnit(val unit: String): AddProductionEvents()

    data object DismissDropDownMenu: AddProductionEvents()

    data object OnExpandedChanged: AddProductionEvents()

    data object DismissDatePickerDialog: AddProductionEvents()

    data object ShowDatePickerDialog: AddProductionEvents()

    data object saveProduction: AddProductionEvents()
}