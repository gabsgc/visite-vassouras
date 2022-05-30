package br.com.univassouras.visitevassouras.service

import br.com.univassouras.visitevassouras.model.atracao.AtracaoResponse
import br.com.univassouras.visitevassouras.model.evento.EventoResponse
import br.com.univassouras.visitevassouras.model.hotel.HotelResponse
import br.com.univassouras.visitevassouras.model.restaurante.RestauranteResponse
import retrofit2.Response
import retrofit2.http.GET

interface ServiceInterface {
    @GET("api/atracoes")
    suspend fun getAtracoes() : Response<List<AtracaoResponse>>

    @GET("api/restaurantes")
    suspend fun getRestaurantes() : Response<List<RestauranteResponse>>

    @GET("api/hoteis")
    suspend fun getHoteis() : Response<List<HotelResponse>>

    @GET("api/eventos")
    suspend fun getEventos() : Response<List<EventoResponse>>
}