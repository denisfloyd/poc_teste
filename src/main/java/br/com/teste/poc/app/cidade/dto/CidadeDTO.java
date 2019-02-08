package br.com.teste.poc.app.cidade.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.teste.poc.domain.cidade.Cidade;
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
public class CidadeDTO extends SingleResponseDTO  {

	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private static final long serialVersionUID = 3238541579718107040L;

	@Attribute(field="id")
	private String id;
	
	@Attribute(field="codigo")
	private Long code;
	
	@Attribute(field="nome")
	private String name;
	
	@Attribute(field="uf")
	private String uf;

	public CidadeDTO(Cidade c, CollectionRequestDTO request) {
		super();
		
		List<String> attributes = request != null ? request.getAttributes() : new ArrayList<>();
		
		if (attributes.isEmpty() || attributes.contains("id")) {
        	this.id = c.getId();
        }   
		
		if (attributes.isEmpty() || attributes.contains("code")) {
        	this.code = c.getCodigo();
        }   
		
		if (attributes.isEmpty() || attributes.contains("name")) {
        	this.name = c.getNome();
        }
		
		if (attributes.isEmpty() || attributes.contains("uf")) {
        	this.uf = c.getUf();
        }
		
	}
}
