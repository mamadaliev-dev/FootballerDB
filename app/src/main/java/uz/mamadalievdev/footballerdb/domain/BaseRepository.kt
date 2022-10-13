package uz.mamadalievdev.footballerdb.domain

import kotlinx.coroutines.flow.Flow
import uz.mamadalievdev.footballerdb.data.base.BaseNetworkResult
import uz.mamadalievdev.footballerdb.data.response.FootballerResponse

interface BaseRepository {
    suspend fun getSearchedFootballers(query: String): Flow<BaseNetworkResult<FootballerResponse>>
}