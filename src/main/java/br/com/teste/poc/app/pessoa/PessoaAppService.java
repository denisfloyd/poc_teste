package br.com.teste.poc.app.pessoa;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.teste.poc.app.pessoa.converter.PessoaConverter;
import br.com.teste.poc.app.pessoa.dto.PessoaDTO;
import br.com.teste.poc.domain.pessoa.Pessoa;
import br.com.teste.poc.domain.pessoa.service.PessoaService;
import br.eti.nexus.kernel.application.dto.response.CollectionResponseDTO;
import br.eti.nexus.kernel.infrastructure.dynamic.jpa.model.PageModel;
import br.eti.nexus.kernel.infrastructure.dynamic.jpa.util.PaginationImp;
import br.eti.nexus.kernel.infrastructure.dynamic.jpa.util.PaginationUtil;
import br.eti.nexus.kernel.infrastructure.dynamic.jpa.util.RequisitionUtil;
import br.eti.nexus.kernel.messages.domain.MessageStack;
import br.eti.nexus.kernel.messages.translator.IMessageTranslator;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PessoaAppService extends PaginationImp<Pessoa> {

	@Autowired
	private PessoaService service;

	@Autowired
	private IMessageTranslator translator;

	@Autowired
	private MessageStack messageStack;

	@Autowired
	private PessoaConverter converter;

	@Autowired
	private RequisitionUtil<Pessoa> requisition;

	@Autowired
	private PaginationUtil paginationUtil;

	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, readOnly = false, rollbackFor = {
			RuntimeException.class, Exception.class })
	public PessoaDTO salvar(PessoaDTO dto) {

		log.debug(" PessoaAppService.salvar() ");
		log.debug(" Pessoa: " + dto);

		Pessoa p = service.inserir(converter.convertModel(dto));

		dto = converter.convertDTO(p);

		return dto;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, readOnly = false, rollbackFor = {
			RuntimeException.class, Exception.class })
	public PessoaDTO alterar(String id, PessoaDTO dto) {

		log.debug(" PessoaAppService.alterar() ");
		log.debug(" ID: " + id);
		log.debug(" Pessoa: " + dto);

		Pessoa p = service.atualizar(id, converter.convertModel(dto));

		dto = converter.convertDTO(p);

		return dto;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, isolation = Isolation.DEFAULT, readOnly = true)
	public CollectionResponseDTO<PessoaDTO> selecionarTodos() {

		log.debug(" PessoaAppService.selecionarTodos() ");

		// CRIAÇÃO DA PAGINAÇÃO
		requisition.setPage(paginationUtil.generatePagination(PessoaDTO.class));
		requisition.setWhere(generateWhere());

		// CHAMADA DO SERVIÇO
		PageModel<Pessoa> page = service.selecionarTodos();

		// CRIAÇÃO DO MODELO DE COLEÇÃO.
		CollectionResponseDTO<PessoaDTO> pessoas = converter.convertManyCollectionResponse(page.getData());
		pessoas.setKeep(page.getHasNext());

		return pessoas;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, readOnly = false, rollbackFor = {
			RuntimeException.class, Exception.class })
	public PessoaDTO excluir(String id) {

		log.debug(" PessoaAppService.excluir() ");
		log.debug(" ID: " + id);

		// EXECUTAR SERVIÇO
		service.excluir(id);

		// REALIZAR TRADUÇÃO
		PessoaDTO dto = new PessoaDTO();
		translator.translateMessages(messageStack.getMessages());
		dto.setMessages(messageStack.getMessages());

		return dto;
	}

	@Override
	protected Pessoa createModel() {

		log.debug(" PessoaAppService.createModel() ");

		// CAPTURANDO ATRIBUTOS DA REQUISIÇÃO.
		Map<String, Object> queryAttributes = requisition.getAttributes().generateQuery();

		if (queryAttributes != null && !queryAttributes.isEmpty()) {
			Pessoa.PessoaBuilder builder = Pessoa.builder();

			if (queryAttributes.containsKey("id")) {
				builder.id(String.valueOf(queryAttributes.get("id")));
			}

			if (queryAttributes.containsKey("code")) {
				builder.codigo(Long.parseLong(queryAttributes.get("code").toString()));
			}

			if (queryAttributes.containsKey("name")) {
				builder.nome(String.valueOf(queryAttributes.get("name")));
			}

			return builder.build();

		}

		return null;
	}
}
