package com.victorkirui.finance.ui

import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorkirui.database.repository.FinanceRepository
import com.victorkirui.finance.domain.AddFinanceState
import com.victorkirui.finance.domain.DataTypeForTopPart
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AddFinanceViewModel @Inject constructor(
    private val financeRepository: FinanceRepository
): ViewModel() {

    private val _addFinanceState = MutableStateFlow(AddFinanceState())
    val addFinanceState = _addFinanceState.asStateFlow()

    fun onEvent(events: AddFinanceEvents){
        when(events){
            is AddFinanceEvents.OnNameChange ->{
                _addFinanceState.update {
                    it.copy(
                        name = events.name
                    )
                }
                if(addFinanceState.value.nameIsError){
                    _addFinanceState.update {
                        it.copy(nameIsError = false)
                    }
                }
            }

            is AddFinanceEvents.OnAmountChange ->{
                _addFinanceState.update {
                    it.copy(
                        amount = events.amount
                    )
                }
                if(addFinanceState.value.amountIsError){
                    _addFinanceState.update {
                        it.copy(amountIsError = false)
                    }
                }
            }

            is AddFinanceEvents.OnUpdateDate ->{
                _addFinanceState.update {
                    it.copy(
                        date = events.date
                    )
                }
            }

            is AddFinanceEvents.OnDescriptionChange ->{
                _addFinanceState.update {
                    it.copy(
                        description = events.description
                    )
                }
            }

            is AddFinanceEvents.SaveData ->{
                val nameIsError = addFinanceState.value.name.isEmpty()
                val amountIsError = addFinanceState.value.amount.isEmpty()

                val amountIsDigits = addFinanceState.value.amount.isDigitsOnly()

                if (nameIsError || amountIsError){
                    _addFinanceState.update {
                        it.copy(
                            nameIsError = nameIsError,
                            amountIsError = amountIsError
                        )
                    }
                }else{
                    if (!amountIsDigits){
                        Toast.makeText(events.context, "Amount should be Numeric", Toast.LENGTH_SHORT).show()
                    }
                    viewModelScope.launch {
                        if (events.financeType == DataTypeForTopPart.Income.name){
                            financeRepository.insertIncome(
                                name = addFinanceState.value.name,
                                amount = addFinanceState.value.amount.toDouble(),
                                description = addFinanceState.value.description,
                                date = addFinanceState.value.date
                            )

                            _addFinanceState.update {
                                it.copy(
                                    name = "",
                                    amountIsError = false,
                                    amount = "",
                                    type = "",
                                    description = "",
                                    nameIsError = false,
                                    shouldShowDatePicker = false, date = Date.from(
                                        LocalDate.now().atStartOfDay(
                                            ZoneId.systemDefault()).toInstant())
                                )
                            }

                        }else{
                            financeRepository.insertExpenses(
                                name = addFinanceState.value.name,
                                amount = addFinanceState.value.amount.toDouble(),
                                description = addFinanceState.value.description,
                                date = addFinanceState.value.date
                            )

                            _addFinanceState.update {
                                it.copy(
                                    name = "",
                                    amountIsError = false,
                                    amount = "",
                                    type = "",
                                    description = "",
                                    nameIsError = false,
                                    shouldShowDatePicker = false, date = Date.from(
                                        LocalDate.now().atStartOfDay(
                                            ZoneId.systemDefault()).toInstant())
                                )
                            }
                        }
                    }
                }
            }

            is AddFinanceEvents.DismissDialog ->{
                _addFinanceState.update {
                    it.copy(
                        shouldShowDatePicker = false
                    )
                }
            }

            is AddFinanceEvents.ShowDatePicker ->{
                _addFinanceState.update {
                    it.copy(
                        shouldShowDatePicker = true
                    )
                }
            }

        }
    }
}