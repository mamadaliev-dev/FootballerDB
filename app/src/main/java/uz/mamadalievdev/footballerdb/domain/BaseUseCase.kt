package uz.mamadalievdev.footballerdb.domain

import kotlinx.coroutines.flow.Flow
import uz.mamadalievdev.footballerdb.data.base.BaseNetworkResult
import uz.mamadalievdev.footballerdb.data.response.FootballerResponse
import javax.inject.Inject

class BaseUseCase @Inject constructor(private val homeRepository: BaseRepository) {
    suspend fun getSearchedFootballers(query: String): Flow<BaseNetworkResult<FootballerResponse>> {
        return homeRepository.getSearchedFootballers(query)
    }
}