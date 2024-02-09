package com.sandeepgupta.openinapp.data.datasource

import com.sandeepgupta.openinapp.domain.model.DashboardModel
import retrofit2.http.GET


interface ApiService {

    @GET("api/v1/dashboardNew")
    suspend fun getDashboardData(): DashboardModel

}