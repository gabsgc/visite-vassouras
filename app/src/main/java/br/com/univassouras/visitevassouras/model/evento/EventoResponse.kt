package br.com.univassouras.visitevassouras.model.evento

import java.io.Serializable

data class EventoResponse(
    var id: Int,
    var titulo: String?,
    var dataInicio: String?,
    var dataTermino: String?,
    var horario: String?,
    var valor: String?,
    var local: String?,
    var linkIngresso: String?,
    var ativo: Boolean?
) : Serializable