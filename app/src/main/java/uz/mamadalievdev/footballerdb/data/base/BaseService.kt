package uz.mamadalievdev.footballerdb.data.base

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.mamadalievdev.footballerdb.data.response.FootballerResponse

interface BaseService {
    @GET("searchplayers.php")
    suspend fun getSearchedArticles(
        @Query("p") p: String,
    ): Response<FootballerResponse>
}