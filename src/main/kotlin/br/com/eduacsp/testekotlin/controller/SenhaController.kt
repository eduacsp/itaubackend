package br.com.eduacsp.testekotlin.controller

import br.com.eduacsp.testekotlin.domain.ValidacaoResponse
import br.com.eduacsp.testekotlin.service.SenhaService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

data class SenhaRequest(val senha: String)

@RestController
class SenhaController @Autowired constructor(private val senhaService: SenhaService) {

    private val logger: Logger = LoggerFactory.getLogger(SenhaController::class.java)

    @PostMapping("/validacoes")
    fun validar(@RequestBody request: SenhaRequest): ResponseEntity<ValidacaoResponse> {
        logger.info("Validando a senha recebida.")
        return if (senhaService.isValid(request.senha)) {
            ResponseEntity.ok(ValidacaoResponse(mensagem = "Senha válida"))
        } else {
            ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ValidacaoResponse(mensagem = "Senha inválida",
                    erros = listOf("A senha não atende aos critérios de complexidade.")))
        }
    }
}
