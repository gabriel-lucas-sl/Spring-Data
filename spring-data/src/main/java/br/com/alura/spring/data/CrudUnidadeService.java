package br.com.alura.spring.data;

import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.repository.UnidadeRepository;

@Service
public class CrudUnidadeService {
	private Boolean system = true;
	private UnidadeRepository repository;
	
	public CrudUnidadeService(UnidadeRepository repository) {
		this.repository = repository;
	}
	
	public void inicial() {
		Scanner s = new Scanner(System.in);
		while(system) {
			System.out.println("\n\nO que você deseja?\n"
					+ "1 - Adiconar funcionário\n"
					+ "2 - Atualizar funcionário\n"
					+ "3 - Listar funcionários\n"
					+ "4 - Deletar funcionário\n"
					+ "5 - Sair\n");
			int action = s.nextInt();
			
			switch(action) {
				case 1:
					add(s);
					break;
				
				case 2:
					update(s);
					break;
				
				case 3:
					read();
					break;
				
				case 4:
					delete(s);
					break;
				
				case 5:
					System.out.println(".\n.\n.\n3 2 1 Saindo");
					System.exit(0);
			}
		}
	}
	
	private int add(Scanner s) {
		
		return 0;
	}
	
	private int update(Scanner s) {
		
		return 0;
	}
	
	private List read() {
		
		return null;
	}
	
	private int delete(Scanner s) {
		
		return 0;
	}
	
	
}
