# 🔐 Validador de Senhas - API em Java com Spring Boot

Bem-vindo ao Validador de Senhas, uma API desenvolvida com Java + Spring Boot, cujo objetivo é validar senhas de acordo com regras de segurança específicas e retornar se a senha é válida ou não.

## 📌 Funcionalidades

Esta API recebe uma senha e retorna:

**✅ true se a senha atender a todas as regras de negócio.**
**❌ Uma mensagem de erro personalizada indicando quais regras não foram atendidas.**

✅ Regras de Negócio

Para ser considerada válida, a senha deve:

Ter 9 ou mais caracteres

- **Conter ao menos 1 dígito**
- **Conter ao menos 1 letra minúscula**
- **Conter ao menos 1 letra maiúscula**
- **Conter ao menos 1 caractere especial (ex: !@#$%^&*()-+)**
- **Não conter caracteres repetidos**

## 🔄 Exemplo de Requisição

POST /validate-password
Content-Type: application/json

{
  "password": "Senhaforte1"
}

#🔄 Exemplo de Resposta
✔ Senha Válida

{
  "valid": true
}

#❌ Senha Inválida

{
  "valid": false,
  "errors": [
    "A senha deve ter pelo menos 9 caracteres.",
    "A senha não deve conter caracteres repetidos."
  ]
}

##⚙️ Tecnologias Utilizadas
- Java 17+
- Spring Boot
- Spring Web
- Maven
