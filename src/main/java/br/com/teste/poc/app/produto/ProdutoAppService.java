package br.com.teste.poc.app.produto;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.teste.poc.app.produto.converter.ProdutoConverter;
import br.com.teste.poc.app.produto.dto.ProdutoDTO;
import br.com.teste.poc.domain.produto.Produto;
import br.com.teste.poc.domain.produto.service.ProdutoService;
import br.eti.nexus.kernel.application.dto.response.CollectionResponseDTO;
import br.eti.nexus.kernel.infrastructure.dynamic.jpa.model.PageModel;
import br.eti.nexus.kernel.infrastructure.dynamic.jpa.util.PaginationImp;
import br.eti.nexus.kernel.infrastructure.dynamic.jpa.util.PaginationUtil;
import br.eti.nexus.kernel.infrastructure.dynamic.jpa.util.RequisitionUtil;
import br.eti.nexus.kernel.messages.domain.MessageStack;
import br.eti.nexus.kernel.messages.translator.IMessageTranslator;
import lombok.extern.log4j.Log4j2;

/**
 * 
 * @author Denis M. Ladeira
 *
 */
@Service
@Log4j2
public class ProdutoAppService extends PaginationImp<Produto> {

	@Autowired
	private ProdutoService service;

	@Autowired
	private IMessageTranslator translator;

	@Autowired
	private MessageStack messageStack;

	@Autowired
	private ProdutoConverter converter;

	@Autowired
	private RequisitionUtil<Produto> requisition;

	@Autowired
	private PaginationUtil paginationUtil;

	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, readOnly = false, rollbackFor = {
			RuntimeException.class, Exception.class })
	public ProdutoDTO salvar(ProdutoDTO dto) {

		log.debug(" ProdutoAppService.salvar() ");
		log.debug(" Produto: " + dto);

		Produto p = service.inserir(converter.convertModel(dto));

		dto = converter.convertDTO(p);

		return dto;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, readOnly = false, rollbackFor = {
			RuntimeException.class, Exception.class })
	public ProdutoDTO alterar(String id, ProdutoDTO dto) {

		log.debug(" ProdutoAppService.alterar() ");
		log.debug(" ID: " + id);
		log.debug(" Produto: " + dto);

		Produto p = service.atualizar(id, converter.convertModel(dto));

		dto = converter.convertDTO(p);

		return dto;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, isolation = Isolation.DEFAULT, readOnly = true)
	public CollectionResponseDTO<ProdutoDTO> selecionarTodos() {

		log.debug(" ProdutoAppService.selecionarTodos() ");

		// CRIAÇÃO DA PAGINAÇÃO
		requisition.setPage(paginationUtil.generatePagination(ProdutoDTO.class));
		requisition.setWhere(generateWhere());

		// CHAMADA DO SERVIÇO
		PageModel<Produto> page = service.selecionarTodos();

		// CRIAÇÃO DO MODELO DE COLEÇÃO.
		CollectionResponseDTO<ProdutoDTO> produtos = converter.convertManyCollectionResponse(page.getData());
		produtos.setKeep(page.getHasNext());

		return produtos;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, readOnly = false, rollbackFor = {
			RuntimeException.class, Exception.class })
	public ProdutoDTO excluir(String id) {

		log.debug(" ProdutoAppService.excluir() ");
		log.debug(" ID: " + id);

		// EXECUTAR SERVIÇO
		service.excluir(id);

		// REALIZAR TRADUÇÃO
		ProdutoDTO dto = new ProdutoDTO();
		translator.translateMessages(messageStack.getMessages());
		dto.setMessages(messageStack.getMessages());

		return dto;
	}

	@Override
	protected Produto createModel() {

		log.debug(" ProdutoAppService.createModel() ");

		// CAPTURANDO ATRIBUTOS DA REQUISIÇÃO.
		Map<String, Object> queryAttributes = requisition.getAttributes().generateQuery();

		if (queryAttributes != null && !queryAttributes.isEmpty()) {
			Produto.ProdutoBuilder builder = Produto.builder();

			if (queryAttributes.containsKey("id")) {
				builder.id(String.valueOf(queryAttributes.get("id")));
			}

			if (queryAttributes.containsKey("code")) {
				builder.codigo(Long.parseLong(queryAttributes.get("code").toString()));
			}

			if (queryAttributes.containsKey("name")) {
				builder.desc(String.valueOf(queryAttributes.get("description")));
			}
			
			if (queryAttributes.containsKey("unit_value")) {
				builder.valor_unitario(new BigDecimal(String.valueOf(queryAttributes.get("unit_value"))));
			}

			return builder.build();

		}

		return null;
	}
}
