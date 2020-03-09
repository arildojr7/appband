package dev.arildo.data.setlist.datasource.remote

import dev.arildo.data.setlist.SetListDataSource
import dev.arildo.data.setlist.api.SetListApiService
import dev.arildo.data.setlist.dto.SetListDTO
import dev.arildo.data.setlist.model.SetList
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