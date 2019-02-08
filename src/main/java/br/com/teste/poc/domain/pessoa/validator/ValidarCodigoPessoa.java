package br.com.teste.poc.domain.pessoa.validator;

import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.teste.poc.domain.pessoa.Pessoa;
import br.com.teste.poc.domain.pessoa.repository.IPessoaRepository;
import br.eti.nexus.kernel.domain.validator.Validator;

/**
 * Classe responsável pela realização da validação de código.
 * 
 * @author Patrick Francis Gomes Rocha - patrick.gomes@nexus.eti.br
 *
 */
@Component
public class ValidarCodigoPessoa implements Validator<Pessoa> {
	
	@Autowired
	private IPessoaRepository repository;

	@Override
	public Predicate<Pessoa> toImplementation() {
		return p -> {
			
			return repository.validarCodigo(p) == null;
			
		};
	}

}
