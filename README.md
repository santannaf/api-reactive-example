# api-reactive-example

API de exemplo utilizando programação reativa com spring web-flux, mongodb e kafka.

Requisitos para os serviços:

- Kotlin versão 1.4.32 ou mais recente
- JDK 11
- docker

Antes de subir os projetos rodem o comando na raiz do projeto.

docker-compose up -d 

No pacote possui dois módulos com os services, suba individualmente cada um dos serviços.

Abordagens aprendidas com web flux

- existe diferença entre programação reativa e sistemas reativos!
- programação reativa é um paradigma de programação onde temos o foco de desenvolver cada componente do sistema de forma assíncrona e non-blocking.
- Exception sem bloqueios
- Melhor escalabilidade
- Programação reativa é o início para construirmos um sistema reativo!
- backPressure!