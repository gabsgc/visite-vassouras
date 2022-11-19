package br.com.univassouras.visitevassouras.model.restaurante

import java.io.Serializable


data class RestauranteResponse(
    var id: Int,
    var nome: String?,
    var logo: String?,
    var url: String?,
    var ativo: Boolean?
) : Serializable