package dev.arildo.data.setlist

import dev.arildo.data.setlist.datasource.local.SetListLocalDataSource
import dev.arildo.data.setlist.datasource.remote.SetListRemoteDataSource
import dev.arildo.data.setlist.dto.SetListDTO
import dev.arildo.data.setlist.model.SetList
import dev.arildo.data.song.exception.FailureRequestException
import dev.arildo.data.song.exception.FailureRequestWithLocalDataException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

@ExperimentalCoroutinesApi
class SetListRepositoryImpl(
    private val remoteDataSource: SetListRemoteDataSource,
    private val localDataSource: SetListLocalDataSource
) : SetListRepository {

    override suspend fun getSetLists(): Flow<Response<List<SetList>>> {
        return flow {
            localDataSource.getSetLists().collect { local ->
                if (local.isNotEmpty()){
                    emit(Response.success(local))
                }
                try {
                    remoteDataSource.getSetLists().body()?.let { remote ->
                        if (!local.containsAll(remote)) {
                            insertAll(remote)
                            emit(Response.success(remote))
                        }
                    }
                } catch (e: Exception){
                    if (local.isNotEmpty()){
                        throw FailureRequestWithLocalDataException()
                    } else {
                        throw FailureRequestException()
                    }
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun createSetList(setListDTO: SetListDTO): Response<SetList> {
        return remoteDataSource.createSetList(setListDTO)
    }

    override suspend fun insertAll(setLists: List<SetList>) {
        localDataSource.insertAll(setLists)
    }

}