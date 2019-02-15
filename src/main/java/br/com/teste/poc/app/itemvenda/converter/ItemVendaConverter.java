package br.com.teste.poc.app.itemvenda.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.teste.poc.app.itemvenda.dto.ItemVendaDTO;
import br.com.teste.poc.domain.itemvenda.ItemVenda;
import br.eti.nexus.kernel.application.converter.ConverterDTO;
import br.eti.nexus.kernel.infrastructure.dynamic.jpa.util.RequisitionUtil;
import br.eti.nexus.kernel.messages.domain.MessageStack;
import br.eti.nexus.kernel.messages.translator.IMessageTranslator;

@Component
public class ItemVendaConverter extends ConverterDTO<ItemVenda, ItemVendaDTO> {
	
	@Autowired
	private RequisitionUtil<ItemVenda> requisition;
	
	@Autowired
	private MessageStack messageStack;
	
	@Autowired
	private IMessageTranslator translator;
	
	//@Autowired 
	//private CidadeService cidadeService;

	@Override
	public ItemVendaDTO convertDTO(ItemVenda model) {
		
		ItemVendaDTO dto = new ItemVendaDTO(model, requisition.getAttributes());
		
		if(messageStack.getMessages().size() > 0) {
			translator.translateMessages(messageStack.getMessages());
			dto.setMessages(messageStack.getMessages());
		}
		
		return dto;
	}

	@Override
	public ItemVenda convertModel(ItemVendaDTO dto) {
			
		//Cidade c = cidadeService.buscarPorID(dto.get.getId());
		
		return ItemVenda.builder()
						.id(dto.getId())
						.quantidade(Integer.parseInt(dto.getQuantity()))
						.valor_item(dto.getItem_value())
						.build();
	}

	
	
}
