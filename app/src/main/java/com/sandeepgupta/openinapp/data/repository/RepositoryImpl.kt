package com.sandeepgupta.openinapp.data.repository

import com.sandeepgupta.openinapp.domain.model.DashboardModel
import com.sandeepgupta.openinapp.domain.repository.Repository
import com.sandeepgupta.openinapp.data.ApiState
import com.sandeepgupta.openinapp.data.datasource.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : Repository {

    override suspend fun getDashboardData(): Flow<ApiState<DashboardModel>> = flow {
        emit(ApiState.Loading())
        try {
            val response = apiService.getDashboardData()
            emit(ApiState.Success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiState.Error(e.message.toString()))
        }
    }
}