package dev.arildo.appband.setlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import dev.arildo.appband.core.base.BaseViewModel
import dev.arildo.data.setlist.SetListRepository
import dev.arildo.data.setlist.model.SetList

class SetListViewModel(private val setListRepository: SetListRepository) : BaseViewModel() {

    private var _setList = MutableLiveData<List<SetList>>()
    val setList: LiveData<List<SetList>> = Transformations.map(_setList) { it }


    suspend fun getSetLists() {
        _setList.postValue(setListRepository.getSetLists().body()?.reversed())
    }
}