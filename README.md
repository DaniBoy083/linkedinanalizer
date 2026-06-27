# Projeto Prático Final: O Nosso LinkedIn Analyzer 🚀

E aí, pessoal! Prontos para colocar a mão na massa e resolver um problema do mundo real usando grafos?

Nesse projeto final, o desafio de vocês é construir um motor de análises e recomendações para uma rede social de conexões profissionais (estilo o LinkedIn). A gente vai usar como base a estrutura de grafos que fomos construindo nas nossas aulas.

---

## 👥 Equipe de Desenvolvimento

| Nome Completo | RGM |
|---|---|
| Caique dos Santos Brito | 46972960 |
| Gabriel Arthur Andrade Silva | 38418631 |
| Pietro Santana Fragoso Vasconcelos | 38187515 |
| Daniel Costa Carvalho Martins | 37196201 |

---

## 🔗 Repositório

**GitHub:** [https://github.com/DaniBoy083/linkedinanalizer](https://github.com/DaniBoy083/linkedinanalizer)

> ⚠️ O repositório está público para fins de avaliação. O código presente no repositório é exatamente o utilizado na apresentação em vídeo.

---

## 🎬 Vídeo Explicativo

> Link do YouTube será adicionado após a gravação e publicação do vídeo.

---

## 🏗️ Arquitetura do Projeto

O projeto segue uma estrutura Gradle padrão para Java, organizado da seguinte forma:

```
LINKEDIN_ANALIZER/
├── .gradle/                  # Arquivos internos de cache do Gradle
├── .idea/                    # Configurações do IntelliJ IDEA
├── bin/                      # Bytecode compilado gerado automaticamente
├── build/                    # Artefatos de build gerados pelo Gradle
├── deploy/                   # Armazenamento do vídeo explicativo antes do upload para o YouTube
├── gradle/                   # Wrapper e configurações do Gradle
├── src/
│   └── main/
│       └── java/
│           ├── Aresta.java         # Representa uma aresta do grafo (conexão entre dois vértices com peso)
│           ├── Grafo.java          # Estrutura do grafo não-direcionado ponderado + algoritmo de Dijkstra
│           ├── Main.java           # Ponto de entrada: configura o cenário de testes e executa as análises
│           └── Vertice.java        # Representa um vértice do grafo (perfil de usuário da rede)
├── .gitignore
├── build.gradle.kts          # Script de build do projeto (dependências, plugins, configurações)
├── gradlew                   # Script de execução do Gradle para Linux/macOS
├── gradlew.bat               # Script de execução do Gradle para Windows
├── README.md                 # Documentação do projeto
└── settings.gradle.kts       # Configurações gerais do projeto Gradle
```

### Descrição dos componentes principais

- **`Vertice.java`** — Modela um nó do grafo, armazenando o nome do usuário e sua lista de adjacências.
- **`Aresta.java`** — Modela uma conexão entre dois vértices, carregando o peso (afinidade) da relação.
- **`Grafo.java`** — Núcleo da estrutura de dados. Gerencia os vértices e arestas e implementa o algoritmo de Dijkstra para cálculo de menor caminho ponderado.
- **`Main.java`** — Classe principal. Instancia o grafo com o cenário de testes sugerido e chama os métodos do `LinkedInAnalyzer` para validação de todas as missões.
- **`deploy/`** — Pasta destinada a armazenar o arquivo de vídeo do grupo antes de realizar o upload para o YouTube. Não contém código-fonte.

---

## 🛠️ Como a nossa rede funciona?

A rede de contatos é modelada como um **Grafo Não-Direcionado e Ponderado**:

- **Quem são as pessoas (Vértices):** Perfis dos usuários.
- **Conexões (Arestas):** Relacionamentos de amizade ou de trabalho (se a Ana tá conectada com o Bruno, o Bruno tá conectado com a Ana).
- **Intensidade da conexão (Pesos):** Representam a afinidade ou a "proximidade" entre as pessoas:
  - **Peso 1 (Muita Afinidade):** Galera que trabalha junto na mesma equipe ou se fala todo dia.
  - **Peso 5 (Pouca Afinidade):** Se adicionaram por educação na rede, mas quase não interagem.

---

## 📋 As Missões do Projeto

Vocês vão precisar criar uma classe chamada `LinkedInAnalyzer`. A ideia aqui é criar o cérebro das análises.
Lembrem-se: **uma das etapas iniciais será implementar um algoritmo clássico de menor caminho para grafos ponderados na classe `Grafo`**, já que vocês vão precisar dele para resolver algumas das missões abaixo.

### 1. Construtor da Análise

- **O que recebe (Input):** A instância do grafo (`Grafo`) que representa a rede social.
- **O que faz:** Guarda essa instância para que as outras missões possam usá-la.

### 2. Sugestão de Conexões (Amigos de 2º Grau)

- **O que recebe (Input):** O nome de uma pessoa (ex: `"Ana"`).
- **O que faz:** Descobre quem são aquelas pessoas que são "amigas de amigas", mas que o usuário ainda não adicionou direto.
- **Regras:**
  1. Não vale sugerir quem o usuário já tem como contato direto (1º grau).
  2. Não vale sugerir o próprio usuário para ele mesmo.
  3. A lista final precisa vir bem organizada: coloque no topo quem tem mais amigos em comum com o usuário (ordem decrescente).
- **Retorno esperado:** Uma estrutura contendo os nomes das pessoas sugeridas e a quantidade de amigos em comum com cada uma.

### 3. Grau de Separação (Quantos "passos" de distância?)

- **O que recebe (Input):** O nome de duas pessoas (Origem e Destino).
- **O que faz:** Descobre a quantos "passos" de conexão direta/indireta essas pessoas estão uma da outra (ex: 1 se forem contatos diretos, 2 se for amigo de amigo, etc.).
- **O desafio:** Pensem em como navegar de forma otimizada para achar o caminho com o menor número de conexões intermediárias.
- **Retorno esperado:** O número de passos (inteiro), ou `-1` se os dois perfis forem totalmente isolados (sem conexão).

### 4. Rota e Custo de Maior Afinidade

- **O que recebe (Input):** O nome de duas pessoas (Origem e Destino).
- **O que faz:** Encontra a melhor rota (a de maior afinidade acumulada, ou seja, com a menor soma de pesos das conexões) entre a origem e o destino, e calcula o custo total dessa rota.
- **Retorno esperado:** Vocês devem retornar tanto a sequência ordenada de nomes que formam esse melhor caminho quanto o custo acumulado (a soma dos pesos). Podem fazer isso usando métodos separados ou uma estrutura unificada. Se os perfis forem inalcançáveis, deve indicar custo `-1` e caminho vazio.

### 5. Mapear Grupos Isolados (Sub-redes)

- **O que recebe (Input):** Nenhum parâmetro (faz a varredura na rede inteira).
- **O que faz:** Acha todos os grupos de pessoas que estão conectadas entre si, mas totalmente isoladas dos outros grupos (componentes conexos).
- **Retorno esperado:** Uma lista ou coleção agrupando os usuários de cada sub-rede identificada.

---

## 🧑‍💻 Divisão de Responsabilidades

Cada integrante atua como desenvolvedor sênior e é responsável por uma fatia bem definida do projeto, da modelagem à entrega.

### Caique dos Santos Brito — RGM 46972960
**Responsável por:** Estrutura base do grafo (`Vertice.java` e `Aresta.java`) e configuração do ambiente de build.
- Modelagem e implementação de `Vertice.java` (lista de adjacências, métodos de acesso)
- Modelagem e implementação de `Aresta.java` (peso, origem, destino)
- Configuração do projeto Gradle (`build.gradle.kts`, `settings.gradle.kts`)
- Manutenção do `.gitignore` e estrutura inicial do repositório GitHub

### Gabriel Arthur Andrade Silva — RGM 38418631
**Responsável por:** Implementação do algoritmo de Dijkstra em `Grafo.java` e da missão de Rota e Custo de Maior Afinidade.
- Implementação do algoritmo de Dijkstra (menor caminho ponderado) dentro da classe `Grafo`
- Missão 4: `rotaDeMaiorAfinidade(origem, destino)` — retorna sequência de nomes e custo acumulado
- Validação do cenário Ana → Fernanda (custo 3 via Bruno e Eduardo vs. custo 13 via Daniela)
- Testes unitários manuais e revisão da lógica de reconstrução de caminho

### Pietro Santana Fragoso Vasconcelos — RGM 38187515
**Responsável por:** Missões de Sugestão de Conexões e Grau de Separação no `LinkedInAnalyzer`.
- Missão 2: `sugerirConexoes(nome)` — amigos de 2º grau ordenados por amigos em comum
- Missão 3: `grauDeSeparacao(origem, destino)` — BFS para menor número de saltos, retorna `-1` se inalcançável
- Garantia das regras de filtragem (sem duplicatas, sem o próprio usuário, sem contatos diretos)
- Definição dos tipos de retorno e contratos dos métodos acima

### Daniel Costa Carvalho Martins — RGM 37196201
**Responsável por:** Missão de Mapeamento de Grupos Isolados, classe `Main.java` e documentação.
- Missão 5: `mapearGruposIsolados()` — DFS/BFS para identificar componentes conexos da rede
- Implementação completa de `Main.java` com o cenário de testes sugerido (Ana, Bruno, Carlos, Daniela, Eduardo, Fernanda, Gabriel, Hugo, Igor, Juliana)
- Atualização e manutenção deste `README.md`
- Gravação e publicação do vídeo explicativo no YouTube, upload dos arquivos na pasta `deploy/`

---

## 💡 Sugestão de Cenário para Testes

Para ajudar vocês a validarem o código, aqui está uma sugestão de rede para cadastrar no `main`:

- **Pessoas na Rede Principal:** Ana, Bruno, Carlos, Daniela, Eduardo e Fernanda.
- **Grupo Isolado 1:** Gabriel e Hugo (só conversam entre si).
- **Grupo Isolado 2:** Igor e Juliana (só conversam entre si).

### Conexões e Afinidades (Pesos):

1. **Ana** <-> **Bruno** (Peso 1 - Trabalham muito próximos)
2. **Ana** <-> **Carlos** (Peso 2)
3. **Ana** <-> **Daniela** (Peso 8)
4. **Bruno** <-> **Eduardo** (Peso 1)
5. **Carlos** <-> **Eduardo** (Peso 1)
6. **Daniela** <-> **Fernanda** (Peso 5)
7. **Eduardo** <-> **Fernanda** (Peso 1)
8. **Gabriel** <-> **Hugo** (Peso 1)
9. **Igor** <-> **Juliana** (Peso 1)

**Por que esse cenário é legal?**
Se você pedir a rota de maior afinidade de **Ana** para **Fernanda**:

- A rota mais curta em passos seria `Ana -> Daniela -> Fernanda` (com apenas 2 conexões/saltos, ou seja, 1 intermediário).
- Mas se você somar os pesos dessa rota curta, o custo é 8 + 5 = 13.
- Já a rota `Ana -> Bruno -> Eduardo -> Fernanda` é mais longa em número de passos (3 conexões/saltos, com 2 intermediários), porém seu custo é 1 + 1 + 1 = 3.
- O algoritmo de menor caminho ponderado (Dijkstra) deve encontrar a rota de custo 3, provando que a rota mais curta em número de conexões (passos) nem sempre é a de maior afinidade (menor custo ponderado)!

---

## 📂 O que vocês precisam entregar?

Para que o projeto seja avaliado, o grupo deve entregar:

1. **Código-Fonte**:
   - **A classe `Grafo` atualizada**: Contendo a implementação do algoritmo de menor caminho em grafos ponderados que vocês desenvolveram.
   - **`LinkedInAnalyzer.java`**: Com as missões descritas acima implementadas.
   - **`LinkedInApp.java`**: Uma classe com o método `main` configurada para rodar o cenário de testes sugerido.
2. **Link de um Vídeo Explicativo (YouTube)**:
   - Vocês precisam gravar um vídeo explicando detalhadamente o código, **linha por linha**, justificando e demonstrando que entendem tudo o que foi produzido.
   - Esse vídeo deve ser enviado através de um link do **YouTube**.
   - ⚠️ **Atenção:** Garantam que o vídeo está acessível (público ou não listado). Se o link estiver quebrado ou o vídeo inacessível na correção, a nota do grupo será **zero**.
3. **Link do Repositório (GitHub)**:
   - Enviem o link do repositório no GitHub contendo o código exato utilizado na apresentação do vídeo.
   - ⚠️ **Atenção:** O repositório deve ser **público**. Se estiver privado, não conseguirei avaliar e a nota também será **zero**.

---

## 👥 Regras de Formação e Envio

- **Tamanho dos grupos:** No máximo **5 pessoas**. Grupos com mais de 5 pessoas **não serão aceitos**.
- **Identificação:** A entrega deve conter o nome completo e o **RGM** de todos os integrantes do grupo.
- **Envio:** Apenas **um membro** do grupo precisa realizar o envio contendo os links e as informações de todos.

---

Bora codar! Qualquer dúvida, chamem.