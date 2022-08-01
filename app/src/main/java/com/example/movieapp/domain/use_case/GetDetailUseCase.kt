package com.example.movieapp.domain.use_case

import com.example.movieapp.config.Resource
import com.example.movieapp.data.remote.dto.toDetail
import com.example.movieapp.domain.irepo.IRepository
import com.example.movieapp.domain.model.Detail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetDetailUseCase @Inject constructor(
    private val repository: IRepository
) {
    var id = 0
    operator fun invoke(): Flow<Resource<List<Detail>>> = flow {
        try {
            emit(Resource.Loading())

            val detail = listOf(repository.getDetail(id).toDetail())

            emit(Resource.Success(detail))
        } catch (e : HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred. "))
        } catch (e : IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server. Check your internet connection."))
        }
    }

}