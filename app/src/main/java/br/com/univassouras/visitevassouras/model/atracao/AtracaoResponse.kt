package br.com.univassouras.visitevassouras.model.atracao

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class AtracaoResponse(
    var id: Int,
    var nome: String?,
    var imgPrincipal: String?,
    var horarioFuncionamento: String?,
    var diasFuncionamento: String?,
    var endereco: String?,
    var descricao: String?,
    var imgSecundaria: String?,
    var rota: String?,
    var ativo: Boolean?
) : Parcelable