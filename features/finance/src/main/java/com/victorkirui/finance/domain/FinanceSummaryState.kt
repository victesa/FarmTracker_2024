package com.victorkirui.finance.domain

data class FinanceSummaryState(
    val today: Double = 0.0,
    val thisWeek: Double = 0.0,
    val thisMonth: Double = 0.0
)
