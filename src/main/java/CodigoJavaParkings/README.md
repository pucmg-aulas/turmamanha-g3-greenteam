# Código do Projeto

Mantenha neste diretório todo o código fonte do projeto. 

## Estrutra do projeto

O projeto é composto pelas seguintes classes principais:

1. **Cliente.java**: Representa um cliente do estacionamento, contendo atributos como nome, CPF e informações pessoais relevantes.

1. **Veiculo.java**: Define os veículos que pertencem aos clientes, com atributos como placa, modelo, cor, etc.

1. **Estacionamento.java**: Responsável por gerenciar o estacionamento como um todo, incluindo controle das vagas disponíveis, ocupadas e a emissão de faturas.

1. **Fatura.java**: Define a estrutura para geração de faturas, calculando o valor a ser cobrado pelo uso das vagas do estacionamento.

1. **Vaga.java**: Classe genérica que representa uma vaga no estacionamento. Outras classes especializadas herdam dessa, como:

- **VagaIdoso.java**: Vagas reservadas para idosos.
- **VagaPCD.java**: Vagas reservadas para pessoas com deficiência.
- **VagaVIP.java**: Vagas exclusivas para clientes VIPs.
- **VagaRegular.java**: Vagas comuns do estacionamento.

## Funcionalidades

O sistema permite as seguintes operações:

- Registro de clientes e seus veículos.
- Controle de vagas específicas para diferentes tipos de usuários (regular, idoso, PCD, VIP).
- Geração de faturas de acordo com o tempo de permanência no estacionamento.
- Gerenciamento do fluxo de entrada e saída de veículos.
