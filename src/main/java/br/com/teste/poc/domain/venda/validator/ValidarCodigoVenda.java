package br.com.teste.poc.domain.venda.validator;

import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.teste.poc.domain.venda.Venda;
import br.com.teste.poc.domain.venda.repository.IVendaRepository;
import br.eti.nexus.kernel.domain.validator.Validator;

/**
 * Classe responsável pela realização da validação de código.
 * 
 * @author Denis M. Ladeira
 *
 */
@Component
public class ValidarCodigoVenda implements Validator<Venda> {
	
	@Autowired
	private IVendaRepository repository;

	@Override
	public Predicate<Venda> toImplementation() {
		return p -> {
			
			return repository.validarCodigo(p) == null;
			
		};
	}

}
