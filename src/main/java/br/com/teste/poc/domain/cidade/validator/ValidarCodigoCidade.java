package br.com.teste.poc.domain.cidade.validator;

import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.teste.poc.domain.cidade.Cidade;
import br.com.teste.poc.domain.cidade.repository.ICidadeRepository;
import br.eti.nexus.kernel.domain.validator.Validator;

/**
 * Classe responsável pela realização da validação de código.
 * 
 * @author Patrick Francis Gomes Rocha - patrick.gomes@nexus.eti.br
 *
 */
@Component
public class ValidarCodigoCidade implements Validator<Cidade> {
	
	@Autowired
	private ICidadeRepository repository;

	@Override
	public Predicate<Cidade> toImplementation() {
		return c -> {
			
			return repository.validarCodigo(c) == null;
			
		};
	}

}
