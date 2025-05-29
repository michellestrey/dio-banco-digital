package application;

import bancoDigital.controller.BancoController;
import bancoDigital.entity.Banco;
import bancoDigital.service.BancoService;
import bancoDigital.service.ContaService;

public class Main {

	public static void main(String[] args) {
		
		Banco banco = new Banco("Banco Digital");
		BancoService bancoService = new BancoService(banco);
		ContaService contaService = new ContaService();
		BancoController controller = new BancoController(bancoService, contaService);
		
		
		controller.iniciar();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
