package com.arildojr.appband.setlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.arildojr.appband.core.base.BaseViewModel
import com.arildojr.data.setlist.SetListRepository
import com.arildojr.data.setlist.model.SetList

class SetListViewModel(private val setListRepository: SetListRepository) : BaseViewModel() {

    private var _setList = MutableLiveData<List<SetList>>()
    val setList: LiveData<List<SetList>> = Transformations.map(_setList) { it }


    suspend fun getSetLists() {
        _setList.postValue(setListRepository.getSetLists().body())
    }
}