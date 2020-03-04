package com.arildojr.data.setlist.datasource.remote

import com.arildojr.data.setlist.SetListDataSource
import com.arildojr.data.setlist.api.SetListApiService
import com.arildojr.data.setlist.dto.SetListDTO
import com.arildojr.data.setlist.model.SetList
import retrofit2.Response

class SetListRemoteDataSource(private val apiService: SetListApiService) :
    SetListDataSource.Remote {

    override suspend fun getSetLists(): Response<List<SetList>> {
        return apiService.getSetLists()
    }

    override suspend fun createSetList(setListDTO: SetListDTO): Response<SetList> {
        return apiService.createSetList(setListDTO)
    }
}