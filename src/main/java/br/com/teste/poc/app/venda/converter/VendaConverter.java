package br.com.teste.poc.app.venda.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.teste.poc.app.venda.dto.VendaDTO;
import br.com.teste.poc.domain.venda.Venda;
import br.eti.nexus.kernel.application.converter.ConverterDTO;
import br.eti.nexus.kernel.infrastructure.dynamic.jpa.util.RequisitionUtil;
import br.eti.nexus.kernel.infrastructure.util.DateUtil;
import br.eti.nexus.kernel.messages.domain.MessageStack;
import br.eti.nexus.kernel.messages.translator.IMessageTranslator;

@Component
public class VendaConverter extends ConverterDTO<Venda, VendaDTO> {
	
	@Autowired
	private RequisitionUtil<Venda> requisition;
	
	@Autowired
	private MessageStack messageStack;
	
	@Autowired
	private IMessageTranslator translator;
	
	//@Autowired 
	//private CidadeService cidadeService;

	@Override
	public VendaDTO convertDTO(Venda model) {
		
		VendaDTO dto = new VendaDTO(model, requisition.getAttributes());
		
		if(messageStack.getMessages().size() > 0) {
			translator.translateMessages(messageStack.getMessages());
			dto.setMessages(messageStack.getMessages());
		}
		
		return dto;
	}

	@Override
	public Venda convertModel(VendaDTO dto) {
			
		//Cidade c = cidadeService.buscarPorID(dto.get.getId());
		
		return Venda.builder()
						.id(dto.getId())
						.codigo(dto.getCode())
						.data_venda(new DateUtil().parseDate(dto.getDate_sale()))
						.valor_total(dto.getTotal_value())
						.build();
	}

	/*
	@Override
	public Pessoa convertModelInserir(VendaDTOInserir dto) {
			
		//Cidade c = cidadeService.buscarPorID(dto.get.getId());
		
		return Pessoa.builder()
						.id(dto.getId())
						.codigo(dto.getCode())
						.nome(dto.getName())
						.nascimento(new DateUtil().parseDate(dto.getBirth()))
						.id_cidade(dto.getCityId())
						.build();
	} */
	
}
