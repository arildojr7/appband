package dev.arildo.data.setlist

import dev.arildo.data.setlist.dto.SetListDTO
import dev.arildo.data.setlist.model.SetList
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface SetListRepository {

    suspend fun getSetLists(): Flow<Response<List<SetList>>>

    suspend fun createSetList(setListDTO: SetListDTO): Response<SetList>

    suspend fun insertAll(setLists: List<SetList>)
}