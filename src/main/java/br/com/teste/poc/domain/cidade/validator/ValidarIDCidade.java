package br.com.teste.poc.domain.cidade.validator;

import java.util.function.Predicate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.teste.poc.domain.cidade.repository.ICidadeRepository;
import br.eti.nexus.kernel.domain.validator.Validator;

@Component
public class ValidarIDCidade implements Validator<String> {
	@Autowired
	private ICidadeRepository repository;

	@Override
	public Predicate<String> toImplementation() {
		return id -> {
			
			return repository.buscarPorID(id) != null;
			
		};
	}
	
}
