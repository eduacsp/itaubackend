package br.com.eduacsp.testekotlin.service

interface SenhaService {
    fun isValid(senha: String): Boolean
}