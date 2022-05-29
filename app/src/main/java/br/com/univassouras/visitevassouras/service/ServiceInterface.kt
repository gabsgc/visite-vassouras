package br.com.univassouras.visitevassouras.service

import br.com.univassouras.visitevassouras.model.atracao.AtracaoResponse
import retrofit2.Response
import retrofit2.http.GET

interface ServiceInterface {
    @GET("api/atracoes")
    suspend fun getAtracoes() : Response<List<AtracaoResponse>>
}