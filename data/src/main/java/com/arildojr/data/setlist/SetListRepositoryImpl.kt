package com.arildojr.data.setlist

import com.arildojr.data.setlist.datasource.remote.SetListRemoteDataSource
import com.arildojr.data.setlist.dto.SetListDTO
import com.arildojr.data.setlist.model.SetList
import retrofit2.Response

class SetListRepositoryImpl(private val remoteDataSource: SetListRemoteDataSource) : SetListRepository {

    override suspend fun getSetLists(): Response<List<SetList>> {
        return remoteDataSource.getSetLists()
    }

    override suspend fun createSetList(setListDTO: SetListDTO): Response<SetList> {
        return remoteDataSource.createSetList(setListDTO)
    }

}