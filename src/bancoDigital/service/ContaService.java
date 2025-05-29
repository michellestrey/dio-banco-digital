package bancoDigital.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import bancoDigital.entity.Conta;

public class ContaService {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public void depositar(Conta conta, BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Valor inválido para depósito");
            return;
        }
        conta.setSaldo(conta.getSaldo().add(valor));
        String dataFormatada = LocalDateTime.now().format(formatter);
       
    }

    public boolean sacar(Conta conta, BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Valor inválido para saque");
            return false;
        }
        if (conta.getSaldo().compareTo(valor) < 0) {
            System.out.println("Saldo insuficiente");
            return false;
        }
        conta.setSaldo(conta.getSaldo().subtract(valor));
        String dataFormatada = LocalDateTime.now().format(formatter);
      
        return true;
    }

    public void validarTransferencia(Conta contaOrigem, BigDecimal valor, Conta contaDestino) {
        if (contaDestino == null || contaDestino.getNumeroDaConta() == null || contaDestino.getNumeroDaConta().isEmpty()) {
            throw new IllegalArgumentException("Conta de destino inválida");
        }
        if (contaDestino.getNumeroDaConta().equals(contaOrigem.getNumeroDaConta())) {
            throw new IllegalArgumentException("Transferência para a mesma conta não permitida!");
        }
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor de transferência inválido");
        }
        if (contaOrigem.getSaldo().compareTo(valor) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
    }

    public void transferir(Conta contaOrigem, BigDecimal valor, Conta contaDestino) {
        validarTransferencia(contaOrigem, valor, contaDestino);

        contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(valor));
        contaDestino.setSaldo(contaDestino.getSaldo().add(valor));

        String dataFormatada = LocalDateTime.now().format(formatter);
       

       
    }
}

