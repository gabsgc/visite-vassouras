package br.com.univassouras.visitevassouras.model.atracao

import java.io.Serializable

data class AtracaoById (
    var id: Int,
    var nome: String?,
    var horarioFuncionamento: String?,
    var diasFuncionamento: String?,
    var endereco: String?,
    var descricao: String?,
    var imagemPrincipal: String?,
    var imagemSecundaria: String?,
    var rota: String?
) : Serializable