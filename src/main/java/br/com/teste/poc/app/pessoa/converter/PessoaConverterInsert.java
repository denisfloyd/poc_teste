package br.com.teste.poc.app.pessoa.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.teste.poc.app.pessoa.dto.PessoaDTOInserir;
import br.com.teste.poc.domain.cidade.Cidade;
import br.com.teste.poc.domain.cidade.service.CidadeService;
import br.com.teste.poc.domain.pessoa.Pessoa;
import br.eti.nexus.kernel.application.converter.ConverterDTO;
import br.eti.nexus.kernel.infrastructure.dynamic.jpa.util.RequisitionUtil;
import br.eti.nexus.kernel.infrastructure.util.DateUtil;
import br.eti.nexus.kernel.messages.domain.MessageStack;
import br.eti.nexus.kernel.messages.translator.IMessageTranslator;

@Component
public class PessoaConverterInsert extends ConverterDTO<Pessoa, PessoaDTOInserir> {
	
	@Autowired
	private RequisitionUtil<Pessoa> requisition;
	
	@Autowired
	private MessageStack messageStack;
	
	@Autowired
	private IMessageTranslator translator;
	
	@Autowired 
	private CidadeService cidadeService;

	@Override
	public PessoaDTOInserir convertDTO(Pessoa model) {
		
		PessoaDTOInserir dto = new PessoaDTOInserir(model, requisition.getAttributes());
		
		if(messageStack.getMessages().size() > 0) {
			translator.translateMessages(messageStack.getMessages());
			dto.setMessages(messageStack.getMessages());
		}
		
		return dto;
	}

	@Override
	public Pessoa convertModel(PessoaDTOInserir dto) {
			
		Cidade c = cidadeService.buscarPorID(dto.getCityId());
		
		return Pessoa.builder()
						.id(dto.getId())
						.codigo(dto.getCode())
						.nome(dto.getName())
						.nascimento(new DateUtil().parseDate(dto.getBirth()))
						.cidade(c)
						.build();
	}
	
}
