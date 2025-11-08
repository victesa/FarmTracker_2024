package com.victorkirui.finance.ui

import android.content.Context
import java.util.Date

sealed class AddFinanceEvents {
    data class OnNameChange(val name: String): AddFinanceEvents()

    data class OnAmountChange(val amount: String): AddFinanceEvents()

    data class OnUpdateDate(val date: Date): AddFinanceEvents()

    data class OnDescriptionChange(val description: String): AddFinanceEvents()

    data class SaveData(val financeType: String, val context: Context): AddFinanceEvents()

    data object DismissDialog: AddFinanceEvents()

    data object ShowDatePicker: AddFinanceEvents()
}