package br.com.alura.spring.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;

@Service
public class CrudFuncionarioService {
	private Boolean system = true;
	private final FuncionarioRepository repository;
	Funcionario func = new Funcionario();
	
	public CrudFuncionarioService(FuncionarioRepository repository) {
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
					+ "5 - Consultar funcionário\n"
					+ "6 - Sair\n");
			int action = s.nextInt();
			
			switch(action) {
				case 1:
					add(s);
					break;
					
				case 3:
					List<Funcionario> lista = list();
					for(Funcionario f: lista) {
						System.out.println("ID           : " + f.getId() + 
								           "\nNome       : " + f.getNome() +
								           "\nCPF        : " + f.getCpf() +
								           "\nSalário    : " + f.getSalario() +
								           "\nContratação: " + f.getDataContratacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
								           +"\n");
					}
					break;
					
				
				case 4:
					int result = delete(s);
					if (result == 0) {
						System.out.println("Funcionário não deletado!");
					}
					System.out.println("Funcionário deletado com sucesso!");
					break;
				
				case 5:
					Funcionario funcionario = read(s);
					System.out.println("ID           : " + funcionario.getId() + 
					           "\nNome       : " + funcionario.getNome() +
					           "\nCPF        : " + funcionario.getCpf() +
					           "\nSalário    : " + funcionario.getSalario() +
					           "\nContratação: " + funcionario.getDataContratacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
					           +"\n");
					break;
				
				case 6:
					System.out.println(".\n.\n.\n3 2 1 Saindo");
					System.exit(0);
			}
		}
	}
	
	private Funcionario add(Scanner s) {
		System.out.println("\nNome");
		func.setNome(s.next().toUpperCase());
	
		System.out.println("\nCPF");
		func.setCpf(s.next());
		
		System.out.println("\nSalário");
		func.setSalario(s.nextDouble());
		
		System.out.println("\nData da contratação\nObs: Insira a data nesse modelo - dd/MM/yyyy");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		func.setDataContratacao(LocalDate.parse(s.next(), formatter));
		
		Funcionario f = repository.save(func);
		if(f == null) {
			System.out.println("Funcionário não cadastrado!");
			return null;
		}
		System.out.println("Funcionário cadastrado com sucesso!");
		return f;
	}
	
	private int delete(Scanner s) {
		System.out.println("\nDeletanto funcionário\n"
				+ "ID:");
		int id = s.nextInt();
		repository.deleteById(id);
		Funcionario f = read(id);
		if (f == null) {
			return 1;
		}
		return 0;
	}
	
	private List<Funcionario> list() {
		Iterable<Funcionario> lista = repository.findAll();
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		lista.forEach(func -> funcionarios.add(func));
		return funcionarios;
	}
	
	private Funcionario read(Scanner s) {
		System.out.println("ID do funcionário:");
		Optional<Funcionario> funcionario = repository.findById(s.nextInt());
		
		if (funcionario == null) {
			System.out.println("Funcionário não existe!");
		}
		
		return funcionario.get();
	}
	
	private Funcionario read(int id) {
		Optional<Funcionario> funcionario = repository.findById(id);
		
		if (funcionario == null) {
			System.out.println("Funcionário não existe!");
		}
		
		return funcionario.get();
	}
	
	
}
