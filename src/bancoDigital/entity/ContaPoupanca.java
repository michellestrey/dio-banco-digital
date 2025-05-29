package bancoDigital.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ContaPoupanca extends Conta {
	
	

	public ContaPoupanca(String numeroDaConta, String titular) {
		super(numeroDaConta, titular, BigDecimal.ZERO, LocalDateTime.now(), new  java.util.ArrayList<>());
		extrato.add("Conta Poupança criada em " + dataDeAbertura.format(formatter));
		
	}
	public void renderJuros() {
		BigDecimal rendimento = saldo.multiply(new BigDecimal(0.005));
		saldo = saldo.add(rendimento);
			
		}
	}
	
	


