package br.com.teste.poc.domain.venda.validator;

import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import br.com.teste.poc.domain.venda.Venda;
import br.eti.nexus.kernel.domain.validator.Validator;

/**
 * Classe para validar se o Venda possui código
 * @author Denis M. Ladeira
 *
 */
@Component
public class VendaPossuiCodigo implements Validator<Venda>{
	@Override
	public Predicate<Venda> toImplementation() {
		return v -> {

			if (v == null) {
				return false;
			}

			if (v.getCodigo() == null) {
				return false;
			}

			return true;

		};
	}
}
