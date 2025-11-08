package com.victorkirui.profile.ui.animalSelection

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import com.victorkirui.profile.ui.animalSelection.model.AnimalSheetDataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddAnimalDataViewModel @Inject constructor(): ViewModel() {

    val uri = mutableStateOf("".toUri())

    fun updateURI(uriProvided: Uri){
        uri.value = uriProvided
    }

    private val _uiState = MutableStateFlow(AnimalSheetDataModel())
    val uiState: StateFlow<AnimalSheetDataModel> = _uiState.asStateFlow()


//    fun readExcel(uri: Uri, context: Context){
//        try {
//            val inputStream = context.contentResolver.openInputStream(uri)
//            val workBook = WorkbookFactory.create(inputStream)
//
//            val sheet = workBook.getSheet("")
//           val filteredList = mutableListOf<Row>()
//
//            workBook.close()
//            inputStream?.close()
//        }catch (e: Exception){
//            e.printStackTrace()
//        }
//    }
}