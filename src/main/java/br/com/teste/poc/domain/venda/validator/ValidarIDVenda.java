package br.com.teste.poc.domain.venda.validator;

import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.teste.poc.domain.pessoa.repository.IPessoaRepository;
import br.eti.nexus.kernel.domain.validator.Validator;

/**
 * Classe responsável pela Validação de ID de Pessoa.
 * 
 * @author Patrick Francis Gomes Rocha - patrick.gomes@nexus.eti.br
 *
 */
@Component
public class ValidarIDVenda implements Validator<String> {

	@Autowired
	private IPessoaRepository repository;

	@Override
	public Predicate<String> toImplementation() {
		return id -> {
			
			return repository.buscarPorID(id) != null;
			
		};
	}


}
