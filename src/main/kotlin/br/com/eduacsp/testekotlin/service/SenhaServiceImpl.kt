package br.com.eduacsp.testekotlin.service

import org.springframework.stereotype.Service

@Service
class SenhaServiceImpl : SenhaService {

    private val regex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()\\-+])[A-Za-z\\d!@#$%^&*()\\-+]{9,}$")

    override fun isValid(senha: String): Boolean {
        return verificaRegex(senha) && verificaCaracteresUnicos(senha)
    }

    private fun verificaRegex(senha: String): Boolean {
        return senha.matches(regex)
    }

    private fun verificaCaracteresUnicos(senha: String): Boolean {
        val chars: MutableSet<Char> = HashSet()
        for (c in senha) {
            if (!chars.add(c)) {
                return false
            }
        }
        return true
    }
}
