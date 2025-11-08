package com.victorkirui.farmtracker.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.victorkirui.farmtracker.ui.BottomNavigationBar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
): ViewModel() {

    private val _bottomNavigationBarList = MutableStateFlow<List<BottomNavigationBar>>(listOf(BottomNavigationBar(
        name = "Home",
        isSelected = true,
        unselectedIcon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home,
    ),
        BottomNavigationBar(
            name = "Search",
            unselectedIcon = Icons.Outlined.Search,
            selectedIcon = Icons.Filled.Search
        ),

        BottomNavigationBar(
            name = "Add",
            unselectedIcon = Icons.Outlined.Add,
            selectedIcon = Icons.Filled.Add
        ),

        BottomNavigationBar(
            name = "Notifications",
            unselectedIcon = Icons.Outlined.Notifications,
            selectedIcon = Icons.Filled.Notifications,
            badgeNumber = null
        ),

        BottomNavigationBar(
            name = "Settings",
            unselectedIcon = Icons.Outlined.AccountCircle,
            selectedIcon = Icons.Filled.AccountCircle
        )))

    val bottomNavigationBarList = _bottomNavigationBarList.asStateFlow()

    fun updateSelected(number: Int, currentScreenNumber: Int){
        val currentList = _bottomNavigationBarList.value.toMutableList()

        currentList[number] = currentList[number].copy(isSelected = true)

        currentList[currentScreenNumber] = currentList[currentScreenNumber].copy(isSelected = false)

        _bottomNavigationBarList.value = currentList
    }

    val bottomNavigationBar = mutableStateListOf(
        BottomNavigationBar(
            name = "Home",
            isSelected = true,
            unselectedIcon = Icons.Outlined.Home,
            selectedIcon = Icons.Filled.Home,
        ),
        BottomNavigationBar(
            name = "Search",
            unselectedIcon = Icons.Outlined.Search,
            selectedIcon = Icons.Filled.Search
        ),

        BottomNavigationBar(
            name = "Add",
            unselectedIcon = Icons.Outlined.Add,
            selectedIcon = Icons.Filled.Add
        ),

        BottomNavigationBar(
            name = "Notifications",
            unselectedIcon = Icons.Outlined.Notifications,
            selectedIcon = Icons.Filled.Notifications,
            badgeNumber = null
        ),

        BottomNavigationBar(
            name = "Settings",
            unselectedIcon = Icons.Outlined.AccountCircle,
            selectedIcon = Icons.Filled.AccountCircle
        )

    )

}