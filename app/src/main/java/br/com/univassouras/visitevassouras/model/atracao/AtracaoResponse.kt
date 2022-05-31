package br.com.univassouras.visitevassouras.model.atracao

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class AtracaoResponse(
    var id: Int,
    var nome: String?,
    var imagemPrincipal: String?,
    var horarioFuncionamento: String?,
    var diasFuncionamento: String?,
    var endereco: String?,
    var descricao: String?,
    var imagemSecundaria: String?,
    var rota: String?
) : Parcelable