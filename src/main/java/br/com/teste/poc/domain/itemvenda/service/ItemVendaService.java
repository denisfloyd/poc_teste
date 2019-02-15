package br.com.teste.poc.domain.itemvenda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.teste.poc.domain.itemvenda.ItemVenda;
import br.com.teste.poc.domain.itemvenda.repository.IItemVendaRepository;
import br.com.teste.poc.domain.itemvenda.validator.ValidarIDItemVenda;
import br.eti.nexus.kernel.domain.validator.ValidadorDefault;
import br.eti.nexus.kernel.exception.NexusException;
import br.eti.nexus.kernel.infrastructure.dynamic.jpa.model.PageModel;
import br.eti.nexus.kernel.infrastructure.dynamic.jpa.util.RequisitionUtil;
import br.eti.nexus.kernel.infrastructure.util.NexusUUID;
import br.eti.nexus.kernel.messages.ErrorMessage;
import br.eti.nexus.kernel.messages.TypeMessage;
import br.eti.nexus.kernel.messages.domain.ErrorMessageStack;
import br.eti.nexus.kernel.messages.domain.MessageStack;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ItemVendaService extends ValidadorDefault<ItemVenda> implements IItemVendaService {
	
	//@Autowired
	//private VendaPossuiCodigo possuiCodigo;
	
	//@Autowired
	//private ValidarCodigoVenda validarCodigo;

	@Autowired
	private ValidarIDItemVenda validarIDItemVenda;

	@Autowired
	private ErrorMessageStack errorStack;

	@Autowired
	private MessageStack messageStack;

	@Autowired
	private IItemVendaRepository repository;

	@Autowired
	private RequisitionUtil<ItemVenda> requisition;

	@Override
	protected void validations(ItemVenda handler) {

		log.debug(" PessoaService.validations() ");
		log.debug(" Pessoa: " + handler);

		/*
		// VALIDAR DUPLICAÇÃO DE CÓDIGO
		if (!validarCodigo.isValid(handler)) {
			log.error("Código já existe");
			errorStack.addMessage("codigo_ja_existe");
		} */
	}

	@Override
	public ItemVenda inserir(ItemVenda iv) throws NexusException {

		log.debug(" ItemVendaService.inserir() ");
		log.debug(" ItemVenda: " + iv);

		// EXECUÇÃO DAS VALIDAÇÕES.
		validations(iv);

		// VERIFICAR SE POSSUI ERROS
		if (errorStack.isError()) {

			// GERANDO MENSAGEM DE ERRO
			ErrorMessage error = ErrorMessage.builder().title("atencao").error("falha_ao_salvar_registro")
					.details(errorStack.getErrors()).build();

			throw NexusException.of(error);
		}

		// GERAÇÃO DE ID
		iv.setId(NexusUUID.generateID());

		try {

			// SALVAR Item de Venda
			repository.salvar(iv);

			// GERAR MENSAGEM DE SUCESSO
			messageStack.addMessage(TypeMessage.success, "registro_salvo_com_sucesso");
			log.debug("Sucesso ao salvar venda: " + iv);

		} catch (Exception e) {
			log.error("Erro ao salvar Pessoa: " + e.getMessage());

			// GERAR MENSAGEM DE ERRO
			ErrorMessage error = ErrorMessage.builder().title("falha_ao_salvar_registro").error(e.getMessage()).build();

			throw NexusException.of(error);
		}

		return iv;
	}

	@Override
	public ItemVenda atualizar(String id, ItemVenda iv) throws NexusException {

		log.debug(" ItemVendaService.inserir() ");
		log.debug(" ItemVenda: " + iv);

		if (!validarIDItemVenda.isValid(id)) {
			log.debug("Registro inválido.");
			errorStack.addMessage("registro_invalido");

			// GERANDO MENSAGEM DE ERRO
			ErrorMessage error = ErrorMessage.builder().title("atencao").error("falha_ao_alterar_registro")
					.details(errorStack.getErrors()).build();

			throw NexusException.of(error);
		}

		iv.setId(id);

		// EXECUÇÃO DAS VALIDAÇÕES.
		validations(iv);

		// VERIFICAR SE POSSUI ERROS
		if (errorStack.isError()) {

			// GERANDO MENSAGEM DE ERRO
			ErrorMessage error = ErrorMessage.builder().title("atencao").error("falha_ao_alterar_registro")
					.details(errorStack.getErrors()).build();

			throw NexusException.of(error);
		}

		try {

			// ATT ITEM DE VENDA
			repository.atualizar(iv);

			// GERAR MENSAGEM DE SUCESSO
			messageStack.addMessage(TypeMessage.success, "registro_alterado_com_sucesso");
			log.debug("Sucesso ao salvar venda: " + iv);

		} catch (Exception e) {
			log.error("Erro ao salvar Pessoa: " + e.getMessage());

			// GERAR MENSAGEM DE ERRO
			ErrorMessage error = ErrorMessage.builder().title("falha_ao_alterar_registro").error(e.getMessage())
					.build();

			throw NexusException.of(error);
		}

		return iv;

	}

	@Override
	public PageModel<ItemVenda> selecionarTodos() throws NexusException {

		log.debug(" PessoaService.selecionarTodos() ");

		List<ItemVenda> itensdevenda = repository.selecionarTodos();
		Boolean hasNext = requisition.getHasNext();

		return new PageModel<>(itensdevenda, hasNext);
	}

	@Override
	public void excluir(String id) throws NexusException {

		log.debug(" ItemVendaService.excluir() ");
		log.debug(" ID: " + id);

		if (!validarIDItemVenda.isValid(id)) {
			log.debug("Registro inválido.");
			errorStack.addMessage("registro_invalido");

			// GERANDO MENSAGEM DE ERRO
			ErrorMessage error = ErrorMessage.builder().title("atencao").error("falha_ao_alterar_registro")
					.details(errorStack.getErrors()).build();

			throw NexusException.of(error);
		}

		repository.excluirItemVenda(id);
		
		// GERAR MENSAGEM DE SUCESSO
		messageStack.addMessage(TypeMessage.success, "registro_excluido_com_sucesso");
		log.debug("Sucesso ao excluir Item de Venda com ID: " + id);
	}

}
