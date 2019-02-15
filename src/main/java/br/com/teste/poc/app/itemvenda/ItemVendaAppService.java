package br.com.teste.poc.app.itemvenda;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.teste.poc.app.itemvenda.converter.ItemVendaConverter;
import br.com.teste.poc.app.itemvenda.dto.ItemVendaDTO;
import br.com.teste.poc.domain.itemvenda.ItemVenda;
import br.com.teste.poc.domain.itemvenda.service.ItemVendaService;
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
public class ItemVendaAppService extends PaginationImp<ItemVenda> {

	@Autowired
	private ItemVendaService service;

	@Autowired
	private IMessageTranslator translator;

	@Autowired
	private MessageStack messageStack;

	@Autowired
	private ItemVendaConverter converter;

	@Autowired
	private RequisitionUtil<ItemVenda> requisition;

	@Autowired
	private PaginationUtil paginationUtil;

	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, readOnly = false, rollbackFor = {
			RuntimeException.class, Exception.class })
	public ItemVendaDTO salvar(ItemVendaDTO dto) {

		log.debug(" ItemVendaAppService.salvar() ");
		log.debug(" ItemVenda: " + dto);

		ItemVenda iv = service.inserir(converter.convertModel(dto));

		dto = converter.convertDTO(iv);

		return dto;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, readOnly = false, rollbackFor = {
			RuntimeException.class, Exception.class })
	public ItemVendaDTO alterar(String id, ItemVendaDTO dto) {

		log.debug(" ItemVendaAppService.alterar() ");
		log.debug(" ID: " + id);
		log.debug(" ItemVenda: " + dto);

		ItemVenda p = service.atualizar(id, converter.convertModel(dto));

		dto = converter.convertDTO(p);

		return dto;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, isolation = Isolation.DEFAULT, readOnly = true)
	public CollectionResponseDTO<ItemVendaDTO> selecionarTodos() {

		log.debug(" ItemVendaAppService.selecionarTodos() ");

		// CRIAÇÃO DA PAGINAÇÃO
		requisition.setPage(paginationUtil.generatePagination(ItemVendaDTO.class));
		requisition.setWhere(generateWhere());

		// CHAMADA DO SERVIÇO
		PageModel<ItemVenda> page = service.selecionarTodos();

		// CRIAÇÃO DO MODELO DE COLEÇÃO.
		CollectionResponseDTO<ItemVendaDTO> itemsdevenda = converter.convertManyCollectionResponse(page.getData());
		itemsdevenda.setKeep(page.getHasNext());

		return itemsdevenda;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, readOnly = false, rollbackFor = {
			RuntimeException.class, Exception.class })
	public ItemVendaDTO excluir(String id) {

		log.debug(" ItemVendaAppService.excluir() ");
		log.debug(" ID: " + id);

		// EXECUTAR SERVIÇO
		service.excluir(id);

		// REALIZAR TRADUÇÃO
		ItemVendaDTO dto = new ItemVendaDTO();
		translator.translateMessages(messageStack.getMessages());
		dto.setMessages(messageStack.getMessages());

		return dto;
	}

	@Override
	protected ItemVenda createModel() {

		log.debug(" ItemVendaAppService.createModel() ");

		// CAPTURANDO ATRIBUTOS DA REQUISIÇÃO.
		Map<String, Object> queryAttributes = requisition.getAttributes().generateQuery();

		if (queryAttributes != null && !queryAttributes.isEmpty()) {
			ItemVenda.ItemVendaBuilder builder = ItemVenda.builder();

			if (queryAttributes.containsKey("id")) {
				builder.id(String.valueOf(queryAttributes.get("id")));
			}

			if (queryAttributes.containsKey("quantity")) {
				builder.quantidade(Integer.parseInt(queryAttributes.get("quantity").toString()));
			}
			
			if (queryAttributes.containsKey("item_value")) {
				builder.valor_item(new BigDecimal(String.valueOf(queryAttributes.get("item_value"))));
			}

			return builder.build();

		}

		return null;
	}
}
