package br.com.teste.poc.app.produto.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.teste.poc.domain.produto.Produto;
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
public class ProdutoDTO extends SingleResponseDTO  {

	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private static final long serialVersionUID = 3238541579718107040L;

	@Attribute(field="id")
	private String id;
	
	@Attribute(field="codigo")
	private Long code;
	
	@Attribute(field="descricao")
	private String description;
	
	@Attribute(field="valor_unitario")
	private BigDecimal unit_value;
	

	public ProdutoDTO(Produto p, CollectionRequestDTO request) {
		super();
		
		List<String> attributes = request != null ? request.getAttributes() : new ArrayList<>();
		
		if (attributes.isEmpty() || attributes.contains("id")) {
        	this.id = p.getId();
        }   
		
		if (attributes.isEmpty() || attributes.contains("code")) {
        	this.code = p.getCodigo();
        }
		
		if (attributes.isEmpty() || attributes.contains("desc")) {
        	this.description = p.getDesc();
        }
		
		if (attributes.isEmpty() || attributes.contains("valor_unitario")) {
        	this.unit_value = p.getValor_unitario();
        }
		
	}
}
