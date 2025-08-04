#🎯 Resolvi o Desafio de Programação do Itaú Unibanco — e foi uma baita experiência!

📌 O desafio era simples no papel, mas repleto de nuances que testam muito mais do que apenas saber criar uma API. A proposta: construir uma API REST capaz de registrar transações e calcular estatísticas dos últimos 60 segundos, tudo **em memória**, sem banco de dados porém optei por usar PostgresSQL pra treinar minha eficiência com medias queries e sql puro.

O Desafio avalia arquitetura, clareza de código, testes, modularização, validações, performance e até boas práticas de engenharia.

💡 No meu processo:
- Modelei DTOs e Models separadamente, mantendo as responsabilidades claras.
- Criei um repositório 100% com filtragem temporal.
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

📁 Repositório de Referência: [Link do Repositório do Desafio](https://github.com/rafaellins-itau/desafio-itau-vaga-99-junior)


