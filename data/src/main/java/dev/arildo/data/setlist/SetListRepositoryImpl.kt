package dev.arildo.data.setlist

import dev.arildo.data.setlist.datasource.remote.SetListRemoteDataSource
import dev.arildo.data.setlist.dto.SetListDTO
import dev.arildo.data.setlist.model.SetList
import retrofit2.Response

class SetListRepositoryImpl(private val remoteDataSource: SetListRemoteDataSource) : SetListRepository {

    override suspend fun getSetLists(): Response<List<SetList>> {
        return remoteDataSource.getSetLists()
    }

    override suspend fun createSetList(setListDTO: SetListDTO): Response<SetList> {
        return remoteDataSource.createSetList(setListDTO)
    }

}