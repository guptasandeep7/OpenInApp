package com.sandeepgupta.openinapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandeepgupta.openinapp.domain.model.DashboardModel
import com.sandeepgupta.openinapp.domain.repository.Repository
import com.sandeepgupta.openinapp.data.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repo: Repository,
) : ViewModel() {

    private val _dashboardData = MutableStateFlow<ApiState<DashboardModel>>(ApiState.Loading())
    val dashboardData: StateFlow<ApiState<DashboardModel>> = _dashboardData

    //fetch data when app is launched
    init {
        viewModelScope.launch {
            fetchDashboardData()
        }
    }

    //To fetch dashboard data from server
    fun fetchDashboardData() = viewModelScope.launch {
        repo.getDashboardData().flowOn(Dispatchers.IO)
            .catch {
                _dashboardData.value = ApiState.Error(it.message.toString())
            }
            .collect {
                when (it) {
                    is ApiState.Success -> {
                        if (it.data == null) {
                            _dashboardData.value = ApiState.Error("No data found !!! Please retry.")
                        } else {
                            _dashboardData.value = it
                        }
                    }

                    else -> _dashboardData.value = it
                }
            }
    }

}