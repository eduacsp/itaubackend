# Teste Backend - Validador de Senha

Este projeto é uma aplicação Spring Boot escrita em Kotlin que valida senhas com base em critérios de complexidade específicos.

## Instruções para Executar o Projeto

1. **Clone o Repositório**

   ```bash
   git clone https://github.com/eduacsp/itaubackend.git
   cd itaubackend

2. **Compile e Execute a Aplicação**

Certifique-se de que o JDK 17 está instalado em seu sistema.

Compile e execute a aplicação usando o Gradle:

```bash
    ./gradlew bootRun
```

A aplicação será iniciada na porta 8088 por padrão.


3. **Endpoints**

#### Validar Senha

URL: http://localhost:8088/validacoes

Método: POST

Conteúdo do Corpo:

```
{
"senha": "SuaSenhaAqui"
}
```

#### Respostas
* 200 OK: Se a senha for válida.

```
{
"mensagem": "Senha válida"
}
```

* 400 Bad Request: Se a senha for inválida.
```
{
"mensagem": "Senha inválida",
"erros": ["A senha não atende aos critérios de complexidade."]
}
```

### Testar o Endpoint com curl

#### Senha Válida
```
curl -X POST http://localhost:8088/validacoes \
-H "Content-Type: application/json" \
-d '{"senha": "AbTp9!fok"}'
```

#### Senha Inválida
```
curl -X POST http://localhost:8088/validacoes \
-H "Content-Type: application/json" \
-d '{"senha": "invalida"}'
```

### Executando os Testes

Para executar os testes (incluindo unitários e integrados), utilize o seguinte comando Gradle:

```
./gradlew test
```

Esse comando irá compilar e executar todos os testes presentes no projeto, garantindo que a lógica de validação de senhas e os endpoints estejam funcionando corretamente.

### Dependências
* Kotlin
* Spring Boot
* Gradle
