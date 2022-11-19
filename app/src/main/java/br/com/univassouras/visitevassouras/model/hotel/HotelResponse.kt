package br.com.univassouras.visitevassouras.model.hotel

import java.io.Serializable

data class HotelResponse(
    var id: Int,
    var nome: String?,
    var imagem: String?,
    var url: String?,
    var ativo: Boolean?
) : Serializable