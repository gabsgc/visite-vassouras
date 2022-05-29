package br.com.univassouras.visitevassouras.service

import br.com.univassouras.visitevassouras.model.atracao.AtracaoResponse
import br.com.univassouras.visitevassouras.model.restaurante.RestauranteResponse
import retrofit2.Response
import retrofit2.http.GET

interface ServiceInterface {
    @GET("api/atracoes")
    suspend fun getAtracoes() : Response<List<AtracaoResponse>>

    @GET("api/restaurantes")
    suspend fun getRestaurantes() : Response<List<RestauranteResponse>>
}