package br.com.alura.spring.data;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;

@Service
public class CrudCargoService {
	private Boolean system = true;
    private final CargoRepository cargoRepository;
    
    public CrudCargoService(CargoRepository cargoRepository) {
    	this.cargoRepository = cargoRepository;
    }
	
	public void inicial() {
		Scanner s = new Scanner(System.in);
		while(system) {
			System.out.println("\n\nO que você deseja?\n"
					+ "1 - Adiconar cargo\n"
					+ "2 - Atualizar cargo\n"
					+ "3 - Listar cargos\n"
					+ "4 - Deletar cargo\n"
					+ "5 - Sair\n");
			int action = s.nextInt();
			
			switch(action) {
				case 1:
					salvar(s);
					break;
				
				case 2:
					atualizar(s);
					break;
				
				case 3:
					visualizar();
					break;
				
				case 4:
					deletar(s);
					break;
				
				case 5:
					System.out.println(".\n.\n.\n3 2 1 Saindo");
					System.exit(0);
				
			}
			
		}
	}
	
	private void salvar(Scanner s) {
		System.out.println("Descrição do cargo: ".toUpperCase());
		String descricao = s.next();
		Cargo cargo = new Cargo();
		cargo.setDescricao(descricao);
		
		cargoRepository.save(cargo);
		System.out.println("\nCargo adicionado com sucesso!\n");
	}
	
	private void atualizar(Scanner s) {
		System.out.println("ID do cargo: ");
		int id = s.nextInt();
		System.out.println("Descrição do cargo atualizada: ");
		String descricao = s.next();
		
		Cargo cargo = new Cargo();
		cargo.setId(id);
		cargo.setDescricao(descricao);
		
		cargoRepository.save(cargo);
		System.out.println("\nCargo atualizado com sucesso!\n");
	}
	
	private void visualizar() {
		Iterable<Cargo> cargos = cargoRepository.findAll();
		cargos.forEach(cargo -> System.out.println(cargo));
	}
	
	private void deletar(Scanner s) {
		System.out.println("ID do cargo:");
		int id = s.nextInt();
		cargoRepository.deleteById(id);
		System.out.println("\nCargo deletado!\n");
	}
    
}
