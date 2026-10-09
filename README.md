# Padrões de projeto com Java: ByteBank

Projeto educacional para o desafio **Explorando Padrões de Projetos na Prática com Java**, da DIO. Demonstra Singleton, Strategy e Facade em operações sobre uma conta fictícia. Implementação própria em Java puro, sem bibliotecas externas.

## Executar

Requisito: JDK 21 ou superior, com `java` e `javac` no PATH.

No PowerShell, a partir da raiz do repositório:

```powershell
.\verificar.ps1
java -cp build br.com.bytebank.Aplicacao
```

O script compila com `--release 21`, executa os testes e encerra com erro se a compilação ou qualquer verificação falhar. Se sua política local impedir scripts, os comandos equivalentes são:

```powershell
New-Item -ItemType Directory -Force build | Out-Null
$fontes = @(Get-ChildItem src -Recurse -Filter '*.java' | ForEach-Object FullName)
javac --release 21 -encoding UTF-8 -d build @fontes
java -cp build br.com.bytebank.Testes
java -cp build br.com.bytebank.Aplicacao
```

No Linux/macOS:

```sh
mkdir -p build
find src -name '*.java' > build/fontes.txt
javac --release 21 -encoding UTF-8 -d build @build/fontes.txt
java -cp build br.com.bytebank.Testes
java -cp build br.com.bytebank.Aplicacao
```

## Padrões utilizados

| Padrão | Classes | Função |
| --- | --- | --- |
| Strategy | `OperacaoBancaria`, `Deposito`, `Saque` | Cada operação implementa o mesmo contrato e pode ser selecionada pela fachada. |
| Facade | `BancoFacade` | Oferece um único método para selecionar a estratégia, executar a operação e registrar o sucesso. |
| Singleton | `RegistroOperacoes` | Enum com uma única instância, compartilhada pelas fachadas no processo Java. |

`Conta` mantém as invariantes do saldo. A fachada aceita exatamente `DEPOSITO` e `SAQUE`. Uma nova estratégia exige implementação do contrato e inclusão no mapa de operações da fachada.

## Regras e exemplo

Saldo inicial deve ser não negativo. Valor deve ser positivo. Saques não podem exceder o saldo. Tipo desconhecido, argumentos nulos e valores inválidos geram `IllegalArgumentException`. Falhas de validação preservam o saldo e não geram registro de sucesso.

Dinheiro usa `BigDecimal` construído a partir de strings. O exemplo não arredonda valores nem impõe quantidade de casas decimais; isso mantém a precisão dos valores fornecidos.

Saída da demonstração:

```text
Deposito: 150.00
Saque: 120.00
Erro: Saldo insuficiente
Saldo preservado: 120.00
Operacoes registradas:
DEPOSITO 50.00 | saldo: 150.00
SAQUE 30.00 | saldo: 120.00
```

## Verificação

32 verificações automatizadas cobrem depósito, saque, saldo insuficiente, valores zero/negativos/nulos, saldo inicial inválido, precisão decimal, saque total, tipo inválido, falhas sem efeitos, Singleton e snapshot imutável do registro. Os testes usam verificações explícitas, portanto não dependem da opção `-ea` da JVM. O workflow executa compilação e testes a cada push e pull request.

## Limitações

Aplicação de console, sem API REST ou Spring. A descrição da DIO permite um projeto em Java puro; esta entrega explora os três padrões apresentados nas aulas. Não é um sistema bancário de produção: não há autenticação, persistência, transações, auditoria durável ou proteção concorrente do saldo. O Singleton guarda dados somente durante a execução e o registro cresce em memória. Os exemplos não usam dados reais.

Autor: [eovitu](https://github.com/eovitu).
