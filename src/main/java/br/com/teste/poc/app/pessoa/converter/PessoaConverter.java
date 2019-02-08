package br.com.teste.poc.app.pessoa.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.teste.poc.app.pessoa.dto.PessoaDTO;
import br.com.teste.poc.domain.cidade.service.CidadeService;
import br.com.teste.poc.domain.pessoa.Pessoa;
import br.eti.nexus.kernel.application.converter.ConverterDTO;
import br.eti.nexus.kernel.infrastructure.dynamic.jpa.util.RequisitionUtil;
import br.eti.nexus.kernel.infrastructure.util.DateUtil;
import br.eti.nexus.kernel.messages.domain.MessageStack;
import br.eti.nexus.kernel.messages.translator.IMessageTranslator;

@Component
public class PessoaConverter extends ConverterDTO<Pessoa, PessoaDTO> {
	
	@Autowired
	private RequisitionUtil<Pessoa> requisition;
	
	@Autowired
	private MessageStack messageStack;
	
	@Autowired
	private IMessageTranslator translator;
	
	@Autowired CidadeService cidadeService;

	@Override
	public PessoaDTO convertDTO(Pessoa model) {
		
		PessoaDTO dto = new PessoaDTO(model, requisition.getAttributes());
		
		if(messageStack.getMessages().size() > 0) {
			translator.translateMessages(messageStack.getMessages());
			dto.setMessages(messageStack.getMessages());
		}
		
		return dto;
	}

	@Override
	public Pessoa convertModel(PessoaDTO dto) {
			
		//Cidade c = cidadeService.buscarPorID(dto.get.getId());
		
		return Pessoa.builder()
						.id(dto.getId())
						.codigo(dto.getCode())
						.nome(dto.getName())
						.nascimento(new DateUtil().parseDate(dto.getBirth()))
						.cidade(dto.getCity())
						.build();
	}

}
