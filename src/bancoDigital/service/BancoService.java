package bancoDigital.service;

import java.util.List;

import bancoDigital.entity.Banco;
import bancoDigital.entity.Conta;

public class BancoService {

	private Banco banco;


	public BancoService(Banco banco) {
		this.banco = banco;

	}

	public void cadastrarConta(Conta conta) {
		boolean existe =  banco.getContas().stream()
				.anyMatch(c -> c.getNumeroDaConta().equals(conta.getNumeroDaConta()));
		if(existe){//SE FOR VERDADE
			throw new IllegalArgumentException("Essa conta já existe");
		}
		//SENÃO
		banco.addConta(conta);
	

	}

	public Conta buscarConta(String numeroDaConta) {

		return banco.getContas().stream()
				.filter(c -> c.getNumeroDaConta().equals(numeroDaConta))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Conta não encontrada!"));

	}

	public void removerConta(String numeroDaConta) {
		boolean removida = banco.getContas()
				.removeIf(c -> c.getNumeroDaConta().equals(numeroDaConta));
		System.out.println("Conta removida com sucesso!");

		if(!removida) {
			throw new IllegalArgumentException ("Conta não encontrada para a remoção!");
		}	

	}
	public List<Conta> listarContas(){
		return banco.getContas();
		
	}

	public int contarClientes() {
		return banco.getContas().size();
	}



}
