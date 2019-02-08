package br.com.teste.poc.app.cidade;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.teste.poc.app.cidade.converter.CidadeConverter;
import br.com.teste.poc.app.cidade.dto.CidadeDTO;
import br.com.teste.poc.app.pessoa.dto.PessoaDTO;
import br.com.teste.poc.domain.cidade.Cidade;
import br.com.teste.poc.domain.cidade.service.CidadeService;
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
public class CidadeAppService extends PaginationImp<Cidade> {

	@Autowired
	private CidadeService service;

	@Autowired
	private IMessageTranslator translator;

	@Autowired
	private MessageStack messageStack;

	@Autowired
	private CidadeConverter converter;

	@Autowired
	private RequisitionUtil<Cidade> requisition;

	@Autowired
	private PaginationUtil paginationUtil;

	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, readOnly = false, rollbackFor = {
			RuntimeException.class, Exception.class })
	public CidadeDTO salvar(CidadeDTO dto) {

		log.debug(" CidadeAppService.salvar() ");
		log.debug(" Cidade: " + dto);

		Cidade c = service.inserir(converter.convertModel(dto));

		dto = converter.convertDTO(c);

		return dto;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, readOnly = false, rollbackFor = {
			RuntimeException.class, Exception.class })
	public CidadeDTO alterar(String id, CidadeDTO dto) {

		log.debug(" CidadeAppService.alterar() ");
		log.debug(" ID: " + id);
		log.debug(" Cidade: " + dto);

		Cidade p = service.atualizar(id, converter.convertModel(dto));

		dto = converter.convertDTO(p);

		return dto;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, isolation = Isolation.DEFAULT, readOnly = true)
	public CollectionResponseDTO<CidadeDTO> selecionarTodos() {

		log.debug(" CidadeAppService.selecionarTodos() ");

		// CRIAÇÃO DA PAGINAÇÃO
		requisition.setPage(paginationUtil.generatePagination(PessoaDTO.class));
		requisition.setWhere(generateWhere());

		// CHAMADA DO SERVIÇO
		PageModel<Cidade> page = service.selecionarTodos();

		// CRIAÇÃO DO MODELO DE COLEÇÃO.
		CollectionResponseDTO<CidadeDTO> cidades = converter.convertManyCollectionResponse(page.getData());
		cidades.setKeep(page.getHasNext());

		return cidades;
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
	protected Cidade createModel() {

		log.debug(" CidadeAppService.createModel() ");

		// CAPTURANDO ATRIBUTOS DA REQUISIÇÃO.
		Map<String, Object> queryAttributes = requisition.getAttributes().generateQuery();

		if (queryAttributes != null && !queryAttributes.isEmpty()) {
			Cidade.CidadeBuilder builder = Cidade.builder();

			if (queryAttributes.containsKey("id")) {
				builder.id(String.valueOf(queryAttributes.get("id")));
			}
			
			if (queryAttributes.containsKey("code")) {
				builder.codigo(Long.parseLong(queryAttributes.get("code").toString()));
			}

			if (queryAttributes.containsKey("name")) {
				builder.nome(String.valueOf(queryAttributes.get("name")));
			}
			
			if (queryAttributes.containsKey("uf")) {
				builder.nome(String.valueOf(queryAttributes.get("uf")));
			}

			return builder.build();

		}

		return null;
	}
}
