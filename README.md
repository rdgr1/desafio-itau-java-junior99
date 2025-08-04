🎯 Resolvi o Desafio de Programação do Itaú Unibanco — e foi uma baita experiência!

📌 O desafio era simples no papel, mas repleto de nuances que testam muito mais do que apenas saber criar uma API. A proposta: construir uma API REST capaz de registrar transações e calcular estatísticas dos últimos 60 segundos, tudo **em memória**, sem banco de dados.

🧠 O mais legal? Não bastava entregar funcionando — o desafio avalia arquitetura, clareza de código, testes, modularização, validações, performance e até boas práticas de engenharia.

💡 No meu processo:
- Modelei DTOs e Models separadamente, mantendo as responsabilidades claras.
- Criei um repositório 100% em memória com filtragem temporal.
- Implementei validações robustas no service (sem jogar tudo no controller!).
- Configurei o Swagger para documentação interativa.
- Escrevi testes com JUnit e Mockito.
- Organizei o projeto em camadas (Controller, Service, Repo, Mapper, DTO, Model).
- Incluir Spring Actuator para coletar métricas.
🛠️ No final, construí uma API sólida, testável, documentada e extensível.

Esse tipo de desafio é excelente para:
✅ Treinar boas práticas com Spring Boot
✅ Pensar em arquitetura limpa mesmo para projetos pequenos
✅ Trabalhar com manipulação temporal (como `OffsetDateTime`)
✅ Refletir sobre responsabilidade de classes e testabilidade

📁 Repositório: [Link do Repositório do Desafio](https://github.com/rafaellins-itau/desafio-itau-vaga-99-junior)

🚀 Bora continuar evoluindo. A jornada é longa, mas cada desafio conta!

#backend #java #springboot #desenvolvimento #engenhariadesoftware #desafioitau #codechallenge #openToWork
