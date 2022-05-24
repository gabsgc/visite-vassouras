package br.com.univassouras.visitevassouras.model.atracao

import java.io.Serializable

data class AtracaoByIdResponse(
    var sucesso: Boolean,
    var mensagem: String,
    var conteudo: AtracaoById
) : Serializable