package com.arildojr.data.setlist

import com.arildojr.data.setlist.model.SetList
import retrofit2.Response

interface SetListDataSource {

    interface Remote {

        suspend fun getSetLists(): Response<List<SetList>>
    }

    interface Local {}
}