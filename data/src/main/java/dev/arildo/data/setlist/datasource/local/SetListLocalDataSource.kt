package dev.arildo.data.setlist.datasource.local

import dev.arildo.data.setlist.SetListDataSource
import dev.arildo.data.setlist.model.SetList
import kotlinx.coroutines.flow.Flow

class SetListLocalDataSource(private val setListDao: SetListDao) : SetListDataSource.Local {
    override fun insertAll(setLists: List<SetList>) {
        setListDao.insertAll(setLists)
    }

    override fun getSetLists(): Flow<List<SetList>> {
        return setListDao.getAll()
    }
}