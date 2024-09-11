package br.com.eduacsp.testekotlin.controller

import br.com.eduacsp.testekotlin.service.SenhaService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
class SenhaControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var senhaService: SenhaService

    @Test
    fun `deve retornar BAD REQUEST para senha vazia`() {
        Mockito.`when`(senhaService.isValid("")).thenReturn(false)

        mockMvc.post("/validacoes") {
            contentType = MediaType.APPLICATION_JSON
            content = """{"senha": ""}"""
        }
            .andExpect {
                status { isBadRequest() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.mensagem") { value("Senha inválida") }
                jsonPath("$.erros[0]") { value("A senha não atende aos critérios de complexidade.") }
            }
    }

    @Test
    fun `deve retornar BAD REQUEST para senha curta`() {
        val senhasInvalidas = listOf("aa", "ab", "AAAbbbCc", "AbTp9!foo", "AbTp9!foA", "AbTp9 fok")
        senhasInvalidas.forEach { senha ->
            Mockito.`when`(senhaService.isValid(senha)).thenReturn(false)

            mockMvc.post("/validacoes") {
                contentType = MediaType.APPLICATION_JSON
                content = """{"senha": "$senha"}"""
            }
                .andExpect {
                    status { isBadRequest() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.mensagem") { value("Senha inválida") }
                    jsonPath("$.erros[0]") { value("A senha não atende aos critérios de complexidade.") }
                }
        }
    }

    @Test
    fun `deve retornar OK para senha válida`() {
        val senhaValida = "AbTp9!fok"
        Mockito.`when`(senhaService.isValid(senhaValida)).thenReturn(true)

        mockMvc.post("/validacoes") {
            contentType = MediaType.APPLICATION_JSON
            content = """{"senha": "$senhaValida"}"""
        }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.mensagem") { value("Senha válida") }
            }
    }
}
