package bancoDigital.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import bancoDigital.entity.Conta;
import bancoDigital.entity.ContaCorrente;
import bancoDigital.entity.ContaPoupanca;
import bancoDigital.service.BancoService;
import bancoDigital.service.ContaService;

public class BancoController {

    private final BancoService bancoService;
    private final ContaService contaService;
    private final Scanner scanner;

    public BancoController(BancoService bancoService, ContaService contaService) {
        this.bancoService = bancoService;
        this.contaService = contaService;
        this.scanner = new Scanner(System.in);
    }
    

    public void iniciar() {
        while (true) {
            System.out.println("\n=== MENU DO BANCO ===");
            System.out.println("1. Cadastrar conta");
            System.out.println("2. Remover conta");
            System.out.println("3. Listar contas");
            System.out.println("4. Buscar conta");
            System.out.println("5. Depositar");
            System.out.println("6. Sacar");
            System.out.println("7. Transferir");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1 -> cadastrarConta();
                case 2 -> removerConta();
                case 3 -> listarContas();
                case 4 -> buscarConta();
                case 5 -> depositar();
                case 6 -> sacar();
                case 7 -> transferir();
                case 8 -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void cadastrarConta() {
        System.out.print("Número da conta: ");
        String numero = scanner.nextLine();

        System.out.print("Nome do titular: ");
        String titular = scanner.nextLine();

        System.out.println("Tipo da conta:");
        System.out.println("1. Conta Corrente");
        System.out.println("2. Conta Poupança");
        System.out.print("Escolha: ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); 

        Conta conta;
        if (tipo == 1) {
            conta = new ContaCorrente(1, numero, titular, BigDecimal.ZERO, LocalDateTime.now(), new ArrayList<>());
        } else if (tipo == 2) {
            conta = new ContaPoupanca(numero, titular);
        } else {
            System.out.println("Tipo inválido.");
            return;
        }

        try {
            bancoService.cadastrarConta(conta);
            System.out.println("Conta cadastrada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void removerConta() {
        System.out.print("Número da conta a remover: ");
        String numero = scanner.nextLine();

        try {
            bancoService.removerConta(numero);
            System.out.println("Conta removida com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarContas() {
        var contas = bancoService.listarContas();
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
            return;
        }
        System.out.println("Contas cadastradas:");
        contas.forEach(c -> System.out.println(
            "Conta: " + c.getNumeroDaConta() + " | Titular: " + c.getTitular() + " | Saldo: " + c.getSaldo() + " | " + c.dataAberturaConta()
        ));
    }

    private void buscarConta() {
        System.out.print("Número da conta: ");
        String numero = scanner.nextLine();

        try {
            Conta conta = bancoService.buscarConta(numero);
            
            System.out.println("Conta encontrada:");
            System.out.println("Titular: " + conta.getTitular());
            System.out.println("Saldo: " + conta.getSaldo());
            System.out.println("Data de abertura: " + conta.dataAberturaConta());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void depositar() {
        System.out.print("Número da conta para depósito: ");
        String numero = scanner.nextLine();

        System.out.print("Valor para depositar: ");
        BigDecimal valor = scanner.nextBigDecimal();
        scanner.nextLine();

        try {
        	Conta contaDeposito = bancoService.buscarConta(numero);
        	contaService.depositar(contaDeposito, valor);
            System.out.println("Depósito realizado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro no depósito: " + e.getMessage());
        }
    }

    private void sacar() {
        System.out.print("Número da conta: ");
        String numero = scanner.nextLine();
        Conta conta = bancoService.buscarConta(numero);  
        
        if (conta == null) {
            System.out.println("Conta não encontrada!");
            return;
        }
        System.out.print("Valor para sacar: ");
        BigDecimal valor = scanner.nextBigDecimal();
        scanner.nextLine();

        try {
            contaService.sacar(conta, valor);
            System.out.println("Saque realizado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro no saque: " + e.getMessage());
        }
    }

    private void transferir() {
        System.out.print("Número da conta origem: ");
        String origem = scanner.nextLine();
        System.out.print("Número da conta destino: ");
        String destino = scanner.nextLine();
        System.out.print("Valor para transferir: ");
        BigDecimal valor = scanner.nextBigDecimal();
        scanner.nextLine();
       
        try {
        	Conta ContaOrigem = bancoService.buscarConta(origem);
        	Conta contaDestino = bancoService.buscarConta(destino);
            contaService.transferir(ContaOrigem, valor, contaDestino);
            System.out.println("Transferência realizada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro na transferência: " + e.getMessage());
        }
    }
}

