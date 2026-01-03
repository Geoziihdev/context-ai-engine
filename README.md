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
