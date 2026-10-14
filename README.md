# 🏢 Sistema de Gestão de Estacionamento - Smart Parking

## 🎯 O que o projeto faz?

O sistema simula o controle operacional e financeiro de um pátio de estacionamento rotativo. O usuário pode abrir o expediente para realizar o fluxo completo de veículos: **registrar a entrada de carros por placa**, buscar e **remover veículos do pátio validando se a placa existe no sistema**, calcular a tarifa com base nas horas de permanência (cobrando R\$ 10,00 por hora com filtro contra horários inválidos) e emitir um relatório de **fechamento de caixa com o faturamento bruto consolidado** do dia.

### 🔄 Fluxo da Aplicação
```text
[ Entrada de Veículo ] ➔ [ Registro de Placa ] ➔ [ Validação de Saída ] ➔ [ Cálculo de Permanência ] ➔ [ Fechamento de Caixa ]
```

---

## 🎨 Design do Código (Como as regras conversam)

Este projeto foi estruturado utilizando um menu iterativo baseado em **Máquinas de Estado com Loops While**, permitindo navegar de forma contínua entre as funcionalidades da aplicação. As regras de negócio e ações de pátio foram distribuídas usando um controle de fluxo por **Múltipla Escolha (Switch-Case)** integrado a **Coleções Dinâmicas (`List<String>`)**. Veja o mapa limpo do design:

```text
       ┌────────────────────────┐
       │   Controle do Sistema  │ (Loop 'while' baseado no estado de abertura do programa)
       └───────────┬────────────┘
                   │
                   ▼
       ┌────────────────────────┐
       │     Menu de Opções     │ (Direciona as ações através de um Switch-Case)
       └───────────┬────────────┘
                   │
         ┌─────────┼─────────┬─────────┐
         ▼         ▼         ▼         ▼
     [Case 1]   [Case 2]  [Case 3]  [Case 4]
     Entrada    Saída     Exibir    Encerrar
     Veículo   (Cálculo)  Pátio     Expediente
         │         │         │         │
         └─────────┼─────────┴─────────┘
                   │
                   ▼
       ┌────────────────────────┐
       │  Validação de Estado   │ (Loop interno gerencia tela de espera e reinicialização)
       └────────────────────────┘
```

### 📋 Mapeamento de Variáveis e Estruturas

| Estrutura / Variável | O que ela faz no código? |
| :--- | :--- |
| 🚀 `main.java` | Classe principal executável que gerencia a persistência em memória do pátio e o fluxo de terminal. |
| 📑 `placasCarros` | `List<String>` dinâmica (`ArrayList`) responsável por armazenar as placas de todos os carros atualmente estacionados. |
| 🔑 `placas` | String temporária que captura a placa digitada pelo operador tanto na triagem de entrada quanto na liberação de saída. |
| ⏰ `horario` | Inteiro que recebe o tempo de permanência do carro para tarifação, monitorado por um filtro `while` de barreira (0h a 23h). |
| 💰 `faturamentoTotal` | Armazena temporariamente o subtotal gerado pela multiplicação das horas do veículo atual pela taxa horária fixada. |
| 📊 `faturamentoDoDia` | Acumulador financeiro global que soma de forma cumulativa todos os recebimentos confirmados do turno. |

---

## 🧠 Conceitos de Programação Praticados

* **Manipulação de Coleções Dinâmicas (`List`):** Utilização dos métodos nativos `.add()` para registrar a entrada, `.contains()` para validar a existência em pátio e `.remove()` para dar baixa na saída do veículo.
* **Filtros Condicionais de Fluxo Seguro:** Aplicação de checagem booleana (`if (existe)`) para impedir que o sistema dê baixa em carros inexistentes ou faça cálculos com dados fantasmas.
* **Máquina de Estados de Terminal:** Uso de laços `while` aninhados atuando como chaves controladoras (`escolha = 2`) para alternar dinamicamente entre o estado de operação ativa e o estado de espera/fechamento do console.

---

## 💻 Exemplo Prático de Uso

Imagine a seguinte simulação de um veículo entrando, saindo com tarifação e o posterior fechamento de caixa rodando direto no terminal:

```text
Iniciar programa - 1
Fechar programa - 2
1

1 - Registrar Entrada de Veículo 
2 - Reegistrar saída de Veículo (pagamento) 
3 - Exibir Veículos no Pátio 
4 - Encerrar Expediente
1
digite a placa do carro
ABC-1234

1 - Registrar Entrada de Veículo 
2 - Reegistrar saída de Veículo (pagamento) 
3 - Exibir Veículos no Pátio 
4 - Encerrar Expediente
2
Digite a placa do carro que esta saindo: 
ABC-1234
Quanto tempo o carro ficou?
3
=============================
Carro liberado!!
Valor a ser pago: 30.00 
=============================

1 - Registrar Entrada de Veículo 
2 - Reegistrar saída de Veículo (pagamento) 
3 - Exibir Veículos no Pátio 
4 - Encerrar Expediente
4
=====================================
     FECHAMENTO DE CAIXA DO DIA      
=====================================
Faturamento bruto total: R\$ 30.00 reais
=====================================
Programa fechado com sucesso
============================"
Deseja iniciar o programa - 1
2
```
