package uz.mamadalievdev.footballerdb.data.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.mamadalievdev.footballerdb.data.response.FootballerResponse
import uz.mamadalievdev.footballerdb.domain.BaseRepository
import javax.inject.Inject

class BaseRepositoryImpl @Inject constructor(private val service: BaseService) :
    BaseRepository {

    override suspend fun getSearchedFootballers(query: String): Flow<BaseNetworkResult<FootballerResponse>> {
        return flow {
            emit(BaseNetworkResult.Loading(true))
            val response =
                service.getSearchedArticles(p = query)
            emit(BaseNetworkResult.Loading(false))
            if (response.code() == 200) {
                emit(BaseNetworkResult.Success(response.body()!!))
            } else {
                emit(BaseNetworkResult.Error("Xatolik"))
            }
        }
    }
}