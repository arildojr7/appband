package dev.arildo.data.song

import dev.arildo.data.song.datasource.local.SongLocalDataSource
import dev.arildo.data.song.datasource.remote.SongRemoteDataSource
import dev.arildo.data.song.exception.FailureRequestException
import dev.arildo.data.song.exception.FailureRequestWithLocalDataException
import dev.arildo.data.song.model.Song
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

@ExperimentalCoroutinesApi
class SongRepositoryImpl(private val songRemoteDataSource: SongRemoteDataSource,
                         private val songLocalDataSource: SongLocalDataSource) : SongRepository {
    override fun getSongs() : Flow<Response<List<Song>>> {
        return flow {
            songLocalDataSource.getSongs().collect { local ->
                if (local.isNotEmpty()){
                    emit(Response.success(local))
                }
                try {
                    songRemoteDataSource.getSongs().body()?.let { remote ->
                        if (!local.containsAll(remote)) {
                            insertAll(remote)
                            emit(Response.success(remote+remote+remote+remote+remote+remote+remote+remote+remote+remote))
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

    override fun getSongsFiltered(filter: String): Flow<Response<List<Song>>> {
        return flow {
            songLocalDataSource.getSongsFiltered(filter).collect { results ->
                emit(Response.success(results))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun insertAll(songs: List<Song>) {
        songLocalDataSource.insertAll(songs)
    }
}