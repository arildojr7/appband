package com.arildojr.data.setlist

import com.arildojr.data.setlist.dto.SetListDTO
import com.arildojr.data.setlist.model.SetList
import retrofit2.Response

interface SetListDataSource {

    interface Remote {

        suspend fun getSetLists(): Response<List<SetList>>

        suspend fun createSetList(setListDTO: SetListDTO) : Response<SetList>
    }

    interface Local {}
}