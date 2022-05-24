package br.com.univassouras.visitevassouras.service

import br.com.univassouras.visitevassouras.model.atracao.AtracaoByIdResponse
import br.com.univassouras.visitevassouras.model.atracao.AtracaoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceInterface {
    @GET("api/atracoes")
    suspend fun getAtracoes() : Response<ArrayList<AtracaoResponse>>

    @GET("api/atracoes/{id}")
    suspend fun getAtracaoById(@Path("id") id: Int) : Response<AtracaoByIdResponse>
}