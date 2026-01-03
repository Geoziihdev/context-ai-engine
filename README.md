# Context-AI Engine: Triagem Inteligente com Java & LLM

O **Context-AI Engine** é uma API desenvolvida em Java e Spring Boot que utiliza Inteligência Artificial para automatizar a triagem, classificação e priorização de ocorrências corporativas (RH, Financeiro e Técnico).

Este projeto representa a evolução de uma solução anterior desenvolvida em Node.js, agora reconstruída em Java para explorar os benefícios de uma arquitetura orientada a objetos, tipagem forte e persistência polimórfica.

---

## O Problema
Empresas recebem centenas de solicitações diárias que precisam ser lidas, categorizadas e encaminhadas para o setor correto. O Context-AI resolve isso utilizando Processamento de Linguagem Natural para decidir em milissegundos o setor e a urgência de cada chamado.

## 🛠️ Evolução Técnica (Node.js ➡️ Java)
Diferente da versão anterior, esta implementação foca em:
- **Polimorfismo JPA:** Uso de `@Inheritance(strategy = InheritanceType.SINGLE_TABLE)` para gerenciar diferentes tipos de ocorrências em uma única estrutura de dados eficiente.
- **Design Patterns:** Implementação de regras de negócio específicas para cada setor através de herança de classes.
- **Integração com LLM:** Conexão direta com o modelo Llama 3 via **Groq Cloud API** para análise contextual.

## Tecnologias Utilizadas
- **Linguagem:** Java 21
- **Framework:** Spring Boot 3.x
- **Persistência:** Spring Data JPA / Hibernate
- **Banco de Dados:** PostgreSQL (Produção) / H2 (Testes)
- **IA:** Groq API (Llama-3.3-70b)
- **Segurança:** Variáveis de ambiente para chaves de API

## Arquitetura do Sistema
O sistema utiliza uma estrutura de classes especializada:
- `Ocorrencia` (Classe Base)
    - `OcorrenciaRH` (Campos: CPF, Categoria)
    - `OcorrenciaFinanceira` (Campos: Valor em Risco, Transação)
    - `OcorrenciaTecnica` (Campos: Equipamento, Status do Sistema)

---

## Resultados e Persistência
O projeto utiliza a estratégia de Herança (Single Table) do JPA, permitindo que diferentes tipos de ocorrências sejam armazenados na mesma tabela, mantendo a integridade e facilitando consultas complexas.

1. **Persistência no PostgreSQL**
Através do console do banco de dados, podemos ver como a IA classifica e rotula cada entrada automaticamente:


```bash
SELECT id, dtype, relato, prioridade_definida FROM ocorrencia;
```

<img width="1402" height="960" alt="Captura de tela 2026-01-03 203929" src="https://github.com/user-attachments/assets/5fe3bdee-96fa-488c-a496-5eb085239f6f" />


2. **Consumo da API (GET)**
Endpoint REST que retorna todos os objetos polimórficos processados:


```bash
GET /api/ocorrencias
```


<img width="1100" height="836" alt="Captura de tela 2026-01-03 200741" src="https://github.com/user-attachments/assets/a4f49ac3-85fa-4271-a701-69f2078559e7" />



3. **Arquitetura de Classes**
Abaixo, a implementação da classe base que utiliza polimorfismo para o cálculo de urgência:


<img width="1844" height="925" alt="Captura de tela 2026-01-03 203032" src="https://github.com/user-attachments/assets/2dcf3aa6-c9d6-4a69-a354-b384c9d65974" />


---



## Como Executar o Projeto

1. **Clone o repositório:**
   ```bash
   git clone [https://github.com/seu-usuario/context-ai-engine.git](https://github.com/seu-usuario/context-ai-engine.git)

2. **Configure sua API Key: No arquivo src/main/resources/application.properties, adicione sua chave da Groq:**
```bash
   groq.api.key=${GROQ_API_KEY}
```
3. **Execute a aplicação:**
   ```bash
   mvn spring-boot:run
   ```
## Exemplos de Uso (Endpoints)
# Criar Ocorrência Técnica
**POST /api/ocorrencias/tecnica**
```bash
{
    "relato": "O servidor de banco de dados parou de responder.",
    "equipamento": "Dell PowerEdge",
    "sistemaForaDoAr": true,
    "setor": { "nome": "TI" }
}
```
## Próximos Passos
[ ] Implementação de Dashboard com estatísticas de prioridade.

[ ] Interface Frontend em React/Angular.

[ ] Notificação automática via E-mail/Slack para ocorrências CRÍTICAS.

Desenvolvido por Geovana J Santos - [LinkedIn](https://www.linkedin.com/in/geovana-jsantos/)
