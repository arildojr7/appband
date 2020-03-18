package dev.arildo.data.setlist

import dev.arildo.data.setlist.dto.SetListDTO
import dev.arildo.data.setlist.model.SetList
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface SetListDataSource {

    interface Remote {

        suspend fun getSetLists(): Response<List<SetList>>

        suspend fun createSetList(setListDTO: SetListDTO): Response<SetList>
    }

    interface Local {
        fun insertAll(setLists: List<SetList>)

        fun getSetLists(): Flow<List<SetList>>

    }
}