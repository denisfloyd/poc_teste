package br.com.teste.poc.domain.cidade.validator;

import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import br.com.teste.poc.domain.cidade.Cidade;
import br.eti.nexus.kernel.domain.validator.Validator;

@Component
public class CidadePossuiNome implements Validator<Cidade> {
	@Override
	public Predicate<Cidade> toImplementation() {
		return c -> {

			if (c == null) {
				return false;
			}

			if (c.getNome() == null) {
				return false;
			}

			return true;

		};
	}
}
