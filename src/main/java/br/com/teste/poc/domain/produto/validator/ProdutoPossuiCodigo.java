package br.com.teste.poc.domain.produto.validator;

import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import br.com.teste.poc.domain.produto.Produto;
import br.eti.nexus.kernel.domain.validator.Validator;

/**
 * Classe para validar se o Produto possui código
 * @author Denis M. Ladeira
 *
 */
@Component
public class ProdutoPossuiCodigo implements Validator<Produto>{
	@Override
	public Predicate<Produto> toImplementation() {
		return p -> {

			if (p == null) {
				return false;
			}

			if (p.getCodigo() == null) {
				return false;
			}

			return true;

		};
	}
}
