package br.com.teste.poc.domain.pessoa.validator;

import java.util.function.Predicate;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import br.com.teste.poc.domain.pessoa.Pessoa;
import br.eti.nexus.kernel.domain.validator.Validator;

/**
 * Classe responsável pela validação de Nome de Pessoa.
 * 
 * @author Patrick Francis Gomes Rocha - patrick.gomes@nexus.eti.br
 *
 */
@Component
public class PessoaPossuiNome implements Validator<Pessoa> {

	@Override
	public Predicate<Pessoa> toImplementation() {
		return p -> {

			if (p == null) {
				return false;
			}

			return StringUtils.isNotEmpty(p.getNome());

		};
	}

}
