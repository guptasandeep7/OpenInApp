package com.sandeepgupta.openinapp.domain.repository

import com.sandeepgupta.openinapp.domain.model.DashboardModel
import com.sandeepgupta.openinapp.data.ApiState
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getDashboardData():Flow<ApiState<DashboardModel>>
}