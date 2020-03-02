package com.arildojr.data.setlist

import com.arildojr.data.setlist.model.SetList
import retrofit2.Response

interface SetListRepository {

    suspend fun getSetLists() : Response<List<SetList>>
}