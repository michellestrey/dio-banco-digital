package bancoDigital.entity;

import java.util.ArrayList;
import java.util.List;

public class Banco {
	
	private String nome;
	private List<Conta> contas;

	public Banco(String nome) {
		super();
		this.nome = nome;
		this.contas = new ArrayList<>();
	}
	
	public List<Conta> getContas(){
		return contas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void addConta(Conta conta) {
		contas.add(conta);
	}
	

}
