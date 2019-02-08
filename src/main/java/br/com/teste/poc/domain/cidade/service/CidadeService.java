package br.com.teste.poc.domain.cidade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.teste.poc.domain.cidade.Cidade;
import br.com.teste.poc.domain.cidade.repository.ICidadeRepository;
import br.com.teste.poc.domain.cidade.validator.CidadePossuiNome;
import br.com.teste.poc.domain.cidade.validator.ValidarCodigoCidade;
import br.com.teste.poc.domain.cidade.validator.ValidarIDCidade;
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
public class CidadeService extends ValidadorDefault<Cidade> implements ICidadeService {

	@Autowired
	private CidadePossuiNome possuiNome;

	@Autowired
	private ValidarIDCidade validarIDCidade;
	
	@Autowired
	private ValidarCodigoCidade validarCodigoCidade;

	@Autowired
	private ErrorMessageStack errorStack;

	@Autowired
	private MessageStack messageStack;

	@Autowired
	private ICidadeRepository repository;

	@Autowired
	private RequisitionUtil<Cidade> requisition;

	@Override
	protected void validations(Cidade handler) {

		log.debug(" PessoaService.validations() ");
		log.debug(" Pessoa: " + handler);

		// VALIDAR NOME DE PESSOA
		if (!possuiNome.isValid(handler)) {
			log.error("Nome nulo ou vazio.");
			errorStack.addMessage("nome_vazio");
		}
		
		// VALIDAR DUPLICAÇÃO DE CÓDIGO
		if (!validarCodigoCidade.isValid(handler)) {
			log.error("Código já existe");
			errorStack.addMessage("codigo_ja_existe");
		}

		// TODO VALIDACAO DE UF
	}

	@Override
	public Cidade inserir(Cidade c) throws NexusException {

		log.debug(" PessoaService.inserir() ");
		log.debug(" Cidade: " + c);

		// EXECUÇÃO DAS VALIDAÇÕES.
		validations(c);

		// VERIFICAR SE POSSUI ERROS
		if (errorStack.isError()) {

			// GERANDO MENSAGEM DE ERRO
			ErrorMessage error = ErrorMessage.builder().title("atencao").error("falha_ao_salvar_registro")
					.details(errorStack.getErrors()).build();

			throw NexusException.of(error);
		}

		// GERAÇÃO DE ID
		c.setId(NexusUUID.generateID());

		try {

			// SALVAR PESSOA
			repository.salvar(c);

			// GERAR MENSAGEM DE SUCESSO
			messageStack.addMessage(TypeMessage.success, "registro_salvo_com_sucesso");
			log.debug("Sucesso ao salvar cidade: " + c);

		} catch (Exception e) {
			log.error("Erro ao salvar Pessoa: " + e.getMessage());

			// GERAR MENSAGEM DE ERRO
			ErrorMessage error = ErrorMessage.builder().title("falha_ao_salvar_registro").error(e.getMessage()).build();

			throw NexusException.of(error);
		}

		return c;
	}

	@Override
	public Cidade atualizar(String id, Cidade c) throws NexusException {

		log.debug(" PessoaService.inserir() ");
		log.debug(" Cidade: " + c);

		if (!validarIDCidade.isValid(id)) {
			log.debug("Registro inválido.");
			errorStack.addMessage("registro_invalido");

			// GERANDO MENSAGEM DE ERRO
			ErrorMessage error = ErrorMessage.builder().title("atencao").error("falha_ao_alterar_registro")
					.details(errorStack.getErrors()).build();

			throw NexusException.of(error);
		}

		c.setId(id);

		// EXECUÇÃO DAS VALIDAÇÕES.
		validations(c);

		// VERIFICAR SE POSSUI ERROS
		if (errorStack.isError()) {

			// GERANDO MENSAGEM DE ERRO
			ErrorMessage error = ErrorMessage.builder().title("atencao").error("falha_ao_alterar_registro")
					.details(errorStack.getErrors()).build();

			throw NexusException.of(error);
		}

		try {

			// SALVAR PESSOA
			repository.atualizar(c);

			// GERAR MENSAGEM DE SUCESSO
			messageStack.addMessage(TypeMessage.success, "registro_alterado_com_sucesso");
			log.debug("Sucesso ao salvar cidade: " + c);

		} catch (Exception e) {
			log.error("Erro ao salvar Pessoa: " + e.getMessage());

			// GERAR MENSAGEM DE ERRO
			ErrorMessage error = ErrorMessage.builder().title("falha_ao_alterar_registro").error(e.getMessage())
					.build();

			throw NexusException.of(error);
		}

		return c;

	}

	@Override
	public PageModel<Cidade> selecionarTodos() throws NexusException {

		log.debug(" CidadeService.selecionarTodos() ");

		List<Cidade> cidades = repository.selecionarTodos();
		Boolean hasNext = requisition.getHasNext();

		return new PageModel<>(cidades, hasNext);
	}

	@Override
	public void excluir(String id) throws NexusException {

		log.debug(" CidadeService.excluir() ");
		log.debug(" ID: " + id);

		if (!validarIDCidade.isValid(id)) {
			log.debug("Registro inválido.");
			errorStack.addMessage("registro_invalido");

			// GERANDO MENSAGEM DE ERRO
			ErrorMessage error = ErrorMessage.builder().title("atencao").error("falha_ao_alterar_registro")
					.details(errorStack.getErrors()).build();

			throw NexusException.of(error);
		}

		repository.excluirCidade(id);
		
		// GERAR MENSAGEM DE SUCESSO
		messageStack.addMessage(TypeMessage.success, "registro_excluido_com_sucesso");
		log.debug("Sucesso ao excluir cidade com ID: " + id);
	}
	
	@Override
	public Cidade buscarPorID(String id) throws NexusException {

		log.debug(" CidadeService.selecionarTodos() ");

		Cidade cidade = repository.buscarPorID(id);
		//Boolean hasNext = requisition.getHasNext();

		return cidade;
	}

}
