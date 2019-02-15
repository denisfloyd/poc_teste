package br.com.teste.poc.domain.venda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.teste.poc.domain.venda.Venda;
import br.com.teste.poc.domain.venda.repository.IVendaRepository;
import br.com.teste.poc.domain.venda.validator.ValidarCodigoVenda;
import br.com.teste.poc.domain.venda.validator.ValidarIDVenda;
import br.com.teste.poc.domain.venda.validator.VendaPossuiCodigo;
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
public class VendaService extends ValidadorDefault<Venda> implements IVendaService {
	
	@Autowired
	private VendaPossuiCodigo possuiCodigo;
	
	@Autowired
	private ValidarCodigoVenda validarCodigo;

	@Autowired
	private ValidarIDVenda validarIDVenda;

	@Autowired
	private ErrorMessageStack errorStack;

	@Autowired
	private MessageStack messageStack;

	@Autowired
	private IVendaRepository repository;

	@Autowired
	private RequisitionUtil<Venda> requisition;

	@Override
	protected void validations(Venda handler) {

		log.debug(" PessoaService.validations() ");
		log.debug(" Pessoa: " + handler);

		// VALIDAR CÓDIGO DE PESSOA
		if (!possuiCodigo.isValid(handler)) {
			log.error("Código nulo ou vazio.");
			errorStack.addMessage("codigo_vazio");
		}

		// VALIDAR DUPLICAÇÃO DE CÓDIGO
		if (!validarCodigo.isValid(handler)) {
			log.error("Código já existe");
			errorStack.addMessage("codigo_ja_existe");
		}
	}

	@Override
	public Venda inserir(Venda v) throws NexusException {

		log.debug(" VendaService.inserir() ");
		log.debug(" Venda: " + v);

		// EXECUÇÃO DAS VALIDAÇÕES.
		validations(v);

		// VERIFICAR SE POSSUI ERROS
		if (errorStack.isError()) {

			// GERANDO MENSAGEM DE ERRO
			ErrorMessage error = ErrorMessage.builder().title("atencao").error("falha_ao_salvar_registro")
					.details(errorStack.getErrors()).build();

			throw NexusException.of(error);
		}

		// GERAÇÃO DE ID
		v.setId(NexusUUID.generateID());

		try {

			// SALVAR Venda
			repository.salvar(v);

			// GERAR MENSAGEM DE SUCESSO
			messageStack.addMessage(TypeMessage.success, "registro_salvo_com_sucesso");
			log.debug("Sucesso ao salvar venda: " + v);

		} catch (Exception e) {
			log.error("Erro ao salvar Pessoa: " + e.getMessage());

			// GERAR MENSAGEM DE ERRO
			ErrorMessage error = ErrorMessage.builder().title("falha_ao_salvar_registro").error(e.getMessage()).build();

			throw NexusException.of(error);
		}

		return v;
	}

	@Override
	public Venda atualizar(String id, Venda v) throws NexusException {

		log.debug(" VendaService.inserir() ");
		log.debug(" Venda: " + v);

		if (!validarIDVenda.isValid(id)) {
			log.debug("Registro inválido.");
			errorStack.addMessage("registro_invalido");

			// GERANDO MENSAGEM DE ERRO
			ErrorMessage error = ErrorMessage.builder().title("atencao").error("falha_ao_alterar_registro")
					.details(errorStack.getErrors()).build();

			throw NexusException.of(error);
		}

		v.setId(id);

		// EXECUÇÃO DAS VALIDAÇÕES.
		validations(v);

		// VERIFICAR SE POSSUI ERROS
		if (errorStack.isError()) {

			// GERANDO MENSAGEM DE ERRO
			ErrorMessage error = ErrorMessage.builder().title("atencao").error("falha_ao_alterar_registro")
					.details(errorStack.getErrors()).build();

			throw NexusException.of(error);
		}

		try {

			// SALVAR PESSOA
			repository.atualizar(v);

			// GERAR MENSAGEM DE SUCESSO
			messageStack.addMessage(TypeMessage.success, "registro_alterado_com_sucesso");
			log.debug("Sucesso ao salvar venda: " + v);

		} catch (Exception e) {
			log.error("Erro ao salvar Pessoa: " + e.getMessage());

			// GERAR MENSAGEM DE ERRO
			ErrorMessage error = ErrorMessage.builder().title("falha_ao_alterar_registro").error(e.getMessage())
					.build();

			throw NexusException.of(error);
		}

		return v;

	}

	@Override
	public PageModel<Venda> selecionarTodos() throws NexusException {

		log.debug(" PessoaService.selecionarTodos() ");

		List<Venda> Vendas = repository.selecionarTodos();
		Boolean hasNext = requisition.getHasNext();

		return new PageModel<>(Vendas, hasNext);
	}

	@Override
	public void excluir(String id) throws NexusException {

		log.debug(" VendaService.excluir() ");
		log.debug(" ID: " + id);

		if (!validarIDVenda.isValid(id)) {
			log.debug("Registro inválido.");
			errorStack.addMessage("registro_invalido");

			// GERANDO MENSAGEM DE ERRO
			ErrorMessage error = ErrorMessage.builder().title("atencao").error("falha_ao_alterar_registro")
					.details(errorStack.getErrors()).build();

			throw NexusException.of(error);
		}

		repository.excluirVenda(id);
		
		// GERAR MENSAGEM DE SUCESSO
		messageStack.addMessage(TypeMessage.success, "registro_excluido_com_sucesso");
		log.debug("Sucesso ao excluir Venda com ID: " + id);
	}

}
