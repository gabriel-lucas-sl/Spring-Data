package br.com.alura.spring.data;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Unidade;
import br.com.alura.spring.data.repository.UnidadeRepository;

@Service
public class CrudUnidadeService {
	private Boolean system = true;
	private UnidadeRepository repository;
	private Unidade unidade;
	
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
			int answer = 0;
			switch(action) {
				case 1:
					answer = add(s);
					if(answer == 1) {
						System.out.println("Unidade adicionada!");
					}
					System.out.println("Unidade não foi adicionada");
					break;
				
				case 2:
					answer = update(s);
					if(answer == 1) {
						System.out.println(answer + " Unidade(s) atualizada(s)!");
					}
					System.out.println(answer + " Unidade(s) atualizada(s)!");
					break;
				
				case 3:
					read();
					break;
				
				case 4:
					answer = delete(s);
					if (answer == 1) {
						System.out.println("Unidade deletada com sucesso!");				
					}
					System.out.println("Unidade não deletada!");
					break;
				
				case 5:
					System.out.println(".\n.\n.\n3 2 1 Saindo");
					System.exit(0);
			}
		}
	}
	
	private int add(Scanner s) {
		System.out.println("\nDescrição: ");
		unidade.setDescricao(s.next());
		
		System.out.println("\nEndereço:");
		unidade.setEndereco(s.next());
		
		Unidade u = repository.save(unidade);
		if (u != null) {
			return 1;			
		}
		return 0;
	}
	
	private int update(Scanner s) {
		System.out.println("\nID da Unidade de Trabalho:");
		unidade.setId(s.nextInt());
		
		System.out.println("\nNovo endereço: ");
		unidade.setEndereco(s.next());
		
		System.out.println("\nNova descrição: ");
		unidade.setDescricao(s.next());
		
		Unidade u = repository.save(unidade);
		if (u != null) {
			return 1;
		}
		return 0;
	}
	
	private void read() {
		Iterable<Unidade> u = repository.findAll();
		u.forEach(unidade -> System.out.println(unidade));
	}
	
	private int delete(Scanner s) {
		System.out.println("\nID da Unidade de Trabalho:");
		repository.deleteById(s.nextInt());
		Optional<Unidade> u = repository.findById(s.nextInt());
		if (u == null) {
			return 1;
		}
		return 0;
	}
	
	
}
