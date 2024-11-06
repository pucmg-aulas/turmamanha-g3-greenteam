
# Sistema de Estacionamento - Guia Básico de Persistência

Este guia fornece instruções básicas sobre como persistir dados para cada tipo de objeto (Cliente, Veiculo, Fatura, e Vaga) no sistema de estacionamento.

---

## 1. ClienteDAO

Cada cliente é salvo no arquivo `clientes.txt` com um **ID**, **nome**, e placas de veículos associados.

### Código para Salvar um Cliente

```java
import dao.ClienteDAO;
import dao.ClienteDAOImpl;
import model.Cliente;
import model.Veiculo;

import java.util.Arrays;

public class PersistirCliente {
    public static void main(String[] args) {
        ClienteDAO clienteDAO = new ClienteDAOImpl();

        Cliente cliente = new Cliente("1", "João Silva");
        cliente.setVeiculos(Arrays.asList(new Veiculo("ABC1234"), new Veiculo("DEF5678")));

        clienteDAO.salvar(cliente); // Salva o cliente
    }
}
```

---

## 2. VeiculoDAO

Cada veículo é salvo no arquivo `veiculos.txt` com apenas a **placa**.

### Código para Salvar um Veículo

```java
import dao.VeiculoDAO;
import dao.VeiculoDAOImpl;
import model.Veiculo;

public class PersistirVeiculo {
    public static void main(String[] args) {
        VeiculoDAO veiculoDAO = new VeiculoDAOImpl();

        Veiculo veiculo = new Veiculo("XYZ9999");

        veiculoDAO.salvar(veiculo); // Salva o veículo
    }
}
```

---

## 3. FaturaDAO

Cada fatura é salva no arquivo `faturas.txt` com a **placa do veículo**, **tempo de início** (em milissegundos), **tempo de fim** (em milissegundos), e **valor**.

### Código para Salvar uma Fatura

```java
import dao.FaturaDAO;
import dao.FaturaDAOImpl;
import model.Fatura;
import model.Veiculo;

import java.util.Date;

public class PersistirFatura {
    public static void main(String[] args) {
        FaturaDAO faturaDAO = new FaturaDAOImpl();

        Veiculo veiculo = new Veiculo("ABC1234");
        Fatura fatura = new Fatura(veiculo, new Date(), new Date());
        fatura.setValor(25.0);

        faturaDAO.salvar(fatura); // Salva a fatura
    }
}
```

---

## 4. VagaDAO

Cada vaga é salva no arquivo `vagas.txt` com um **identificador**, **tipo** (VIP, Idoso, PCD, Regular), e **estado de ocupação**.

### Código para Salvar uma Vaga

```java
import dao.VagaDAO;
import dao.VagaDAOImpl;
import model.VagaVIP;

public class PersistirVaga {
    public static void main(String[] args) {
        VagaDAO vagaDAO = new VagaDAOImpl();

        VagaVIP vagaVIP = new VagaVIP("A01");
        vagaVIP.setOcupada(true);

        vagaDAO.salvar(vagaVIP); // Salva a vaga
    }
}
```

---

Este README fornece exemplos básicos para persistir cada tipo de objeto no sistema de estacionamento.
