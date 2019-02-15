package br.com.teste.poc.domain.itemvenda.validator;

import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.teste.poc.domain.itemvenda.repository.IItemVendaRepository;
import br.eti.nexus.kernel.domain.validator.Validator;

/**
 * Classe responsável pela Validação de ID do Item de Venda.
 * 
 * @author Denis M. Ladeira - denis.ladeira@fivesoftware.com.br
 *
 */
@Component
public class ValidarIDItemVenda implements Validator<String> {

	@Autowired
	private IItemVendaRepository repository;

	@Override
	public Predicate<String> toImplementation() {
		return id -> {
			
			return repository.buscarPorID(id) != null;
			
		};
	}


}
