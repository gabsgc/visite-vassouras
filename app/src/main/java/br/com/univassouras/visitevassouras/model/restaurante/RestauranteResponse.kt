package br.com.univassouras.visitevassouras.model.restaurante

import java.io.Serializable


data class RestauranteResponse(
    var id: Int,
    var nome: String?,
    var logotipo: String?,
    var urlSite: String?
) : Serializable