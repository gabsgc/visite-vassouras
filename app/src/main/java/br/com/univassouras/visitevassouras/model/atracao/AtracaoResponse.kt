package br.com.univassouras.visitevassouras.model.atracao

import java.io.Serializable

data class AtracaoResponse (
    var id: Int,
    var nome: String,
    var imagemPrincipal: String
) : Serializable