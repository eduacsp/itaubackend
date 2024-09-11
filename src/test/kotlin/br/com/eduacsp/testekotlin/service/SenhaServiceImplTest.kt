package br.com.eduacsp.testekotlin.service

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExtendWith(MockitoExtension::class)
class SenhaServiceImplTest {

    @InjectMocks
    lateinit var senhaService: SenhaServiceImpl

    private val logger: Logger = LoggerFactory.getLogger(SenhaServiceImplTest::class.java)

    @BeforeEach
    fun setUp() {
    }

    @Test
    fun `testa senha vazia`() {
        logger.info("Executando testa senha vazia")
        assertFalse(senhaService.isValid(""))
    }

    @Test
    fun `testa senha muito curta`() {
        logger.info("Executando testa senha muito curta")
        assertFalse(senhaService.isValid("aa"))
        assertFalse(senhaService.isValid("ab"))
    }

    @Test
    fun `testa senha sem caractere especial`() {
        logger.info("Executando testa senha sem caractere especial")
        assertFalse(senhaService.isValid("AAAbbbCc"))
    }

    @Test
    fun `testa senha com caracteres repetidos`() {
        logger.info("Executando testa senha com caracteres repetidos")
        assertFalse(senhaService.isValid("AbTp9!foo"))
        assertFalse(senhaService.isValid("AbTp9!foA"))
    }

    @Test
    fun `testa senha com espaços`() {
        logger.info("Executando testa senha com espaços")
        assertFalse(senhaService.isValid("AbTp9 fok"))
    }

    @Test
    fun `testa senha válida`() {
        logger.info("Executando testa senha válida")
        assertTrue(senhaService.isValid("AbTp9!fok"))
    }
}
