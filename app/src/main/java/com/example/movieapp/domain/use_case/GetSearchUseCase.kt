package com.example.movieapp.domain.use_case

import com.example.movieapp.config.Resource
import com.example.movieapp.data.remote.dto.toDetail
import com.example.movieapp.data.remote.dto.toSearch
import com.example.movieapp.domain.irepo.IRepository
import com.example.movieapp.domain.model.Search
import com.example.movieapp.domain.model.SearchTypeModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetSearchUseCase @Inject constructor(
    private val repository : IRepository
) {
   var query : String = "a"
   operator fun invoke() : Flow<Resource<List<SearchTypeModel>>> = flow {
       try {
           emit(Resource.Loading())

           val listSearch = repository.getSearch(query).results.map { it.toSearch() }

           val data : List<SearchTypeModel> = listOf(SearchTypeModel(listSearch))

           emit(Resource.Success(data))
       } catch (e : HttpException) {
           emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred. "))
       } catch (e : IOException) {
           emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server. Check your internet connection."))
       }
   }
}