package br.com.teste.poc.app.itemvenda.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.teste.poc.domain.itemvenda.ItemVenda;
import br.eti.nexus.kernel.application.dto.annotation.Attribute;
import br.eti.nexus.kernel.application.dto.request.CollectionRequestDTO;
import br.eti.nexus.kernel.application.dto.response.SingleResponseDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ItemVendaDTO extends SingleResponseDTO  {

	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private static final long serialVersionUID = 3238541579718107040L;

	@Attribute(field="id")
	private String id;

	@Attribute(field="quantidade")
	private String quantity;
	
	@Attribute(field="valor_item")
	private BigDecimal item_value;
	

	public ItemVendaDTO(ItemVenda iv, CollectionRequestDTO request) {
		super();
		
		List<String> attributes = request != null ? request.getAttributes() : new ArrayList<>();
		
		if (attributes.isEmpty() || attributes.contains("id")) {
        	this.id = iv.getId();
        }   
		
		if (attributes.isEmpty() || attributes.contains("quantity")) {
        	this.quantity = String.valueOf(iv.getQuantidade());
        } 
		
		if (attributes.isEmpty() || attributes.contains("valor_total")) {
        	this.item_value = iv.getValor_item();
        }
		
	}
}
