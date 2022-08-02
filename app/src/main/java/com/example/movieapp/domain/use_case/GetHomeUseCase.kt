package com.example.movieapp.domain.use_case

import com.example.movieapp.config.Resource
import com.example.movieapp.data.remote.dto.toHome
import com.example.movieapp.domain.irepo.IRepository
import com.example.movieapp.domain.model.HomeTypeModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetHomeUseCase @Inject constructor(
    private val repository: IRepository
){
    operator fun invoke(): Flow<Resource<List<HomeTypeModel>>> = flow {
        try {
            emit(Resource.Loading())
            val popular = repository.getPopular().results.map { it.toHome() }
            val upcoming = repository.getUpcoming().results.map { it.toHome() }

            val listData = mutableListOf<HomeTypeModel>()

            listData.add(
                HomeTypeModel(popular, upcoming)
            )

            emit(Resource.Success(listData))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server. Check your internet connection."))
        }
    }
}