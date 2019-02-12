package br.com.teste.poc.domain.produto.validator;

import java.util.function.Predicate;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import br.com.teste.poc.domain.produto.Produto;
import br.eti.nexus.kernel.domain.validator.Validator;

/**
 * Classe Responsável para validação da Descrição do Produto
 * 
 * @author Denis M. Ladeira
 */
@Component
public class ProdutoPossuiDesc implements Validator<Produto>{
	@Override
	public Predicate<Produto> toImplementation() {
		return p -> {

			if (p == null) {
				return false;
			}

			return StringUtils.isNotEmpty(p.getDesc());

		};
	}
}
