package br.com.teste.poc.app.cidade.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.teste.poc.app.cidade.dto.CidadeDTO;
import br.com.teste.poc.domain.cidade.Cidade;
import br.eti.nexus.kernel.application.converter.ConverterDTO;
import br.eti.nexus.kernel.infrastructure.dynamic.jpa.util.RequisitionUtil;
import br.eti.nexus.kernel.messages.domain.MessageStack;
import br.eti.nexus.kernel.messages.translator.IMessageTranslator;

@Component
public class CidadeConverter extends ConverterDTO<Cidade, CidadeDTO> {
	
	@Autowired
	private RequisitionUtil<Cidade> requisition;
	
	@Autowired
	private MessageStack messageStack;
	
	@Autowired
	private IMessageTranslator translator;

	@Override
	public CidadeDTO convertDTO(Cidade model) {
		
		CidadeDTO dto = new CidadeDTO(model, requisition.getAttributes());
		
		if(messageStack.getMessages().size() > 0) {
			translator.translateMessages(messageStack.getMessages());
			dto.setMessages(messageStack.getMessages());
		}
		
		return dto;
	}

	@Override
	public Cidade convertModel(CidadeDTO dto) {
		
		return Cidade.builder()
						.id(dto.getId())
						.codigo(dto.getCode())
						.nome(dto.getName())
						.uf(dto.getUf())
						.build();
	}

}
