package br.com.teste.poc.app.venda.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.teste.poc.domain.venda.Venda;
import br.eti.nexus.kernel.application.dto.annotation.Attribute;
import br.eti.nexus.kernel.application.dto.request.CollectionRequestDTO;
import br.eti.nexus.kernel.application.dto.response.SingleResponseDTO;
import br.eti.nexus.kernel.infrastructure.util.DateUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class VendaDTO extends SingleResponseDTO  {

	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private static final long serialVersionUID = 3238541579718107040L;

	@Attribute(field="id")
	private String id;
	
	@Attribute(field="codigo")
	private Long code;
	
	@Attribute(field="data_venda")
	private String date_sale;
	
	@Attribute(field="valor_total")
	private BigDecimal total_value;
	

	public VendaDTO(Venda v, CollectionRequestDTO request) {
		super();
		
		List<String> attributes = request != null ? request.getAttributes() : new ArrayList<>();
		
		if (attributes.isEmpty() || attributes.contains("id")) {
        	this.id = v.getId();
        }   
		
		if (attributes.isEmpty() || attributes.contains("code")) {
        	this.code = v.getCodigo();
        }
		
		if (attributes.isEmpty() || attributes.contains("birth")) {
        	this.date_sale = v.getData_venda() != null ? new DateUtil().formatDateTime(v.getData_venda()) : null;
        }
		
		if (attributes.isEmpty() || attributes.contains("valor_total")) {
        	this.total_value = v.getValor_total();
        }
		
	}
}
