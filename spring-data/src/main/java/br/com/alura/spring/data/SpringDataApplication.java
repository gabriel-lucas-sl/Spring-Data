package br.com.alura.spring.data;


import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner{
	private final CrudCargoService cargoService;
	private final CrudFuncionarioService funcionarioService;
	private final CrudUnidadeService unidadeService;
	
	public SpringDataApplication(CrudCargoService cargoService, CrudFuncionarioService funcionarioService, CrudUnidadeService unidadeService) {
		this.cargoService = cargoService;
		this.funcionarioService = funcionarioService;
		this.unidadeService = unidadeService;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner s = new Scanner(System.in);
		boolean system = true;
		while(system) {
			System.out.println("Escolha a opção que deseja consultar:\n"
					+ "1) Cargo\n"
					+ "2) Funcionário\n"
					+ "3) Unidade\n"
					+ "4) Sair\n");
			int answer = s.nextInt();
			
			switch(answer) {
			case 1:
				cargoService.inicial();
				break;
			
			case 2:
				funcionarioService.inicial();
				break;
			
			case 3:
				unidadeService.inicial();
				break;
				
			case 4:
				System.out.println("\n\nFinalizando acesso...");
				system = false;
			}
		}
		
		
		
		
	}

}
