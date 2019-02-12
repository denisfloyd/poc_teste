package br.com.teste.poc.app.produto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.teste.poc.app.produto.dto.ProdutoDTO;
import br.com.teste.poc.domain.produto.Produto;
import br.eti.nexus.kernel.application.converter.ConverterDTO;
import br.eti.nexus.kernel.infrastructure.dynamic.jpa.util.RequisitionUtil;
import br.eti.nexus.kernel.messages.domain.MessageStack;
import br.eti.nexus.kernel.messages.translator.IMessageTranslator;

@Component
public class ProdutoConverter extends ConverterDTO<Produto, ProdutoDTO> {
	
	@Autowired
	private RequisitionUtil<Produto> requisition;
	
	@Autowired
	private MessageStack messageStack;
	
	@Autowired
	private IMessageTranslator translator;

	@Override
	public ProdutoDTO convertDTO(Produto model) {
		
		ProdutoDTO dto = new ProdutoDTO(model, requisition.getAttributes());
		
		if(messageStack.getMessages().size() > 0) {
			translator.translateMessages(messageStack.getMessages());
			dto.setMessages(messageStack.getMessages());
		}
		
		return dto;
	}

	@Override
	public Produto convertModel(ProdutoDTO dto) {
			
		//Cidade c = cidadeService.buscarPorID(dto.get.getId());
		
		return Produto.builder()
						.id(dto.getId())
						.codigo(dto.getCode())
						.desc(dto.getDescription())
						.valor_unitario(dto.getUnit_value())
						.build();
	}

}
