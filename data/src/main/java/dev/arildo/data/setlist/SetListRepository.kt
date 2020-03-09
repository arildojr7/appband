package dev.arildo.data.setlist

import dev.arildo.data.setlist.dto.SetListDTO
import dev.arildo.data.setlist.model.SetList
import retrofit2.Response

interface SetListRepository {

    suspend fun getSetLists(): Response<List<SetList>>

    suspend fun createSetList(setListDTO: SetListDTO): Response<SetList>
}