package br.com.teste.poc.domain.produto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.teste.poc.domain.produto.Produto;
import br.com.teste.poc.domain.produto.repository.IProdutoRepository;
import br.com.teste.poc.domain.produto.validator.ProdutoPossuiCodigo;
import br.com.teste.poc.domain.produto.validator.ProdutoPossuiDesc;
import br.com.teste.poc.domain.produto.validator.ValidarIDProduto;
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
public class ProdutoService extends ValidadorDefault<Produto> implements IProdutoService {
	
	@Autowired
	private ProdutoPossuiCodigo possuiCodigo;
	
	@Autowired
	private ProdutoPossuiDesc possuiDesc;
	
	//@Autowired
	//private ValidarCodigoPessoa validarCodigo;

	@Autowired
	private ValidarIDProduto validarIDProduto;

	@Autowired
	private ErrorMessageStack errorStack;

	@Autowired
	private MessageStack messageStack;

	@Autowired
	private IProdutoRepository repository;

	@Autowired
	private RequisitionUtil<Produto> requisition;

	@Override
	protected void validations(Produto handler) {

		log.debug(" PessoaService.validations() ");
		log.debug(" Pessoa: " + handler);

		// VALIDAR CÓDIGO DE PESSOA
		if (!possuiCodigo.isValid(handler)) {
			log.error("Código nulo ou vazio.");
			errorStack.addMessage("codigo_vazio");
		}

		// VALIDAR NOME DE PESSOA
		if (!possuiDesc.isValid(handler)) {
			log.error("Nome nulo ou vazio.");
			errorStack.addMessage("nome_vazio");
		}

		// VALIDAR DUPLICAÇÃO DE CÓDIGO
		/*if (!validarCodigo.isValid(handler)) {
			log.error("Código já existe");
			errorStack.addMessage("codigo_ja_existe");
		} */
	}

	@Override
	public Produto inserir(Produto p) throws NexusException {

		log.debug(" ProdutoService.inserir() ");
		log.debug(" Produto: " + p);

		// EXECUÇÃO DAS VALIDAÇÕES.
		validations(p);

		// VERIFICAR SE POSSUI ERROS
		if (errorStack.isError()) {

			// GERANDO MENSAGEM DE ERRO
			ErrorMessage error = ErrorMessage.builder().title("atencao").error("falha_ao_salvar_registro")
					.details(errorStack.getErrors()).build();

			throw NexusException.of(error);
		}

		// GERAÇÃO DE ID
		p.setId(NexusUUID.generateID());

		try {

			// SALVAR PRODUTO
			repository.salvar(p);

			// GERAR MENSAGEM DE SUCESSO
			messageStack.addMessage(TypeMessage.success, "registro_salvo_com_sucesso");
			log.debug("Sucesso ao salvar pessoa: " + p);

		} catch (Exception e) {
			log.error("Erro ao salvar Pessoa: " + e.getMessage());

			// GERAR MENSAGEM DE ERRO
			ErrorMessage error = ErrorMessage.builder().title("falha_ao_salvar_registro").error(e.getMessage()).build();

			throw NexusException.of(error);
		}

		return p;
	}

	@Override
	public Produto atualizar(String id, Produto p) throws NexusException {

		log.debug(" ProdutoService.inserir() ");
		log.debug(" Produto: " + p);

		if (!validarIDProduto.isValid(id)) {
			log.debug("Registro inválido.");
			errorStack.addMessage("registro_invalido");

			// GERANDO MENSAGEM DE ERRO
			ErrorMessage error = ErrorMessage.builder().title("atencao").error("falha_ao_alterar_registro")
					.details(errorStack.getErrors()).build();

			throw NexusException.of(error);
		}

		p.setId(id);

		// EXECUÇÃO DAS VALIDAÇÕES.
		validations(p);

		// VERIFICAR SE POSSUI ERROS
		if (errorStack.isError()) {

			// GERANDO MENSAGEM DE ERRO
			ErrorMessage error = ErrorMessage.builder().title("atencao").error("falha_ao_alterar_registro")
					.details(errorStack.getErrors()).build();

			throw NexusException.of(error);
		}

		try {

			// SALVAR PESSOA
			repository.atualizar(p);

			// GERAR MENSAGEM DE SUCESSO
			messageStack.addMessage(TypeMessage.success, "registro_alterado_com_sucesso");
			log.debug("Sucesso ao salvar pessoa: " + p);

		} catch (Exception e) {
			log.error("Erro ao salvar Pessoa: " + e.getMessage());

			// GERAR MENSAGEM DE ERRO
			ErrorMessage error = ErrorMessage.builder().title("falha_ao_alterar_registro").error(e.getMessage())
					.build();

			throw NexusException.of(error);
		}

		return p;

	}

	@Override
	public PageModel<Produto> selecionarTodos() throws NexusException {

		log.debug(" PessoaService.selecionarTodos() ");

		List<Produto> produtos = repository.selecionarTodos();
		Boolean hasNext = requisition.getHasNext();

		return new PageModel<>(produtos, hasNext);
	}

	@Override
	public void excluir(String id) throws NexusException {

		log.debug(" PessoaService.excluir() ");
		log.debug(" ID: " + id);

		if (!validarIDProduto.isValid(id)) {
			log.debug("Registro inválido.");
			errorStack.addMessage("registro_invalido");

			// GERANDO MENSAGEM DE ERRO
			ErrorMessage error = ErrorMessage.builder().title("atencao").error("falha_ao_alterar_registro")
					.details(errorStack.getErrors()).build();

			throw NexusException.of(error);
		}

		repository.excluirProduto(id);
		
		// GERAR MENSAGEM DE SUCESSO
		messageStack.addMessage(TypeMessage.success, "registro_excluido_com_sucesso");
		log.debug("Sucesso ao excluir pessoa com ID: " + id);
	}

}
