package dev.arildo.appband.setlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import dev.arildo.appband.core.base.BaseViewModel
import dev.arildo.data.setlist.SetListRepository
import dev.arildo.data.setlist.model.SetList
import dev.arildo.data.song.exception.FailureRequestException
import dev.arildo.data.song.exception.FailureRequestWithLocalDataException
import kotlinx.coroutines.flow.collect

class SetListViewModel(private val setListRepository: SetListRepository) : BaseViewModel() {

    private var _setList = MutableLiveData<List<SetList>>()
    val setList: LiveData<List<SetList>> = Transformations.map(_setList) { it }


    suspend fun getSetLists() {
        try {
            setListRepository.getSetLists().collect { response ->

                if (response.isSuccessful) {
                    response.body()?.let { songList ->
                        _setList.postValue(songList)
                    }
                } else {
                    // error
                }
            }

        } catch (e: Exception) {
            when (e) {
                is FailureRequestException -> {
                    // error
                }
                is FailureRequestWithLocalDataException -> {
                    // show a toast to notify data is only local
                }
            }
        }
    }
}