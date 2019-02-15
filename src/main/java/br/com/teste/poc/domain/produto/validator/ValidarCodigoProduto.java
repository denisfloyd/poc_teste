package br.com.teste.poc.domain.produto.validator;

import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.teste.poc.domain.produto.Produto;
import br.com.teste.poc.domain.produto.repository.IProdutoRepository;
import br.eti.nexus.kernel.domain.validator.Validator;

/**
 * Classe responsável pela realização da validação de código.
 * 
 * @author Denis M. Ladeira
 *
 */
@Component
public class ValidarCodigoProduto implements Validator<Produto> {
	
	@Autowired
	private IProdutoRepository repository;

	@Override
	public Predicate<Produto> toImplementation() {
		return p -> {
			
			return repository.validarCodigo(p) == null;
			
		};
	}

}
