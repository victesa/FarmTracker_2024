package com.victorkirui.finance.domain

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

data class AddFinanceState(
    val name: String = "",
    val type: String = "",
    val amount: String = "",
    val description: String = "",
    val date: Date = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()),
    val nameIsError: Boolean = false,
    val amountIsError: Boolean = false,
    val shouldShowDatePicker: Boolean = false
)
