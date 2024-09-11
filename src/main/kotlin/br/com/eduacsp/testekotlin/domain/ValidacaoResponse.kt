package br.com.eduacsp.testekotlin.domain

data class ValidacaoResponse(
    val mensagem: String,
    val erros: List<String>? = null
)
