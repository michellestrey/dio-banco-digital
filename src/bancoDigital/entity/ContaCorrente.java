package bancoDigital.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ContaCorrente extends Conta{

	public ContaCorrente(int agencia, String numeroDaConta, String titular, BigDecimal saldo, LocalDateTime dataDeAbertura,
			List<String> extrato) {
		super(agencia, numeroDaConta, titular, saldo, dataDeAbertura, extrato);
		
	}

}
