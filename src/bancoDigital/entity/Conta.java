package bancoDigital.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public abstract class Conta {

	protected int agencia;
    protected String numeroDaConta;
	protected String titular;
	protected BigDecimal saldo;
	protected LocalDateTime dataDeAbertura;
	protected List<String> extrato;
	protected static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	public Conta(int agencia, String numeroDaConta, String titular, BigDecimal saldo, LocalDateTime dataDeAbertura, List<String> extrato) {
		super();
		this.agencia = agencia;
		this.numeroDaConta = numeroDaConta;
		this.titular = titular;
		this.saldo = BigDecimal.ZERO;
		this.dataDeAbertura = dataDeAbertura;
	
	}

	public Conta(String numeroDaConta2, String titular2, BigDecimal zero, LocalDateTime now, Object object) {
		// TODO Auto-generated constructor stub
	}

	public int getAgencia() {
		return agencia;
	}

	public String getNumeroDaConta() {

		return numeroDaConta;
	}

	public String getTitular() {
		return titular;
	}

	  public String dataAberturaConta() {
	        if (dataDeAbertura == null) {
	            return "Data não informada";
	        }
	        return dataDeAbertura.format(formatter);
	    }
	

	public void setNumeroDaConta(String numeroDaConta) {
		this.numeroDaConta = numeroDaConta;
	}


	public void setTitular(String titular) {
		this.titular = titular;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public BigDecimal getExtrato(Conta conta) {
		System.out.println("Data de abertura da conta" + conta.dataAberturaConta());
		
		
		return null;
	}

	
	

}








