package br.com.teste.poc.app.venda;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.teste.poc.app.venda.converter.VendaConverter;
import br.com.teste.poc.app.venda.dto.VendaDTO;
import br.com.teste.poc.domain.venda.Venda;
import br.com.teste.poc.domain.venda.service.VendaService;
import br.eti.nexus.kernel.application.dto.response.CollectionResponseDTO;
import br.eti.nexus.kernel.infrastructure.dynamic.jpa.model.PageModel;
import br.eti.nexus.kernel.infrastructure.dynamic.jpa.util.PaginationImp;
import br.eti.nexus.kernel.infrastructure.dynamic.jpa.util.PaginationUtil;
import br.eti.nexus.kernel.infrastructure.dynamic.jpa.util.RequisitionUtil;
import br.eti.nexus.kernel.infrastructure.util.DateUtil;
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
public class VendaAppService extends PaginationImp<Venda> {

	@Autowired
	private VendaService service;

	@Autowired
	private IMessageTranslator translator;

	@Autowired
	private MessageStack messageStack;

	@Autowired
	private VendaConverter converter;

	@Autowired
	private RequisitionUtil<Venda> requisition;

	@Autowired
	private PaginationUtil paginationUtil;

	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, readOnly = false, rollbackFor = {
			RuntimeException.class, Exception.class })
	public VendaDTO salvar(VendaDTO dto) {

		log.debug(" VendaAppService.salvar() ");
		log.debug(" Venda: " + dto);

		Venda p = service.inserir(converter.convertModel(dto));

		dto = converter.convertDTO(p);

		return dto;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, readOnly = false, rollbackFor = {
			RuntimeException.class, Exception.class })
	public VendaDTO alterar(String id, VendaDTO dto) {

		log.debug(" VendaAppService.alterar() ");
		log.debug(" ID: " + id);
		log.debug(" Venda: " + dto);

		Venda p = service.atualizar(id, converter.convertModel(dto));

		dto = converter.convertDTO(p);

		return dto;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, isolation = Isolation.DEFAULT, readOnly = true)
	public CollectionResponseDTO<VendaDTO> selecionarTodos() {

		log.debug(" VendaAppService.selecionarTodos() ");

		// CRIAÇÃO DA PAGINAÇÃO
		requisition.setPage(paginationUtil.generatePagination(VendaDTO.class));
		requisition.setWhere(generateWhere());

		// CHAMADA DO SERVIÇO
		PageModel<Venda> page = service.selecionarTodos();

		// CRIAÇÃO DO MODELO DE COLEÇÃO.
		CollectionResponseDTO<VendaDTO> Vendas = converter.convertManyCollectionResponse(page.getData());
		Vendas.setKeep(page.getHasNext());

		return Vendas;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, readOnly = false, rollbackFor = {
			RuntimeException.class, Exception.class })
	public VendaDTO excluir(String id) {

		log.debug(" VendaAppService.excluir() ");
		log.debug(" ID: " + id);

		// EXECUTAR SERVIÇO
		service.excluir(id);

		// REALIZAR TRADUÇÃO
		VendaDTO dto = new VendaDTO();
		translator.translateMessages(messageStack.getMessages());
		dto.setMessages(messageStack.getMessages());

		return dto;
	}

	@Override
	protected Venda createModel() {

		log.debug(" VendaAppService.createModel() ");

		// CAPTURANDO ATRIBUTOS DA REQUISIÇÃO.
		Map<String, Object> queryAttributes = requisition.getAttributes().generateQuery();

		if (queryAttributes != null && !queryAttributes.isEmpty()) {
			Venda.VendaBuilder builder = Venda.builder();

			if (queryAttributes.containsKey("id")) {
				builder.id(String.valueOf(queryAttributes.get("id")));
			}

			if (queryAttributes.containsKey("code")) {
				builder.codigo(Long.parseLong(queryAttributes.get("code").toString()));
			}

			if (queryAttributes.containsKey("date_sale")) {
				builder.data_venda(new DateUtil().parseDate(String.valueOf(queryAttributes.get("date_sale"))));
			}
			
			if (queryAttributes.containsKey("unit_value")) {
				builder.valor_total(new BigDecimal(String.valueOf(queryAttributes.get("total_value"))));
			}

			return builder.build();

		}

		return null;
	}
}
