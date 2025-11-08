package com.victorkirui.home.ui

data class FinanceState(
    val incomeDaily: Int = 0,
    val profitDaily: Int = 0,
    val expensesDaily: Int = 0,
    val incomeWeekly: Int = 0,
    val profitWeekly: Int = 0,
    val expensesWeekly: Int = 0,
    val incomeMonthly: Int = 0,
    val profitMonthly: Int = 0,
    val expensesMonthly: Int = 0,
    val showFinanceEmptyMessage: Boolean = true
)